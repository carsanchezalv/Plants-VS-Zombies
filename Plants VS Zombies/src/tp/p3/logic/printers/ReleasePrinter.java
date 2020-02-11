package tp.p3.logic.printers;

import tp.p3.logic.Game;

public class ReleasePrinter extends BoardPrinter{

	public final static int CELL_SIZE=7;
	public ReleasePrinter(int dimX, int dimY)
	{
		super(dimX,dimY);
	}
	public void encodeGame(Game game)
	{
		System.out.print(game.getCycleInfo());
		board = new String[dimX][dimY];
		
		for(int i = 0; i < dimX; i++)
		{
			for(int j = 0; j < dimY; j++)
			{
				board[i][j] =  space;
			}
		}
		
		for(int i=0;i<game.plantListSize();++i)
		{
			int x=game.getPlantX(i);
			int y=game.getPlantY(i);
			board[x][y]=game.getPlantToString(i);
		}
		
		for(int i=0;i<game.zombieListSize();++i)
		{
			int x=game.getZombieX(i);
			int y=game.getZombieY(i);
			board[x][y]=game.getZombieToString(i);
		}		
	}
	@Override
	public String printGame(Game game) {
		encodeGame(game);
		return boardToString(CELL_SIZE);
	}
}
