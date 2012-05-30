package backEnd;

public class EmptyCell extends Cell{

	private Elem onIt = new EmptyElem();
	
	@Override
	public boolean moveOnIt(Cell c, Direction direction) {
		boolean aux = onIt.move(this, direction);
		return true;
	}
	
	public Elem getElem(){
		return onIt;
	}
	
	public void setElem(Elem onIt){
		this.onIt = onIt;
	}
	
}
