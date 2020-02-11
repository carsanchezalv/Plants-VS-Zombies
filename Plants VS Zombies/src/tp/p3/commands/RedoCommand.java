package tp.p3.commands;

import tp.p3.logic.Game;

public class RedoCommand extends NoParamsCommand
{
	private static String commandText = "ORedo";
	public final static String commandInfo = "[R]edo";
	public final static String helpInfo = "Redo action";
	
	public RedoCommand()
	{
		super(commandText, commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game)
	{
		game.redo();
		return false;
	}
}
