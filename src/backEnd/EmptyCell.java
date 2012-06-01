package backEnd;

public class EmptyCell{

	protected static Board board;
	protected Elem onIt = new EmptyElem();
	
	
	public boolean moveOnIt(EmptyCell ec, Direction direction, int might) {
		boolean aux = onIt.move(this, direction, might);
		if(aux){
			interact(ec.getElem());
			board.move(ec, board.calculateCell(direction, might));
		}
		return aux;
		
	}
	
	public Elem getElem(){
		return onIt;
	}
	
	public void setElem(Elem onIt){
		this.onIt = onIt;
	}
	
	public static void setBoard(Board b){
		board = b;
	}
	
	public void interact(Elem e) {
		e.interact(this);
	}
	
	public void interact(Box b){
		if(b.onTarget())
			board.decreaseRemainingTargets();			
	}
	
	public void interact(Player player){			
	}

	public boolean keepMoving(EmptyCell ec, Direction direction, int might) {
		return moveOnIt(board.calculateCell(direction, might), direction, might);
	}

	public void alert(BombBox bb) {
		board.hasWon();
		board.hasLose(bb);
	}
	
	
}
