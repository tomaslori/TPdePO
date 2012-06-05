package backEnd;

public abstract class Elem {
	
	public boolean interact(Elem e, EmptyCell ec, Direction direction) {
		return e.interact(this, ec, direction);
	}
	
	
	public boolean move(EmptyCell ec, Direction direction) {
		return true;
	}
	


	
	public void doubleDispatching(EmptyCell cell) {
		cell.interact(this);
	}
	
	public void doubleDispatching(BlackHole bh){
		bh.interact(this);
	}

	
	
//TODO VER!!!
	public boolean doubleDispatching(Box box, EmptyCell ec, Direction direction) {
		return false;
	}
	
}
