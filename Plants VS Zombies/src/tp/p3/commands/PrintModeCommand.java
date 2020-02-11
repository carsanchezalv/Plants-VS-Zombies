package tp.p3.commands;

import tp.p3.logic.Game;
import tp.p3.logic.printers.DebugPrinter;
import tp.p3.logic.printers.ReleasePrinter;

public class PrintModeCommand extends Command{
	public static final String CommandText="printmode";
	public static final String CommandTextInitial="[P]rintMode";
	public static final String HelpText="change print mode [Release|Debug].";
	public String mode;
	
	public PrintModeCommand() {
		super(CommandText, CommandTextInitial, HelpText);
	}

	@Override
	public boolean execute(Game game) {
			
		if(mode.equalsIgnoreCase("debug")||mode.equalsIgnoreCase("d"))
		{
			int columns=game.getNumObjects();
			game.setGamePrinter(new DebugPrinter(1,columns));
			game.setCellsize(17);
		}
		else if(mode.equalsIgnoreCase("release")||mode.equalsIgnoreCase("r"))
		{
			game.setGamePrinter(new ReleasePrinter(Game.NUM_ROWS,Game.NUM_COLUMNS));
			game.setCellsize(7);
		}
		else
		{
			System.out.println("Invalid print mode");
		}
		return false;
	}

	@Override
	public Command parse(String[] commands) {
		if((commands.length==2)&&((commands[0].equalsIgnoreCase("printMode"))||(commands[0].equalsIgnoreCase("p"))))
		{
			mode=commands[1];
			return this;
		}
		else
			return null;
	}
}
