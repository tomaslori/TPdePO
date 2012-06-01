package backEnd;

import java.awt.Color;


public class Target extends EmptyCell{
	
	private Color color;
	
	public Target(Color color){
		this.color = color;
	}

	
	@Override
	public boolean moveOnIt(EmptyCell ec, Direction direction, int might) {
		boolean aux = super.moveOnIt(ec, direction, might);
		if(aux)
			interact(this.getElem());
		return aux;
		
	}
	

	
	
	public Color getColor(){
		return color;
	}
	
	public void interact(Box b){
		if(b.getColor() == color)
			board.decreaseRemainingTargets();
	}
	 
}
