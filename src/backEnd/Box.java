package backEnd;

import java.awt.Color;

public class Box extends Elem {

	private Color color;
	private boolean onTarget;
	
	
	public Box(Color color){
		this.color=color;
	}
	
	
	@Override
	public boolean interact(EmptyCell ec, Point position, Direction direction) {
		return ec.interact(this, position, direction);
	}
		
	
	private boolean interact(Player player, Direction direction){
		return true;
	}
	
	private boolean interact(Box box, Direction direction){
		return false;
	}
	

	public Color getColor(){
		return color;
	}
	
	
	public boolean onTarget(){
		return onTarget;
	}


}
