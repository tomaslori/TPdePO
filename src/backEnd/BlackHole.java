package backEnd;

public class BlackHole extends EmptyCell{
	
	public void setElem(Elem e){
	}

	@Override
	public boolean moveOnIt(EmptyCell ec, Direction direction, int might) {
		interact(ec.getElem());
		board.move(ec, board.calculateCell(direction, might));
		return true;
	}
	
	
	public void interact(Player player){
		board.hasLose(this);
	}
	

	

}
