package tp.p3.logic.objects;

import tp.p3.lists.ZombieList;

public abstract class Zombie extends GameObject{

	public Zombie(int resistance, int damage, int speed, String name, String initial) {
		super(resistance, damage, speed, name, initial);
	}
	public void damage(int pain)
	{
		this.setResistance(this.getResistance() - pain);
    	if(getResistance() <= 0)
    	{
    		ZombieList zombieList = getGame().getZombieList();
    		zombieList.eraseZombie(this);
    	}
	}
	public void update() {
		
		if (this.getCycles() + 1 == this.getInitialCycles()) //Si le toca moverse
		{
			if (this.getGame().moveZombie(this)) //Si esta vacia la siguiente casilla
			{
				this.setY(this.getY()-1);
				this.setCycles(0);
				
				if (this.getY() == 0) // Si ha llegado a la columna 0
					getGame().setEnd(true);
			}
			attack(); // atacar
		}
		else 
			this.addCycle();
	}
	
	public void attack()
	{
		getGame().ZombieAttack(this);
	}
	
	public void freeze()
	{
		this.setCycles(this.getCycles() + 2);
	}
}
