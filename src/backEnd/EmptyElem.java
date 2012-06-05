package backEnd;

public class EmptyElem extends Elem{

	
	@Override
	public boolean interact(Elem e, EmptyCell ec, Direction direction) {
		return true;
	}

}
