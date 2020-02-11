package tp.p3.managers;

import java.util.Random;

import tp.p3.logic.Level;

public class ZombieManager {
	private int numZombies;
	private Random numRand;
	private Level level;
	
	public ZombieManager(Level level, Random rand) {
		this.level=level;
		this.numZombies=level.getNumberOfZombies();
		this.numRand=rand;
	}
	
	public ZombieManager(Level level, Random rand,int zombiesLeft) {
		this.level=level;
		this.numZombies=zombiesLeft;
		this.numRand=rand;
	}
	
	public boolean isZombieAdded()
	{
		float numRandom;			
		boolean added = false;
	
		if (numZombies > 0)
		{
			numRandom = numRand.nextInt(10);
			
			if (numRandom/10 <= level.getZombieFrequency())
				added = true;
		}
		return added;
	}
	
	public ZombieManager clone()
	{
		ZombieManager zombieManager=new ZombieManager(this.level,this.numRand,this.numZombies);
		return zombieManager;
	}

	public void addZombie()
	{
		--this.numZombies;
	}
	
	public int zombiesLeftToAppear()
	{
		return this.numZombies;
	}
	
	public String toString()
	{
		return Integer.toString(this.numZombies);
	}

	public void setNumZombies(int numZombies) {
		this.numZombies=numZombies;
	}
}