package tp.p3.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.factories.PlantFactory;

public class AddCommand extends Command {
	public static final String commandInfo = "[A]dd";
	public static final String helpInfo = "add flower: ";
	private Plant plant;
	String plantName;
	int x, y;
	
	public AddCommand()
	{
		super("", commandInfo, helpInfo);
	}
	public AddCommand(String commandText, String commandInfo, String helpInfo)
	{
		super(commandText,commandInfo,helpInfo);
	}

	public boolean execute(Game game) throws CommandParseException, CommandExecuteException
	{	
		plant = PlantFactory.getPlant(this.plantName);
		if(plant != null)
		{
			plant.setX(this.x);
			plant.setY(this.y);
		}
		else
			throw new CommandParseException("Planta " + plantName + " desconocida.");
	
		plant.setGame(game);
		game.addPlant(x, y, plant);
		return true;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException
	{ 
		Command command=null;
		if((commandWords[0].equalsIgnoreCase("a"))||(commandWords[0].equalsIgnoreCase("add")))
		{
			if(commandWords.length == 4)
			{
				try {
					plantName=commandWords[1];
					x=Integer.parseInt(commandWords[2]);
					y=Integer.parseInt(commandWords[3]);
					command=this;
				}catch(NumberFormatException ex)
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
