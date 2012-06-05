package backEnd;

public class BlackHole extends Cell {
	
	public void setElem(Elem e) {
	}

	@Override
	public boolean moveOnIt(EmptyCell ec, Direction direction) {
		ec.getElem().doubleDispatching(this);
		board.swap(ec, this);
		return true;
	}
	
	public void interact(Player player) {
		board.hasLost(this);
	}
	
	public void interact(Box box){
		board.deleteBox(box);
		System.out.println("fasfnmaosfoi");
	}
	

	
	public String toString(){
		return "5";
	}

	@Override
	public void interact(Elem elem) {
		// TODO Auto-generated method stub
		
	}
	

}
