package tp.p3.logic.objects;

public abstract class Plant extends GameObject {
	
	public int cost;
	public int frequency;
	
	public Plant(int resistance, int damage, int cycles, String name, String initial)
	{
		super(resistance, damage, cycles, name, initial);
	}
	
	public abstract Plant parse(String plantName);
	
	public void damage(int pain)
	{
		setResistance(getResistance() - pain);	
    	if (getResistance() <= 0)
    		getGame().removePlant(this, getX(), getY());
	}
	
	public abstract int getCost();
}