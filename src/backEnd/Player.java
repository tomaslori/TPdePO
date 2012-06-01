package backEnd;

public class Player extends Elem{

	private int might;
	
	public Player(){
		this(1);
	}
	
	public Player(int might){
		this.might = might;
	}
	
	
	public boolean move(EmptyCell ec, Direction direction){
		return ec.moveOnIt(ec, direction, might);
	}


	public int getMight() {
		return might;
	}

}
