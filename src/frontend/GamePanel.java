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
	private static final int CELL_SIZE = 30;
	private HashMap<String, Image> images = new HashMap<String, Image>(); 
	private BoardPanel boardP;
	
	
	
	public GamePanel(final Board board){
		
		//TODO clonar tablero
		
		setLayout(null);
		setSize(board.getRows() * CELL_SIZE + 40, board.getCols() * CELL_SIZE + 40);
		setBorder(new EtchedBorder());
		
		System.out.println("Rows" + board.getRows() + "Cols" + board.getCols());
		
		
		
		try{
			images.put("backEnd.Box",ImageUtils.loadImage("resources/box.png"));
			images.put("backEnd.BombBox",ImageUtils.loadImage("resources/box2.png"));
			images.put("backEnd.BlackHole",ImageUtils.loadImage("resources/hole.png"));
			images.put("backEnd.Player",ImageUtils.loadImage("resources/smile.gif"));
			images.put("backEnd.Target",ImageUtils.loadImage("resources/target.png"));
			images.put("backEnd.Wall",ImageUtils.loadImage("resources/wall.jpg"));
		} 
		catch (IOException e) {
			System.out.println("Error al cargar imagenes.");
		}
		
		
		
		
		//Panel Estadísticas
		JPanel stats = new JPanel();
		stats.setLayout(new FlowLayout());
		
		JLabel lscore = new JLabel("Score: " + board.getScore());
		JButton restart = new JButton("Restart");
		
		
		stats.add(lscore);
		stats.add(restart);
		stats.setBorder(new EtchedBorder());
		
		
		//Panel del tablero
		
		boardP = new BoardPanel(board.getRows(),board.getCols(),CELL_SIZE);
		boardP.setBackground(Color.WHITE);
		boardP.setBorder(new EtchedBorder());
		boardP.repaint();
		add(boardP);
		draw(board, boardP);
		System.out.println(boardP);
		
		add(stats);
		
		
		


	}
	public void playerMoved(Board board, Direction dir){
		Image img;
		Elem elem = null;
		Point pos = board.getPlayerPosition();
		int strenght = ((Player)((EmptyCell)board.at(pos)).getElem()).getStrength();
		for(int i=0;i<strenght;i++){
			int x = pos.getX();
			int y = pos.getY();
			
			Cell c = board.at(pos);
			img = images.get(c.getClass().getName());
			
			this.boardP.setImage(x,y,img);
			if(c instanceof EmptyCell){
				elem = ((EmptyCell)c).getElem();
				if(!(elem instanceof EmptyElem)){
					img = images.get(elem.getClass().getName());
					if(elem instanceof Box) {
							img = ImageUtils.colorize(img,((Box)elem).getColor());
							if(elem instanceof BombBox){
							//	TODO cambiar esto
								img = ImageUtils.drawString(img,((BombBox)elem).getTimes() + "" , Color.WHITE);
							}
							if(((Box) elem).onTarget()){
								img = ImageUtils.increaseBrightness(img);
							}
						}
					this.boardP.appendImage(x,y,img);
					
					
					}
				pos = pos.moveTo(dir);
			}
		}
	}
	
	private void draw(Board board, BoardPanel boardP){
		int rows = board.getRows();
		int cols = board.getCols();
		Elem elem = null;

		
				for(int i=0;i<rows;i++){
					for(int j=0;j<cols;j++){
						Cell c = board.at(i,j);
							 
						Image img = images.get(c.getClass().getName());
						
						
						if(c instanceof Target)
							img = ImageUtils.colorize(img, ((Target)c).getColor());
							
						boardP.setImage(i,j,img);
						
						System.out.println(i+","+j);
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
											img = ImageUtils.increaseBrightness(img);
										}
									}
									boardP.appendImage(i,j,img);
								}
							}	
					}

				}
	}	
		
}
	
