package backEnd;

public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y){
		this.x=x;
		this.y=y;
	}
	public Point(Point p){
		this.x=p.x;
		this.y=p.y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Point moveTo(Direction direction){
		return new Point(x + direction.getPoint().x, y + direction.getPoint().y);
	}
	
	public void setPosition(Point p){
		x=p.x;
		y=p.y;
	}

}
