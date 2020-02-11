package tp.p3.logic.objects.zombies;

import tp.p3.logic.objects.Zombie;

public class ZombieComun extends Zombie {

	public final static int SPEED = 2;
	public final static int CYCLES = 1;
	public final static int RESISTANCE = 5;
	public final static int DAMAGE = 1;
	public final static String NAME = "ZombieComun";
	public final static String INITIAL = "Z";
	
	public ZombieComun() {
		super(RESISTANCE, DAMAGE, SPEED, NAME, INITIAL);
	}
}
