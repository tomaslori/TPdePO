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
			interact(ec.getElem());
		return aux;
		
	}
	

	
	
	public Color getColor() {
		return color;
	}
	
	
	
	public void interact(Box b) {
		if(b.getColor() == color){
			board.decreaseRemainingTargets();
			b.setOnTarget(true);
		}
	}
	
	
	
	public void interact(Player pl){
	}
	 
	

	
	public String toString(){
		return "3";
	}
}
