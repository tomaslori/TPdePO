package backEnd;

import java.awt.Color;

public class Box extends Elem {

	
	
	private Color color;
	private boolean onTarget;
	
	
	public Box(int r, int g, int b){
		this(new Color(r,g,b));
	}
	
	
	public Box(Color color){
		this.color = color;
	}
	
	
	
	@Override
	public boolean move(EmptyCell ec, Direction direction, int might) {
		if(might <= 0)
			return false;
		return ec.keepMoving(ec, direction, might-1);
	}
		
	
	
//	public boolean interact(Player player, EmptyCell ec, Direction direction, int might){
//		
//	}
//	
//	
//	public boolean interact(Box box, EmptyCell ec, Direction direction, int might){
//		
//	}
	

	public Color getColor(){
		return color;
	}
	
	
	public boolean onTarget(){
		return onTarget;
	}


}
