package tp.p3.objects.plants;

import tp.p3.logic.objects.Plant;

public class HypnoShroom extends PassivePlant {

	public final static int COST = 50;
	public final static int RESISTANCE = 1;
	
	public final static String NAME = "[H]ypnoShroom";
	public final static String INITIAL = "H";
	
	public HypnoShroom()
	{
		super(RESISTANCE, 0, COST, NAME, INITIAL);
	}
	
	public Plant parse(String plantName)
	{
		if (plantName.equalsIgnoreCase(getName()) || plantName.equalsIgnoreCase(getInitial()))
			return this;
		
		else
			return null;
	}
	
	public int getCost()
	{
		return COST;
	}
	
	public void update()
	{
		
	}
}
