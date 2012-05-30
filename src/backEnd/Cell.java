package backEnd;

public abstract class Cell {
	
	protected static Board board;
	
	public abstract boolean moveOnIt(Cell c, Direction direction);
	
}
