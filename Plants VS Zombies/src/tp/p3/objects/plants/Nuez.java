package tp.p3.objects.plants;

import tp.p3.logic.objects.Plant;

public class Nuez extends PassivePlant {
	
	public final static int COST=50;
	public final static int CYCLES=1;
	public final static int FREQUENCY=0;
	public final static int DAMAGE=0;
	public final static int RESISTANCE=10;
	public final static String NAME="[N]uez";
	public final static String INITIAL="N";

	public Nuez()
	{
		super(RESISTANCE, DAMAGE, CYCLES, NAME, INITIAL);
	}
	
	public void update()
	{
		removeCycles(); 
		setCycles(getInitialCycles());
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
