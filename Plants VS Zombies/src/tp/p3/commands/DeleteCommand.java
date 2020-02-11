package tp.p3.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.exceptions.FileContentsException;
import tp.p3.logic.Game;
import tp.p3.logic.objects.Plant;


public class DeleteCommand extends Command {
	
	public static final String commandInfo = "[A]dd";
	public static final String helpInfo = "add flower: ";
	int x, y;
	
	public DeleteCommand()
	{
		super("", commandInfo, helpInfo);
	}
	
	public DeleteCommand(String commandText, String commandInfo, String helpInfo)
	{
		super(commandText, commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException, FileContentsException, CommandParseException
	{
		Plant plant = null;
		for(int i = 0; i < game.plantListSize(); ++i)
		{
			int row = game.getPlantX(i);
			int column = game.getPlantY(i);
			
			if((x == row) && (y == column))
			{
				plant = game.getPlantList().getPlant(row, column);
				game.erasePlant(plant);
				
			}
		}
		if(plant == null)
			throw new CommandParseException("Posicion sin planta.");
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException
	{
		Command command = null;
		if((commandWords[0].equalsIgnoreCase("d")) || (commandWords[0].equalsIgnoreCase("delete")))
		{
			if(commandWords.length == 3)
			{
				try {
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
					command=this;
				} catch(NumberFormatException ex)
				{
					throw new CommandParseException("Las coordenadas tienen que ser numeros.");
				}
			}
			else
				throw new CommandParseException("Insuficientes parametros");
		}		
		return command;
	}
}
