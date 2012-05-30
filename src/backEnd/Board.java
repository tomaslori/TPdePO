package backEnd;

import java.util.LinkedList;
import java.util.List;

import javax.swing.text.Position;


public class Board {
	
	private Cell [][] board;
	private Point playerPosition;
	private List boxes= new LinkedList();
	private int remainingTargets; 
	
	
	
	public Board(int rows, int cols){
		board = new Cell[rows+1][cols+1];
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
	
	/*
	 * Mueve el objeto en la posicion indicada, en la direccion dada.
	 */
	public void move(Point position, Direction direction){
		move((EmptyCell)at(position), (EmptyCell)at(position.moveTo(direction)));
	}
	
	/*
	 * Mueve el objeto en c1 hacia c2
	 */
	private void move(EmptyCell ec1, EmptyCell ec2){
		ec2.setElem(ec1.getElem());
		ec1.setElem(new EmptyElem());
	}
	
	
	public void wMove(Direction direction){
		at(playerPosition.moveTo(direction)).moveOnIt(at(playerPosition), direction);		
	}

	
	public void putAt(int row, int col, Cell c){
		board[row][col] = c;
	}
	
	public void putAt(int row, int col, Elem elem){
		((EmptyCell)at(row,col)).setElem(elem);
	}

	
	public Cell at(Point p) {
		return this.at(p.getX(), p.getY());
	}
	
	public Cell at(int row, int col){
		return board[row][col];
	}
}
