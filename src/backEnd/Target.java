package backEnd;

import java.awt.Color;


public class Target extends EmptyCell {
	
	private Color color;
	
	public Target(Color color) {
		this.color = color;
	}

	
	@Override
	public boolean moveOnIt(EmptyCell ec, Direction direction) {
		boolean aux = super.moveOnIt(ec, direction);
		if(aux)
			interact((Box)onIt);
		return aux;
		
	}
	

	
	
	public Color getColor() {
		return color;
	}
	
	
	@Override
	public void interact(Box b) {
		if(b.getColor().equals(color)){
			b.setOnTarget(true);
			board.decreaseRemainingTargets();
		}
	}	

	
	public String toString(){
		return "3";
	}
}
