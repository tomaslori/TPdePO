package backEnd;

import java.util.LinkedList;
import java.util.List;


public class Board {
	
	private Cell [][] board;
	private Point playerPosition;
	private List<Box> boxes= new LinkedList<Box>();
	private int remainingTargets=0;
	private int score = 0;
	private  boolean finishedGame = false;

	
	public Board(int rows, int cols) {
		this(new Cell[rows+2][cols+2]);
		
	}
	
	public Board(Cell [][] board) {
		this.board = board;
		initialize();
		Cell.setBoard(this);
	}
	
	public int getRows(){
		return this.board.length - 2;
	}
	
	public int getCols(){
		return this.board[0].length - 2;
	}
	
	
	private void initialize(){
		initializeLimits();
		for(int i=0; i < (board.length-1); i++) {
			for(int j=0; j < (board[0].length-1); j++) {
				if(pAt(i, j) == null){
					put(i, j, new EmptyCell());
				}
			}
		}
	}
	
	
	private void initializeLimits() {
		for(int j=0; j < board[0].length; j++){
			put(0, j, new Wall());
			put(board.length - 1, j, new Wall());
		}
		
		for(int i=0; i<board.length; i++) {
			put(i, 0, new Wall());
			put(i, board[0].length - 1, new Wall());
		}
	}
	
	
	public Cell calculateCell(Direction direction, int times) {
		Point p = playerPosition;
		while(times>0){
			p = p.moveTo(direction);
			times--;
		}
		return pAt(p);
	}
	
	
	public Boolean isPlayerPresent() {
		
		return (playerPosition != null);
	}
	
	
	
	
	
	/*
	 * Mueve el objeto en c1 hacia c2
	 */
	public void swap(EmptyCell ec1, EmptyCell ec2) {
		ec2.setElem(ec1.getElem());
		ec1.setElem(new EmptyElem());
	}
	
	public void swap(EmptyCell ec, BlackHole bh){
		swap(ec, new EmptyCell());
	}
	
	
	public boolean move(Direction direction) {
		boolean b = false;;
		if(!finishedGame && (b = pAt(playerPosition.moveTo(direction)).moveOnIt((EmptyCell)pAt(playerPosition), direction))){
			score++;
			playerPosition = playerPosition.moveTo(direction);
		}
		for(Box box: boxes){
			System.out.println("BOX " + box + "   OnTARGET:   " + box.onTarget());
		}
		return b;
	}
	
	
	public int getScore() {
		return score;
	}

	private void put(int row, int col, Cell cell){
		board[row][col] = cell;
	}
	
	private void put(int row, int col, Elem elem){// throws OutOfB{
		((EmptyCell)board[row][col]).setElem(elem);
	}
	
	
	public void putAt(int row, int col, Cell cell) {
		put(row+1, col+1, cell);
	}
	
	public void vPutAt(int row, int col, Cell cell) {
		/* Validate position or throw Exception */
		putAt(row, col, cell);
	}
	
	public void vPutAt(int row, int col, Target t) {
		/* Validate position or throw Exception */
		putAt(row, col, t);
		remainingTargets++;
	}
	
	public void putAt(int row, int col, Elem elem) {
		put(row+1, col+1, elem);
	}
	
	public void vPutAt(int row, int col, Elem elem) {
		//TODO /* Validate position or throw Exception */
		putAt(row, col, elem);
	}
	
	public void vPutAt(int row, int col, Player player) {
		putAt(row, col, player);
		playerPosition = new Point(row+1, col+1);
	}
	
	
	public void vPutAt(int row, int col, Box box) {
		//TODO /* Validate position or throw Exception */
		putAt(row, col, box);
		boxes.add(box);
		
	}
	
	
	public Point getPlayerPosition(){
		return new Point(playerPosition.getX()-1, playerPosition.getY()-1);
	}

	
	public Cell pAt(Point p) {
		return this.pAt(p.getX(), p.getY());
	}
	
	public Cell pAt(int row, int col) {
		return board[row][col];
	}
	
	public Cell at(Point p) {
		return this.pAt(p.getX()+1, p.getY()+1);
	}
	
	public Cell at(int row, int col) {
		return board[row+1][col+1];
	}
	
	
	
	
	public void decreaseRemainingTargets(){
		remainingTargets--;
		hasWon();
	}
	
	
	public void increaseRemainingTargets() {
		remainingTargets++;
	}
	
	
	public boolean hasWon() {
		boolean aux = (remainingTargets == 0 && checkBoxes());
		if(aux){
			System.out.println("Ganaste :)");
			finishedGame = true;
		}
		return aux;
	}

	public void hasLost(BlackHole bh) {
		System.out.println("te caiste");
		hasLost();
	}
	
	
	public void hasLost(BombBox bb) {
		System.out.println("BOOOM!");
		hasLost();
	}
	
	private void hasLost(){
		finishedGame = true;
	}

	private boolean checkBoxes() {
		for(Box b: boxes){
			if(!b.onTarget())
				return false;
		}
		return true;
	}
	


	public void deleteBox(Box box) {
		boxes.remove(box);
		hasWon();
	}
	
	
	
	
}
