package tp.p3.logic;

import tp.p3.exceptions.FileContentsException;
import tp.p3.logic.printers.ReleasePrinter;

public class PlantsVsZombies {

	public static void main(String[] args) throws FileContentsException
	{
		Level level = null;
		int seed = 0;
		String message="Usage: <EASY|HARD|INSANE> [seed]";
		
		if (args.length > 0)
		{
			level = Level.parse(args[0]);
			if(level != null)
			{
				try {
					if (args.length == 2) // Si la longitud de lo introducido tiene 2 espacios
						seed = Integer.parseInt(args[1]);
					
					Game game = new Game(level, seed);
					Controller controller = new Controller(game);
					game.setGamePrinter(new ReleasePrinter(Game.NUM_ROWS,Game.NUM_COLUMNS));
					game.setCellsize(7);
					controller.run();
					
				}catch(RuntimeException ex) {
					System.out.println(message);
				}
			}
			else
				System.out.println(message);
		}
		else
			System.out.println(message);
	}
}

