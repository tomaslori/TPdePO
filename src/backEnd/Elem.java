package backEnd;

public abstract class Elem {
	
	public boolean interact(Elem e, EmptyCell ec, Direction direction, int might){
		return e.interact(this, ec, direction, might);
	}
	
	public void interact(EmptyCell ec){
		ec.interact(this);
	}

	public boolean move(EmptyCell ec, Direction direction, int might){
		return true;
	}
	
}
