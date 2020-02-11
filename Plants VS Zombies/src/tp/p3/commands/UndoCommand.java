package tp.p3.commands;

import tp.p3.logic.Game;

public class UndoCommand extends NoParamsCommand {
	
	private static String commandText = "Undo";
	public final static String commandInfo = "[U]ndo";
	public final static String helpInfo = "Undo action";
	
	public UndoCommand()
	{
		super(commandText, commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game)
	{
		game.undo();
		return false;
	}
}
