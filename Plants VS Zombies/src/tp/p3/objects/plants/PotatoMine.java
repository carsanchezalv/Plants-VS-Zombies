package tp.p3.objects.plants;

import tp.p3.logic.objects.Plant;

public class PotatoMine extends ExplosivePlant {

	public final static int COST = 50;
	public final static int CYCLES = 3;
	public final static int FREQUENCY = 0;
	public final static int DAMAGE = 10;
	public final static int RESISTANCE = 15;
	public final static String NAME = "Potatomine";
	public final static String INITIAL = "PM";
	public int remainingCycles = CYCLES;
	
	public PotatoMine()
	{
		super(RESISTANCE, DAMAGE, CYCLES, NAME, INITIAL);
	}

	@Override
	public Plant parse(String word)
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

	@Override
	public void update()
	{ 	
		if(attack())
			this.getGame().removePlant(this, this.getX(),this.getY());
		else
			--remainingCycles;
	}
	private boolean attack()
	{
		boolean attacked = false;
		int row = getX();
		int column = getY();
		
		for (int i = row - 1; i <= row + 1; i++)
		{
			for (int j = column - 1; j <= column + 1; j++)
			{
				if (!(i == row && j == column))
				{
					if(remainingCycles == 0)
						attacked = this.getGame().canPlantAttack(this, i, j);			
				}
			}
		}
		return attacked;
	}
}
