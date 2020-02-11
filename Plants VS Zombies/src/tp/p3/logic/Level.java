package tp.p3.logic;

import java.util.Scanner;

public enum Level {
	
	EASY(3, 0.1), HARD(5, 0.2), INSANE(10, 0.3), BLITZ(3, 0.1), CUSTOM();
	private int numberOfZombies;
	private double zombieFrequency;
	
	private Level(int zombieNum, double zombieFreq)
	{
		numberOfZombies = zombieNum;
		zombieFrequency = zombieFreq;
	}
	
	private Level()
	{
		numberOfZombies = 1;
		zombieFrequency = 0;
	}
	
	public int getNumberOfZombies()
	{
		return numberOfZombies;
	}
	
	public double getZombieFrequency()
	{
		return zombieFrequency;
	}
	
	public static Level nextLevel(Level level)
	{
		Level lvl = null;
		if(level.name().equals("BLITZ"))
		{
			lvl = level;
			System.out.println("Antes " + lvl.numberOfZombies);
			lvl.numberOfZombies *= 2;
			System.out.println("Despues " + lvl.numberOfZombies);
			lvl.zombieFrequency += 1;
			
			if(lvl.numberOfZombies > 12)
				return null;
		}
		return lvl;
	}
	
	public static Level parse(String inputString)
	{
		for (Level level : Level. values())
		{
			if (level . name().equalsIgnoreCase(inputString))
				return level;
			else if(inputString.equalsIgnoreCase("CUSTOM"))
				return createLevel();
		}
		return null;
	}
	
	private static Level createLevel()
	{
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		System.out.println("Numero de zombies: ");
		int numZombies = in.nextInt();
		
		System.out.println("Frecuencia: ");
		float frecuencia = in.nextFloat();
	
		Level level = Level.CUSTOM;
		level.numberOfZombies = numZombies;
		level.zombieFrequency = frecuencia;
		return level;
	}
	
	public static String all (String separator)
	{
		StringBuilder sb = new StringBuilder();
		for (Level level : Level. values() )
			sb. append(level.name() + separator);
		String allLevels = sb.toString();
		return allLevels.substring(0, allLevels . length()-separator.length());
	}
}