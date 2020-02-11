package tp.p3.commands;

import tp.p3.logic.Game;

public class HelpCommand extends NoParamsCommand // Extiende de una abstracta
{
	public final static String commandText="help";
	public final static String commandInfo="[H]elp";
	public final static String helpInfo="print this help message";
	
	public HelpCommand()
	{
		super(commandText,commandInfo,helpInfo);
	}
	
	public boolean execute(Game game)
	{
		System.out.println(game.help());
		return false;
	}
}