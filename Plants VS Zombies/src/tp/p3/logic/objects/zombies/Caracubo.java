package tp.p3.logic.objects.zombies;


import tp.p3.logic.objects.Zombie;

public class Caracubo extends Zombie {
	
	public final static int SPEED=4;
	public final static int CYCLES=1;
	public final static int RESISTANCE=8;
	public final static int DAMAGE=1;
	public final static String NAME="Caracubo";
	public final static String INITIAL="W";
	
	public Caracubo()
	{
		super(RESISTANCE, DAMAGE, SPEED, NAME, INITIAL);
	}
}
