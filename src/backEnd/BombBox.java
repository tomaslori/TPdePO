package backEnd;

import java.awt.Color;

public class BombBox extends Box{

	private int times;
	
	public BombBox(Color color, int times) {
		super(color);
		this.times = times;
	}
	
	
	public BombBox(int r, int g, int b, int times){
		this(new Color(r,g,b), times);
	}
	
	
	@Override
	public boolean move(EmptyCell ec, Direction direction, int might){
		boolean aux = super.move(ec, direction, might);
			if(--times == 0)
				ec.alert(this);
			return aux;
	}
		
}
