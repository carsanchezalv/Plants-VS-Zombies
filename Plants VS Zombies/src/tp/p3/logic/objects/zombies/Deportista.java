package tp.p3.logic.objects.zombies;

import tp.p3.logic.objects.Zombie;

public class Deportista extends Zombie{
	public final static int SPEED=1;
	public final static int CYCLES=1;
	public final static int RESISTANCE=2;
	public final static int DAMAGE=1;
	public final static String NAME="Deportista";
	public final static String INITIAL="X";
	
	public Deportista() {
		super(RESISTANCE, DAMAGE, SPEED, NAME, INITIAL);
		// TODO Auto-generated constructor stub
	}
}