package backEnd;

public abstract class Elem {
	
	public abstract boolean interact(EmptyCell ec, Direction direction);

	public boolean interact(Elem e, Point position, Direction direction){
		return e.interact(this, position, direction);
	}

}
