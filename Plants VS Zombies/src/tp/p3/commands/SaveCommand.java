package tp.p3.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.logic.Game;
import tp.p3.utils.MyStringUtils;

public class SaveCommand extends Command {
	private static String fileName="gameSaved";
	public final static String commandInfo="[S]ave";
	public final static String helpInfo="Save the game in a file";
	private String header="Plants Vs Zombies v3.0";
	
	public SaveCommand() {
		super(fileName, commandInfo, helpInfo);
	}
	
	public SaveCommand(String message) {
		super(message,commandInfo,helpInfo);
		fileName=message;
	}
	
	public boolean execute(Game game) throws CommandExecuteException
	{
		if(MyStringUtils.isValidFilename(fileName+".dat"))
		{
			BufferedWriter bw=null;
			try {
				if(!MyStringUtils.fileExists(fileName+".dat"))
					System.out.println("Game saved in: " + fileName +".dat");
				else
					throw new CommandExecuteException("Ya existe dicho fichero, se ha sobreescrito");
				
				bw=new BufferedWriter(new FileWriter(new File(fileName+".dat")));
				bw.write(header);
				bw.newLine();
				bw.newLine();
				game.store(bw);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Command parse(String[] commands) {
		
		Command command=null;
		
		if((commands[0].equalsIgnoreCase("s"))||(commands[0].equalsIgnoreCase("save")))
		{
			if(commands.length==2)
				command=new SaveCommand(commands[1]);
			else
				command=this;
		}
		
		return command;
	}
}
