package tp.p3.commands;

import tp.p3.exceptions.CommandParseException;

public class CommandParser {
	
	private static Command[] availableCommands= {
		new AddCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new ListCommand(),
		new ZombieListCommand(),
		new PrintModeCommand(),
		new NoneCommand(),
		new SaveCommand(),
		new LoadCommand(),
		new DeleteCommand(),
		new UndoCommand(),
		new RedoCommand()
	};
	public static Command parseCommand(String[]commands) throws CommandParseException
	{
		Command command;
		for(Command c:availableCommands) 
		{
			command=c.parse(commands);
			if(command!=null)
				return command;
		}
		throw new CommandParseException();
	}
	public static String commandHelp()
	{
		StringBuilder str=new StringBuilder();
		for(Command c:availableCommands)
		{
			str.append(c.commandHelp() + System.lineSeparator());
		}
		return str.toString();
	}
}
