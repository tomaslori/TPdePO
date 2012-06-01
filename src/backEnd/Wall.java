package backEnd;

public class Wall extends EmptyCell{

	@Override
	public boolean moveOnIt(EmptyCell c, Direction direction, int might) {
		return false;
	}
	

}
