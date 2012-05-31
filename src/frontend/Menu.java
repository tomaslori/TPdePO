//package frontend;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.KeyStroke;
//
//public class Menu extends JMenuBar {
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	public Menu(){
//
//		//		Menu File
//		
//		JMenu file = new JMenu("File");
//		file.setMnemonic(KeyEvent.VK_F);
//		
//		JMenuItem newgame = new JMenuItem("New");
//		newgame.setMnemonic(KeyEvent.VK_N);
//		newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
//		newgame.addActionListener(new ActionListener() {
//			@Override
//				public void actionPerformed(ActionEvent e){
//				//Cargar mapa y enviarselo al juego
//				
//			}
//		});
//		
//		JMenuItem open = new JMenuItem("Open");
//		open.setMnemonic(KeyEvent.VK_O);
//		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
//		
//		JMenuItem save = new JMenuItem("Save");
//		save.setMnemonic(KeyEvent.VK_S);
//		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
//		
//		JMenuItem quit = new JMenuItem("Quit");
//		quit.setMnemonic(KeyEvent.VK_Q);
//		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
//		quit.addActionListener(new ActionListener() {
//		@Override
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
//		
//		file.add(newgame);
//		file.add(open);
//		file.add(save);
//		file.add(quit);
//		
////		Item Help
//		
//		JMenuItem help = new JMenuItem("?");
//		
//		
//		add(file);
//		add(help);
//	}
//}
