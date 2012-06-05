package backEnd;

public class Wall extends Cell{

	@Override
	public boolean moveOnIt(EmptyCell c, Direction direction) {
		System.out.println("moveOnit WALL");
		return false;
	}
	

}
