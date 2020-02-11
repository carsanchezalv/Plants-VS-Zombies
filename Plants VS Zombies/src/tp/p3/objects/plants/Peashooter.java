package tp.p3.objects.plants;

import tp.p3.logic.objects.Plant;

public class Peashooter extends ShooterPlant {
	
	public final static int COST = 50;
	public final static int CYCLES = 1;
	public final static int FREQUENCY = 0;
	public final static int DAMAGE = 1;
	public final static int RESISTANCE = 3;
	public final static String NAME = "[P]eashooter";
	public final static String INITIAL = "P";
	
	public int remainingResistance=RESISTANCE;
	public int contCycles;
	
	public Peashooter()
	{
		super(RESISTANCE, DAMAGE, CYCLES, NAME, INITIAL);
		contCycles = 0;
	}

	
	public boolean shoot(int x, int y)
	{
		return this.getGame().canPlantAttack(this, x, y);	
	}
	
	public Plant parse(String word)
	{
		if(word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getInitial()))
			return this;
		
		else
			return null;
	}
	
	public String toString()
	{
		return "P"+"["+getResistance()+"]";
	}
	public int getCost()
	{
		return COST;
	}
	public int getContCycles()
	{
		return contCycles;
	}
	public int getCycles()
	{
		return CYCLES;
	}
}
