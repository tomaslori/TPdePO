package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import parser.*;

import backEnd.Board;
import backEnd.Direction;


public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JMenuBar MainMenu = new JMenuBar();
	JMenuItem open = new JMenuItem("Open");
	JMenuItem save = new JMenuItem("Save");
	JMenuItem quit = new JMenuItem("Quit");
	JFileChooser browsemap = new JFileChooser(); 
	
	public Frame(){
	
		super("Sokoban");
		setLayout(new BorderLayout());
		setBounds(300, 300, 500,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		
//		Menu File
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
//		NewGame
		JMenuItem newgame = new JMenuItem("New");
		newgame.setMnemonic(KeyEvent.VK_N);
//		newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		newgame.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e){
				BoardParser auxp;
				if((auxp = loadMap())!= null){
				Board board = auxp.getParsedBoard();
				newGame(board);
				}
			}	
		});
		
//		Open
		open.setMnemonic(KeyEvent.VK_O);
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		open.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e){
				openFile();
			}
		});
		
//		Save
		save.setMnemonic(KeyEvent.VK_S);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){


		}
	});
		
//		Quit
		quit.setMnemonic(KeyEvent.VK_Q);
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
		quit.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		file.add(newgame);
		file.add(open);
		file.add(save);
		file.add(quit);
		
//		Item Help
		
		JMenuItem help = new JMenuItem("?");
		
		
		MainMenu.add(file);
		MainMenu.add(help);
		
		setJMenuBar(MainMenu);	
	}
	
	
	public static void main(String[] args){
		Frame juego = new Frame();
		juego.setVisible(true);
	}
	
/**
 * Método que carga el mapa, invocando al Parser y revisa las excepciones
 * @return board
 * @throws CancelException 
 */
	private BoardParser loadMap(){
		BoardParser boardp = null;
		String aux;
		
		if( (aux=openFile()) != null){
			boardp = new BoardParser(aux);
		
		try {
			boardp.parse();
				} catch (NumberFormatException e) {
						boardp = exception("El argumento número " + boardp.getParamNumber() + " no es un número en la línea: " + boardp.getLineNumber());
					}
				catch(IncorrectFileExtensionException e){
						boardp = exception("Extensión de archivo inválida");
					}
				catch(BoardParamIsLTExpectedException e){
						boardp = exception("El argumento número " + boardp.getParamNumber() + "en la línea: " + boardp.getLineNumber() + " es menor al tamaño mínimo de tablero" );
					}
				catch(BoardParamIsGTExpectedException e){
						boardp = exception("El argumento número " + boardp.getParamNumber() + "en la línea: " + boardp.getLineNumber() + " es mayor al tamaño máximo de tablero" );
					}
				catch(WrongNumberOfArgumentsException e){
						boardp = exception("Error en el número de argumentos en la linea: " + boardp.getLineNumber());
					}
				catch(MissingPlayerException e){
						boardp = exception("El archivo no declara un jugador");
					}
				catch(ParamNotZeroException e){
						boardp = exception("Se espera un 0 en el argumento número: " + boardp.getParamNumber() + "en la línea: " + boardp.getLineNumber() );
					}
				catch(IllegalColorException e){
						boardp = exception("El color no es válido en la línea: " + boardp.getLineNumber() );
					}
				catch(PlayerAlreadyDeclaredException e){
						boardp = exception("El jugador ya fué declarado anteriormente, repetición de declaración en la linea: " + boardp.getLineNumber() );
					}
				catch(EmptyFileException e){
						boardp = exception("El archivo esta vacio o solo contiene comentarios");
					}
				catch (FileNotFoundException e) {
						boardp = exception("El archivo no existe");
					} 
				catch (EmptyArgumentException e) {
						boardp = exception("Un argumento esta vacio en la linea número:" + boardp.getLineNumber());
					}
		}
		return boardp;
	}
	
	private BoardParser exception(String str){
		JOptionPane.showMessageDialog(null , str, "Error", JOptionPane.ERROR_MESSAGE);
		BoardParser boardp = loadMap();
		return boardp;
	}
	
/**
 * Ventana que devuelve el string con la dirección del mapa elegido por el usuario 
 * @return String con la direccion
 */
	private String openFile(){
		
		String f = null;
		int ret = browsemap.showDialog(getContentPane(), "Open file");
		
		
		if (ret == JFileChooser.APPROVE_OPTION)
			f = browsemap.getSelectedFile().getPath();
		
		return f;
	}
	
	
	/**
	 * Método que coloca el tablero de juego y activa las teclas de movimiento
	 * @param board
	 */
	private void newGame(final Board board){
		final GamePanel juego;
		putOnBoard(juego = new GamePanel(board));
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				boolean result=false;
				Direction dir = null;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        System.out.println("UP");
                        result = board.move(dir = Direction.UP);
                        break;

                    case KeyEvent.VK_DOWN:
                    	System.out.println("DOWN");
                    	result = board.move(dir = Direction.DOWN);
                        break;

                    case KeyEvent.VK_LEFT:
                    	System.out.println("LEFT");
                    	result = board.move(dir = Direction.LEFT);
                        break;

                    case KeyEvent.VK_RIGHT:
                        System.out.println("RIGHT");
                        result = board.move(dir = Direction.RIGHT);
                        break;
                    
                    case KeyEvent.VK_R:
                    	System.out.println("R");
//                    	llamar restart
                    	break;
                 }
                if(result){
                	juego.playerMoved(board, dir);
                	setVisible(true);
                }
              
			}});
	}
	
/**
 * Método que coloca un panel
 * @param panel
 */
	private void putOnBoard(JPanel panel){
		add(panel);
		setVisible(true);
		requestFocus(true);
	}
}



	
	
