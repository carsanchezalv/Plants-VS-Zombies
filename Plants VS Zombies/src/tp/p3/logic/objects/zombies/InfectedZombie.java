package tp.p3.logic.objects.zombies;

import tp.p3.logic.Game;
import tp.p3.logic.objects.Zombie;

public class InfectedZombie extends Zombie {
	
	public final static int SPEED = 2;
	public final static int CYCLES = 1;
	public final static int RESISTANCE = 5;
	public final static int DAMAGE = 1;
	public final static String NAME = "InfectedZombie";
	public final static String INITIAL = "I";
	
	public InfectedZombie()
	{
		super(RESISTANCE, DAMAGE, SPEED, NAME, INITIAL);
	}
	
	public void update()
	{
		if (this.getCycles() + 1 == this.getInitialCycles()) //Si le toca moverse
		{
			if (this.getGame().moveInfected(this)) //Si esta vacia la siguiente casilla
			{
				this.setY(this.getY() + 1);
				this.setCycles(0);
				
				if (this.getY() >= Game.NUM_COLUMNS) // Si ha llegado a la columna máxima
					this.getGame().removeZombie(this, getX(), getY());
			}
			attack(); // atacar
		}
		else 
			this.addCycle();
	}
	
	public void attack()
	{
		getGame().InfectedAttack(this);
	}
}
