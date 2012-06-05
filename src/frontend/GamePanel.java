package frontend;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;

import java.io.IOException;
import java.util.HashMap;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;

import backEnd.Board;
import backEnd.BombBox;
import backEnd.Box;
import backEnd.Cell;
import backEnd.Direction;
import backEnd.EmptyElem;
import backEnd.Player;
import backEnd.Point;

import backEnd.Elem;
import backEnd.EmptyCell;

import backEnd.Target;


public class GamePanel extends JPanel {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	To do try catch
	private static final int CELL_SIZE = 32;
	private HashMap<String, Image> images = new HashMap<String, Image>(); 
	private BoardPanel boardP;
	private JLabel score;
	private JPanel stats;
	
	
	
	public GamePanel(final Board board){
		
		//TODO clonar tablero
		
		setLayout(null);
		setSize(board.getRows() * CELL_SIZE + 40, board.getCols() * CELL_SIZE + 40);
		setBorder(new EtchedBorder());
		
		System.out.println("Rows" + board.getRows() + "Cols" + board.getCols());
		
		
		
		try{
			images.put("backEnd.Box",ImageUtils.loadImage("resources/box.png"));
			images.put("backEnd.BombBox",ImageUtils.loadImage("resources/box.png"));
			images.put("backEnd.BlackHole",ImageUtils.loadImage("resources/hole2.png"));
			images.put("backEnd.Player",ImageUtils.loadImage("resources/smile.gif"));
			images.put("backEnd.Target",ImageUtils.loadImage("resources/wood.png"));
			images.put("backEnd.Wall",ImageUtils.loadImage("resources/wall2.png"));
			images.put("backEnd.EmptyCell",ImageUtils.loadImage("resources/wood.png"));
		} 
		catch (IOException e) {
			System.out.println("Error al cargar imagenes.");
		}
		
		
		
		
		//Panel Estadísticas
		stats = new JPanel();
		stats.setLayout(new FlowLayout());
		stats.setBounds(board.getRows() * CELL_SIZE + 5, 20, 100, 60);
		
		JLabel lscore = new JLabel("Score: ");
		score = new JLabel(board.getScore() + "");
		JButton restart = new JButton("Restart");
		
		
		stats.add(lscore);
		stats.add(score);
		stats.add(restart);
		stats.setBorder(new EtchedBorder());
		
		
		//Panel del tablero
		
		boardP = new BoardPanel(board.getRows(),board.getCols(),CELL_SIZE);
		boardP.setBackground(Color.WHITE);
		boardP.setBorder(new EtchedBorder());
		boardP.repaint();
		add(boardP);
		draw(board);
		System.out.println(boardP);
		
		add(stats);
		
		
		


	}
	

	
	public void playerMoved(Board board, Direction dir){
	
		Point pos = board.getPlayerPosition().moveToOpposite(dir);
		
		int strenght = ((Player)((EmptyCell)board.at(board.getPlayerPosition())).getElem()).getStrength();
		
		for(int i=0;i<strenght+2;i++){
			refreshCell(pos.getX(),pos.getY(),board);
			pos = pos.moveTo(dir);
			}
		score.setText(board.getScore() + "");
		boardP.repaint();
	
	}
	
	private void draw(Board board){
		int rows = board.getRows();
		int cols = board.getCols();
				for(int i=0;i<rows;i++){
					for(int j=0;j<cols;j++){
						refreshCell(i,j,board);
					}
				}
	}
	
	private void refreshCell(int i, int j, Board board){
		Elem elem = null;
		Cell c = board.at(i,j);
		Image img = images.get(c.getClass().getName());
		if(c instanceof Target)
			img = ImageUtils.colorize(img, ((Target)c).getColor());
		boardP.setImage(i,j,img);
			if(c instanceof EmptyCell){
				elem = ((EmptyCell)c).getElem();
				
				if(!(elem instanceof EmptyElem)){
					img = images.get(elem.getClass().getName());
					if(elem instanceof Box) {
						img = ImageUtils.colorize(img,((Box)elem).getColor());
						if(elem instanceof BombBox){
							//TODO Corregir
							img = ImageUtils.drawString(img,((BombBox)elem).getTimes() + "", Color.WHITE);
						}	
						if(((Box) elem).onTarget()){
							System.out.println("LA CAJA ESTA EN EL TARGET" + elem);
							img = ImageUtils.increaseBrightness(img);
						}
					}
					boardP.appendImage(i,j,img);
				}
			}	
	}
		
}
	
