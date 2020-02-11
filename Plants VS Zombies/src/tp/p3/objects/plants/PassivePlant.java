package tp.p3.objects.plants;

import tp.p3.logic.objects.Plant;

public abstract class PassivePlant extends Plant {

	public PassivePlant(int resistance, int damage, int cycles, String name, String initial)
	{
		super(resistance, damage, cycles, name, initial);
	}
}
