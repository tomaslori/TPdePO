package frontend;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;


public class GamePanel extends JPanel {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GamePanel(){
		setLayout(new FlowLayout());
		
		BoardPanel board = new BoardPanel(20,20,20);
		
//		Añadir Tablero
//		board.paintBorder(new EtchedBorder());
		
		JPanel stats = new JPanel();
		stats.setLayout(new FlowLayout());
		
		JLabel lname = new JLabel("Name: " );
		JLabel lscore = new JLabel("Score: "); //corregir
		JButton restart = new JButton("Restart");
		
		
		stats.add(lname);
		stats.add(lscore);
		stats.add(restart);
		stats.setBorder(new EtchedBorder());
		
		add(stats);
		
		
		add(board);
	}
}
