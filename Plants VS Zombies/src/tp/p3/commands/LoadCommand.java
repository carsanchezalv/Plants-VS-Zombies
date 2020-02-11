package tp.p3.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.FileContentsException;
import tp.p3.logic.Game;
import tp.p3.utils.MyStringUtils;

public class LoadCommand extends Command {
	private static String fileName="gameSaved";
	public final static String commandInfo="[L]oad";
	public final static String helpInfo="Load a game from a file";
	
	public LoadCommand() {
		super(fileName, commandInfo, helpInfo);
	}
	public LoadCommand(String message) {
		super(message, commandInfo, helpInfo);
		fileName=message;
	}
	
	public boolean execute(Game game) throws CommandExecuteException, FileContentsException
	{
		if(MyStringUtils.isValidFilename(fileName+".dat")&&(MyStringUtils.isReadable(fileName+".dat")))
		{
			try(BufferedReader br= new BufferedReader(new FileReader(fileName+".dat"))){
				String header=br.readLine();
				
				if(header.equals("Plants Vs Zombies v3.0"))
				{
					br.readLine();
					game.load(br);
					System.out.println("Juego cargado con exito desde el fichero <"+fileName+".dat>.");
				}
			
			} catch(IOException | FileContentsException ex) {
				throw new CommandExecuteException("Problema al cargar el fichero.");
			} catch(NullPointerException ex) {
				throw new CommandExecuteException("Fichero vacio.");
			}
		}
		else
			throw new CommandExecuteException("El fichero "+fileName+" no es valido.");
		
		return false;
	}

	@Override
	public Command parse(String[] commands)
	{
		Command command=null;
		
		if((commands[0].equalsIgnoreCase("lo"))||(commands[0].equalsIgnoreCase("load")))
		{
			if(commands.length==2)
				command=new LoadCommand(commands[1]);
			else
				command=this;
		}
		
		return command;
	}
}
