package backEnd;

public class EmptyCell extends Cell{

	
	protected Elem onIt = new EmptyElem();
	
	
	public boolean moveOnIt(EmptyCell ec, Direction direction) {
		boolean aux = onIt.move(ec, direction);
		if(aux){
			interact(ec.getElem());
			board.swap(ec, this);			
		}
		return aux;
	}
	
	public Elem getElem() {
		return onIt;
	}
	
	public void setElem(Elem onIt) {
		this.onIt = onIt;
	}
	
	
	
	
	public void interact(Box b) {
		if(b.onTarget())
			board.decreaseRemainingTargets();
			b.setOnTarget(false);
	}
	
	
	public void interact(Player player) {			
	}

	public boolean keepMoving(EmptyCell ec, Direction direction) {
		return board.calculateCell(direction, 2).moveOnIt((EmptyCell)board.calculateCell(direction, 1), direction);
	}

	public void alert(BombBox bb) {
		board.hasWon();
		board.hasLost(bb);
	}
	
	
//	public void doubleDispatching(Elem elem) {
//		elem.interact(this);
//	}
}
