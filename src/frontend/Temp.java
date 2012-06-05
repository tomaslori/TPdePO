package frontend;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Temp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Temp(){
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        System.out.println("Arriba");
                        break;

                    case KeyEvent.VK_DOWN:
                    	System.out.println("Abajo");
                        break;

                    case KeyEvent.VK_LEFT:
                    	System.out.println("Izquierda");
                        break;

                    case KeyEvent.VK_RIGHT:
                        System.out.println("Derecha");
                        break;
                    
                    case KeyEvent.VK_R:
                    	System.out.println("R");
                    	break;
                 }
             }
	     });
	}
                
	public static void main(String[] args){
		
		Temp temp = new Temp();
		temp.setVisible(true);
	}

}
