package backEnd;

public class EmptyElem extends Elem{

	
	@Override
	public boolean interact(EmptyCell ec, Direction direction) {
		return true;
	}
	
}
