package frontend;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;







public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JMenuBar MainMenu = new JMenuBar();
	JMenuItem open = new JMenuItem("Open");
	JMenuItem save = new JMenuItem("Save");
	JMenuItem quit = new JMenuItem("Quit");
	GamePanel gp = new GamePanel();
	JFileChooser browsemap = new JFileChooser(); 
	
	public Frame(){
	
		super("Sokoban");
		setLayout(new FlowLayout());
		setBounds(300, 300, 800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		
//		Menu File
		
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
//		NewGame
		JMenuItem newgame = new JMenuItem("New");
		newgame.setMnemonic(KeyEvent.VK_N);
		newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		newgame.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e){
				openFile();
				putOnBoard(new GamePanel());
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
	
//Ventana que abre los archivos
	private String openFile(){
		
		String f = null;
		int ret = browsemap.showDialog(getContentPane(), "Open file");
		
		
		if (ret == JFileChooser.APPROVE_OPTION){
			f = browsemap.getSelectedFile().getPath();
			
		}
		return f;
	}
	
//Método que coloca un panel
	private void putOnBoard(JPanel panel){
		add(panel);
		setVisible(true);
	}
}

	
	
