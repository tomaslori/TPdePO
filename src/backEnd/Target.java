package backEnd;

import java.awt.Color;

public class Target extends EmptyCell{
	
	private Color color;
	
	public Target(Color color){
		this.color = color;
	}

	
	public boolean moveOnIt(EmptyCell c, Direction direction){
		Troolean tr = super.moveOnIt(c, direction);
		if(tr.getBoolean())
			setReturnValue((MyTrue)tr, c.getElem());
		return tr;
	}
	
	@SuppressWarnings("unused")
	private void setReturnValue(MyTrue t, Box b){
		if(b.getColor().equals(color))
			t.setValue(1);
	}
	
	private void setReturnValue(MyTrue t, Elem e){
	}
	
	
	public Color getColor(){
		return color;
	}
	 
}
