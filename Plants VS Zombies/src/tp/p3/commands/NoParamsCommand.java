package tp.p3.commands;

import tp.p3.logic.Game;

public abstract class NoParamsCommand extends Command{

	private static Command[] availableCommands =
	{
		new ExitCommand(),
		new ListCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new NoneCommand(),
		new ZombieListCommand(),
		new UndoCommand(),
		new RedoCommand()
	};
	public NoParamsCommand(String commandtext, String commandinfo, String helpinfo) {
		super(commandtext, commandinfo, helpinfo);
	}

	@Override
	public abstract boolean execute(Game game);

	@Override
	public Command parse(String[] commands)
	{
		Command command=null;
		if(commands.length==1)
		{
			for(Command c: availableCommands)
			{
				if(commands[0].length()==0)
					command=new NoneCommand();
				
				else if(c.commandName.equalsIgnoreCase(commands[0])||Character.toUpperCase(c.commandName.charAt(0))==Character.toUpperCase(commands[0].charAt(0)))
					command=c;
			}
		}
		return command;
	}
}