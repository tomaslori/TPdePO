package backEnd;

import java.util.LinkedList;
import java.util.List;


public class Board {
	
	private EmptyCell [][] board;
	private Point playerPosition;
	private Player player;
	private List<Box> boxes= new LinkedList<Box>();
	private int remainingTargets;
	private int score = 0;
	
	
	
	public Board(int rows, int cols){
		board = new EmptyCell[rows+1][cols+1];
		initializeLimits();
	}
	
	
	private void initializeLimits(){
		for(int j=0; j<board[0].length; j++){
			putAt(0, j, new Wall());
			putAt(board.length, j, new Wall());
		}
		for(int i=1; i<board.length; i++){
			putAt(i, 0, new Wall());
			putAt(i, board[0].length, new Wall());
		}
	}
	
	
	
	public EmptyCell calculateCell(Direction direction, int times){
		int t = player.getMight() - times + 1;
		Point p = playerPosition;
		while(t>0)
			p.moveTo(direction);
		return at(p);
	}
	
	
	
	/*
	 * Mueve el objeto en la posicion indicada, en la direccion dada.
	 */
	public void move(Point position, Direction direction){
		move(at(position), at(position.moveTo(direction)));
	}
	
	
	/*
	 * Mueve el objeto en c1 hacia c2
	 */
	public void move(EmptyCell ec1, EmptyCell ec2){
		ec2.setElem(ec1.getElem());
		ec1.setElem(new EmptyElem());
	}
	
	
	public void move(Direction direction){
		if(player.move(at(playerPosition), direction))
			score++;
	}
	
	public int getScore(){
		return score;
	}

	
	public void putAt(int row, int col, EmptyCell c){
		board[row][col] = c;
	}
	
	public void putAt(int row, int col, Elem elem){
		(at(row,col)).setElem(elem);
	}

	
	public EmptyCell at(Point p) {
		return this.at(p.getX(), p.getY());
	}
	
	public EmptyCell at(int row, int col){
		return board[row][col];
	}
	
	public void hasLose(BlackHole bh){
		System.out.println("te caiste boludo");
	}
	
	public void hasLose(BombBox bb){
		System.out.println("booom!");
	}
	
	public void decreaseRemainingTargets(){
		remainingTargets--; 
	}
	
	public void increaseRemainingTargets(){
		remainingTargets++;
	}
	
	public boolean hasWon(){
		boolean aux = (remainingTargets == 0 && checkBoxes());
		if(aux)
			System.out.println("Ganaste :)");
		return aux;
	}


	private boolean checkBoxes() {
		for(Box b: boxes){
			if(!b.onTarget())
				return false;
		}
		return true;
	}
	
	
}
