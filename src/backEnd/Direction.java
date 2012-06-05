package backEnd;

public enum Direction {
	UP(-1,0), DOWN(1,0), RIGHT(0,1), LEFT(0,-1);

	private Point p;
	
	Direction(int x, int y) {
		p = new Point(x,y);
	}
	
	public Point getPoint() {
		return p;
	}	
	
}
