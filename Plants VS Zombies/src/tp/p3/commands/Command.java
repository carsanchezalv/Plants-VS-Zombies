package tp.p3.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.exceptions.FileContentsException;
import tp.p3.logic.Game;

public abstract class Command {
	
	private String helpText;
	private String commandText;
	protected final String commandName;

	public Command(String commandText,String commandInfo,String helpInfo)
	{
		this.commandText=commandInfo;
		this.helpText=helpInfo;
		String[]commandWords=commandText.split("\\s+"); 
		this.commandName=commandWords[0];
	}
	
	public abstract boolean execute (Game game) throws CommandExecuteException, FileContentsException, CommandParseException;
	public abstract Command parse (String[] commands) throws CommandParseException;
	
	public String commandHelp()
	{		
		return " "+commandText+": "+helpText;
	}
}