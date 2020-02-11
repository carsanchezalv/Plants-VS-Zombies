package tp.p3.logic.objects.factories;

import java.util.Random;

import tp.p3.logic.objects.Zombie;
import tp.p3.logic.objects.zombies.Caracubo;
import tp.p3.logic.objects.zombies.Deportista;
import tp.p3.logic.objects.zombies.InfectedZombie;
import tp.p3.logic.objects.zombies.ZombieComun;

public class ZombieFactory {
	
	private static Zombie[] availableZombies= {
			new ZombieComun(), // 0
			new Caracubo(), // 1
			new Deportista(), //2
			new InfectedZombie()
	};
	
	public static Zombie getZombie()
	{
		int codeZombie = new Random().nextInt(availableZombies.length - 1);
		
		if (codeZombie == 0)
			return new ZombieComun();
		
		else if (codeZombie == 1)
			return new Caracubo();
		
		else
			return new Deportista();
	}
	
	public static String listOfAvailableZombies()
	{
		StringBuilder str=new StringBuilder();
		for(Zombie zombie:availableZombies) 
		{
			str.append(zombie.getName()+": speed "+zombie.getInitialCycles()+" Harm: "+zombie.getDamage()+" Life: "+zombie.getResistance()+ System.lineSeparator());	
		}
		return str.toString();
	}
	
	public static Zombie getZombie(String zombieName)
	{
		if (zombieName.equalsIgnoreCase("X"))
			return new Deportista();
		
		else if (zombieName.equalsIgnoreCase("Z"))
			return new ZombieComun();
		
		else if (zombieName.equalsIgnoreCase("W"))
			return new Caracubo();
		
		else if (zombieName.equalsIgnoreCase("I"))
			return new InfectedZombie();
	
		else
			return null;
	}
}
