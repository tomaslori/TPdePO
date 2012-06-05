package backEnd;

import java.awt.Color;

public class Box extends Elem {

	
	
	private Color color;
	private boolean onTarget = false;
	
	
	public Box(int r, int g, int b) {
		this(new Color(r,g,b));
	}
	
	
	public Box(Color color) {
		this.color = color;
	}
	
	
	
	@Override
	public boolean move(EmptyCell ec, Direction direction) {
		boolean b=ec.getElem().doubleDispatching(this, ec, direction);
		return b;
	}
		
	
	
	public boolean interact(Player player, EmptyCell ec, Direction direction){
		return ec.keepMoving(ec, direction);
	}
	
	
	public boolean interact(Box box, EmptyCell ec, Direction direction){
		return false;
	}
	

	public Color getColor() {
		return color;
	}
	
	
	public boolean onTarget() {
		return onTarget;
	}

	public void setOnTarget(boolean b){
		onTarget = b;
		System.out.println("BOX ONTARGET: " + onTarget);
	}


	
	public String toString(){
		return "2";
	}
	
	public void doubleDispatching(Cell cell) {
		cell.interact(this);
	}
	
	public void doubleDispatching(BlackHole bh){
		bh.interact(this);
	}
	
	public void doubleDispatching(Target target){
		target.interact(this);
	}
	
	@Override
	public boolean doubleDispatching(Box box, EmptyCell ec, Direction direction){
		return box.interact(this, ec, direction);
	}
	
}
