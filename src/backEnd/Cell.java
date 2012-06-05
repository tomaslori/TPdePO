package backEnd;

public abstract class Cell {
	
	
	protected static Board board;
	
	public abstract boolean moveOnIt(EmptyCell ec, Direction direction);
	
	
	

//	public void interact(EmptyElem eElem){
//		System.out.println("Cell.interact:" + eElem.getClass());
//	}

//	public void doubleDispatching(Elem elem) {
//		elem.interact(this);	
//	}
	
	
	
	public static void setBoard(Board b) {
		board = b;
	}




	public void interact(Elem elem) {
	}
	
}
