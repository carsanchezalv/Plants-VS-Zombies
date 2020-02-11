package tp.p3.logic.objects.factories;

import tp.p3.logic.objects.Plant;
import tp.p3.objects.plants.HypnoShroom;
import tp.p3.objects.plants.Nuez;
import tp.p3.objects.plants.Peashooter;
import tp.p3.objects.plants.Petacereza;
import tp.p3.objects.plants.PotatoMine;
import tp.p3.objects.plants.SnowPeashooter;
import tp.p3.objects.plants.Sunflower;

public class PlantFactory {
	private static Plant[] availablePlants = {
			new Sunflower(),
			new Peashooter(),
			new Nuez(),
			new Petacereza(),
			new SnowPeashooter(),
			new HypnoShroom(),
			new PotatoMine()
	};

	public static Plant getPlant(String plantName)
	{
		if (plantName.equalsIgnoreCase("sunflower")||(plantName.equalsIgnoreCase("s")))
			return new Sunflower();
		
		else if (plantName.equalsIgnoreCase("peashooter")||(plantName.equalsIgnoreCase("p")))
			return new Peashooter();
		
		else if (plantName.equalsIgnoreCase("nuez")||(plantName.equalsIgnoreCase("n")))
			return new Nuez();
		
		else if (plantName.equalsIgnoreCase("petacereza")||(plantName.equalsIgnoreCase("c")))
			return new Petacereza();
		
		else if (plantName.equalsIgnoreCase("snowpeashooter")||(plantName.equalsIgnoreCase("sp")))
			return new SnowPeashooter();
		
		else if (plantName.equalsIgnoreCase("hypnoshroom") || (plantName.equalsIgnoreCase("h")))
			return new HypnoShroom();
		
		else if (plantName.equalsIgnoreCase("potatomine")|| (plantName.equalsIgnoreCase("pm")))
			return new PotatoMine();
		
		else
			return null;
	}
	public static String listOfAvailablePlants()
	{
		StringBuilder str = new StringBuilder();
		
		for(Plant plant:availablePlants)
		{
			str.append(plant.getName()+": Cost "+plant.getCost()+" suncoins  "+" Harm "+plant.getDamage()+System.lineSeparator());
		}
		return str.toString();
	}
}