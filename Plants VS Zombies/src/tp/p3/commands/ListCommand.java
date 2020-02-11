package tp.p3.commands;

import tp.p3.logic.Game;
import tp.p3.logic.objects.factories.PlantFactory;

public class ListCommand extends NoParamsCommand{
	public static final String CommandText="list";
	public static final String CommandTextInitial="[L]ist";
	public static final String HelpText="print the text of available plants";
	
	public ListCommand()
	{
		super(CommandText, CommandTextInitial, HelpText);
	}
	
	public boolean execute (Game game)
	{
		System.out.println(PlantFactory.listOfAvailablePlants());
		return false;
	}
}
