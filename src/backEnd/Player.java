package backEnd;

public class Player extends Elem{

	private int strength;
	
	public Player() {
		this(1);
	}
	
	public Player(int strength) {
		this.strength = strength;
	}
	
	
//	public boolean move(EmptyCell ec, Direction direction) {
//		return ec.moveOnIt(ec, direction, strength);
//	}


	public int getStrength() {
		return strength;
	}
	
	public void doubleDispatching(Cell cell) {
		cell.interact(this);
	}

	public void doubleDispatching(BlackHole bh){
		bh.interact(this);
	}
	
	@Override
	public boolean doubleDispatching(Box box, EmptyCell ec, Direction direction) {
		return box.interact(this, ec, direction);
	}
}
