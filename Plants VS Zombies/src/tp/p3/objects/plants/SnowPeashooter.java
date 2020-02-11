package tp.p3.objects.plants;

import tp.p3.logic.objects.Plant;

public class SnowPeashooter extends ShooterPlant {

	public final static int COST = 50;
	public final static int FRECUENCY = 1;
	public final static int CYCLES = 1;
	public final static int RESISTANCE = 10;
	public final static int DAMAGE = 3;
	public final static String NAME = "[SP]eashooter";
	public final static String INITIAL = "SP";
	
	public int remainingResistance = RESISTANCE;
	public int contCycles;
	
	public SnowPeashooter()
	{
		super(RESISTANCE, DAMAGE, CYCLES, NAME, INITIAL);
		contCycles = 0;
	}

	@Override
	public Plant parse(String plantName)
	{
		if (plantName.equalsIgnoreCase(getName()) || plantName.equalsIgnoreCase(getInitial()))
			return this;
		
		else
			return null;
	}

	@Override
	public int getCost()
	{
		return COST;
	}

	public boolean shoot(int x, int y)
	{
		if (this.getGame().canPlantAttack(this, x, y))
		{
			this.getGame().freeze(this, x, y);
			return true;
		}
		else
			return false;
	}

	public String toString()
	{
		return "SP"+"["+getResistance()+"]";
	}

	@Override
	protected int getContCycles()
	{
		return contCycles;
	}
}
