package tp.p3.commands;

import tp.p3.logic.Game;
import tp.p3.logic.objects.factories.ZombieFactory;

public class ZombieListCommand extends NoParamsCommand{
	public static final String CommandText="zombielist";
	public static final String CommandTextInitial="[Z]ombielist";
	public static final String HelpText="print the text of available zombies";
	
	public ZombieListCommand()
	{
		super(CommandText, CommandTextInitial, HelpText);
	}
	
	public boolean execute (Game game)
	{
		System.out.println(ZombieFactory.listOfAvailableZombies());
		return false;
	}
}
