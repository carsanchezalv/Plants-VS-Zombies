package tp.p3.logic.printers;

import tp.p3.logic.Game;

public class DebugPrinter extends BoardPrinter{
	private int cont;
	public final static int CELL_SIZE=23;
	
	public DebugPrinter(int dimX, int dimY) {
		super(dimX, dimY);
	}

	@Override
	public void encodeGame(Game game) {
		dimY=game.getNumObjects();
		
		board=new String[dimX][dimY];
		System.out.print(game.debugInfoToString());	
		cont=0;
		
		for(int i=0;i<game.plantListSize();++i)
		{
			board[0][cont]=game.getPlantInfo(i);
			this.cont++;
		}
		
		for(int i=0;i<game.zombieListSize();++i)
		{
			board[0][cont]=game.getZombieInfo(i);
			this.cont++;
		}	
	}

	@Override
	public String printGame(Game game) {
		encodeGame(game);
		return boardToString(CELL_SIZE);
	}
}
