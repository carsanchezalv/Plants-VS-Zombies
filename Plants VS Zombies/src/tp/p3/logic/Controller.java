package tp.p3.logic;

// Carlos Sánchez Álvarez

import java.util.Scanner;
import tp.p3.commands.Command;
import tp.p3.commands.CommandParser;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.exceptions.FileContentsException;


public class Controller
{
	public Game game;
	private Scanner in;
	
	public Controller(Game game)
	{
		this.game = game;
		this.in = new Scanner(System.in);
	}
	
	public void run() throws FileContentsException
	{
		boolean victory = false;
		
		while(!this.game.getEnd()&&!this.game.getExit()) 
		{	
			game.draw();
			victory = game.update();
			if(!this.game.getEnd())
			{
				System.out.println("Command > ");
				String[]words=in.nextLine().trim().split("\\s+");
				try {
					
					Command command = CommandParser.parseCommand(words); 
					
					if(command.execute(game))
					{
						game.computerAction();
						game.addCycle();
					}
				} catch (CommandParseException | CommandExecuteException ex) 
				{
					System.out.format(ex.getMessage() + " %n %n"); 
				}
			}
			else
			{
				game.draw();
				System.out.println("Game over");
				game.setNoPrintGameState();
				if(victory)
					System.out.println("Player wins!");
				
				else
					System.out.println("Computer wins!");
			}
		}
	}
}