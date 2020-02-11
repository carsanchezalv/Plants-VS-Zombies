package tp.p3.objects.plants;

import tp.p3.logic.Game;
import tp.p3.logic.objects.Plant;

public abstract class ShooterPlant extends Plant {

	public ShooterPlant(int resistance, int damage, int cycles, String name, String initial)
	{
		super(resistance, damage, cycles, name, initial);
	}
	
	public void attack()
	{
		boolean attacked = false;
		int x = this.getX();
		int y = this.getY();
		
		while((!attacked) && (y < Game.NUM_COLUMNS))
		{
			attacked = this.shoot(x, y);		
			if(!attacked)
				++y;
		}
	}
	
	protected abstract boolean shoot(int x, int y);
	
	public void update()
	{
		if (this.getContCycles() == this.getCycles() - 1)
		{
			this.setCycles(0);
			attack();
		}
		else
		{
			int ciclos = this.getContCycles() + 1;
			this.setCycles(ciclos);
		}
	}
	protected abstract int getContCycles();
}