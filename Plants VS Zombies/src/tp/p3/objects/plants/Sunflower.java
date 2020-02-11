package tp.p3.objects.plants;

import tp.p3.logic.objects.Plant;


public class Sunflower extends PassivePlant {
	
	public final static int COST = 20;
	public final static int CYCLES = 2;
	public final static int FREQUENCY = 0;
	public final static int DAMAGE = 0;
	public final static int RESISTANCE = 2;
	public final static String NAME = "[S]unflower";
	public final static String INITIAL = "S";
	public final static int SUNS = 10;

	public int remainingResistance = RESISTANCE;
	public int contCycles = 0;
	
	public Sunflower()
	{
		super(RESISTANCE, DAMAGE, CYCLES, NAME, INITIAL);
	}
	
	public void update()
	{
    	if(this.contCycles < CYCLES-1) 
    		++this.contCycles;
    	
    	else 
    	{
    		this.getGame().addSuncoins();
    		this.contCycles=0;
    	}
	}
		
	public void damage()
	{
    	setResistance(getResistance()-DAMAGE);
    	if(getResistance()<=0)
    		getGame().removePlant(this, getX(), getY());
    }
	
	public Plant parse (String word)
	{
		if(word.equalsIgnoreCase(getName())||word.equalsIgnoreCase(getInitial()))
			return this;
		
		else
			return null;
	}

	@Override
	public int getCost()
	{
		return COST;
	}
}
