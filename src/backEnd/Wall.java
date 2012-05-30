package backEnd;

public class Wall extends Cell{

	@Override
	public boolean moveOnIt(Cell c, Direction direction) {
		return false;
	}
	

}
