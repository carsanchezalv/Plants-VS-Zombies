package tp.p3.objects.plants;

import tp.p3.logic.objects.Plant;

public class Petacereza extends ExplosivePlant
{
	public final static int COST = 50;
	public final static int CYCLES = 2;
	public final static int FREQUENCY = 0;
	public final static int DAMAGE = 10;
	public final static int RESISTANCE = 2;
	public final static String NAME = "Peta[c]ereza";
	public final static String INITIAL = "C";
	public int remainingCycles = CYCLES;
	
	public Petacereza()
	{
		super(RESISTANCE, DAMAGE, CYCLES, NAME, INITIAL);
	}
	
	public Plant parse (String word)
	{
		if (word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getInitial()))
			return this;
		
		else
			return null;
	}
	
	public void update()
	{
		removeCycles(); 
		if (getRemainingCycles() == 0) 
		{
			attack(); 			
			this.getGame().removePlant(this, this.getX(), this.getY());
		}
	}

	public void removeCycles()
	{
		setRemainingCycles(this.remainingCycles - 1);
	}
	
	
	public void setRemainingCycles(int remainingCycles)
	{
		this.remainingCycles = remainingCycles;
	}
	
	public int getRemainingCycles()
	{
		return remainingCycles;
	}
	
	private void attack()
	{
		int row = getX();
		int column = getY();
		
		for (int i = row - 1; i <= row + 1; ++i)
		{
			for (int j = column - 1; j <= column + 1; ++j)
			{
				if (!(i == row && j == column))
					this.getGame().canPlantAttack(this, i, j);
			}
		}
	}	
	
	@Override
	public int getCost()
	{
		return COST;
	}
}