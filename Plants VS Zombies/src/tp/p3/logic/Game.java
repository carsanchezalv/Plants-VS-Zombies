package tp.p3.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.FileContentsException;
import tp.p3.lists.PlantList;
import tp.p3.lists.ZombieList;
import tp.p3.logic.objects.GameObject;
import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.Zombie;
import tp.p3.logic.objects.factories.ZombieFactory;
import tp.p3.logic.objects.zombies.InfectedZombie;
import tp.p3.logic.printers.GamePrinter;
import tp.p3.logic.printers.ReleasePrinter;
import tp.p3.managers.SuncoinManager;
import tp.p3.managers.ZombieManager;
import tp.p3.objects.plants.Peashooter;
import tp.p3.objects.plants.Sunflower;


public class Game {
	
	private Random rand;
	private boolean end;
	private int seed;
	private PlantList plantList;
	private ZombieList zombieList;
	private int cycles;
	private SuncoinManager suncoinManager;
	private ZombieManager zombieManager;
	private GamePrinter gamePrinter;
	private Level level;
	private int cellSize;
	private boolean exit;
	private boolean noPrint;	
	public final static int NUM_ROWS = 4;
	public final static int NUM_COLUMNS = 8;
	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";
	private GameHistory[] history;
	private int indexHistory;

	public Game(Level level, int seed)
	{
		this.cycles = 0; 
		this.plantList = new PlantList();
		this.zombieList = new ZombieList();
		this.suncoinManager = new SuncoinManager();
		rand = new Random();
		rand.setSeed(seed);
		this.level = level;
		this.seed = seed;
		this.zombieManager = new ZombieManager(level, rand);
		this.end = false;
		this.history = new GameHistory[10];
		this.indexHistory = 0;
	}
	
	public boolean update()
	{	
		boolean victory = false;
				
		for(int i = 0; i < this.plantList.getSize(); ++i)
		{
			this.plantList.getPlant(i).update();
		}
		for(int i = 0; i < this.zombieList.getSize(); ++i)
		{
			Zombie zombie;
			zombie = this.zombieList.getZombie(i);
			
			if(zombie.getY() < 0)
				this.end = true;
			
			else
				zombie.update();
		}
		
		if ((zombieList.getSize() == 0) && (this.zombieManager.zombiesLeftToAppear() == 0))
		{
			Level newLevel = Level.nextLevel(level);
			if(newLevel == null)
			{
				victory = true;
				this.end = true;
			}
			else
			{
				level = newLevel;
				zombieManager.setNumZombies(level.getNumberOfZombies());
			}
		}
		
		int ciclos = this.cycles;
		PlantList plantas = plantList.clone();
		ZombieList zombies = zombieList.clone();
		SuncoinManager suncoins = suncoinManager.clone();
		ZombieManager zombiemanager = zombieManager.clone();
		
		history[indexHistory] = new GameHistory(plantas, zombies, ciclos, suncoins, zombiemanager,level);
		++indexHistory;
		
		
		return victory;		
	}

	public boolean undo()
	{
		if(indexHistory > 0)
		{
			plantList = history[indexHistory-2].getPlantList();
			zombieList = history[indexHistory-2].getZombieList();
			cycles = history[indexHistory-2].getCycles();
			level = history[indexHistory-2].getLevel();
			zombieManager = history[indexHistory-2].getZombieManager();
			suncoinManager = history[indexHistory-2].getSuncoinManager();
			indexHistory-=2;
			return true;
		}
		else
			return false;
	}
	
	public boolean redo()
	{
		if(cycles >= 0)
		{
			plantList = history[indexHistory].getPlantList();
			zombieList = history[indexHistory].getZombieList();
			cycles = history[indexHistory].getCycles();
			level = history[indexHistory].getLevel();
			zombieManager = history[indexHistory].getZombieManager();
			suncoinManager = history[indexHistory].getSuncoinManager();
			indexHistory++;
			return true;
		}
		else
			return false;
	}
	public void ZombieAttack(Zombie zombie)
	{
		Plant plant = canZombieAttack(zombie);
		Zombie infected = canZombieAttackInfected(zombie);
		if (plant != null)
		{
			plant.damage(zombie.getDamage());
			if(plant.getResistance() <= 0 && plant.getInitial().equalsIgnoreCase("h"))
			{
				int x = zombie.getX();
				int y = zombie.getY();
				Game gameInfected = zombie.getGame();
				this.zombieList.eraseZombie(zombie);
				
				zombie = new InfectedZombie();
				zombie.setX(x);
				zombie.setY(y);
				zombie.setGame(gameInfected);
				this.zombieList.addZombie(zombie);
			}
		}
		else if(infected != null)
			infected.damage(zombie.getDamage());
	}
	
	public Zombie canZombieAttackInfected(Zombie zombie)
	{
		int x=zombie.getX();
		int y=zombie.getY()-1;
		Zombie infected = zombieList.getZombie(x, y);
		return infected;
	}
	public void InfectedAttack(Zombie infected)
	{
		Zombie zombie = canInfectedAttack(infected);
		
		if(zombie != null)
			zombie.damage(infected.getDamage());
	}
	public Zombie canInfectedAttack(Zombie infected)
	{
		int x = infected.getX();
		int y = infected.getY() + 1;
		Zombie zombie = zombieList.getZombie(x, y);
		return zombie;
	}
	public Plant canZombieAttack(Zombie zombie)
	{
		int x=zombie.getX();
		int y=zombie.getY()-1;
		Plant plant = plantList.getPlant(x, y);
		return plant;
	}
	public boolean canPlantAttack(Plant plant, int x, int y)
	{
		Zombie zombie = zombieList.getZombie(x, y);
		
		if(zombie != null)
		{
			zombie.damage(plant.getDamage());
			return true;
		}
		else
			return false;
	}

	public void freeze(Plant plant, int x, int y)
	{
		Zombie zombie = zombieList.getZombie(x, y);
		
		if(zombie!=null)
			zombie.freeze();
	}
	
	public void computerAction() throws CommandExecuteException
	{
		int x,y;
		if(this.zombieManager.isZombieAdded())
		{
			x=this.rand.nextInt(NUM_ROWS); 
			y=NUM_COLUMNS-1;
			
			Zombie zombie = ZombieFactory.getZombie(); 
			zombie.setX(x);
			zombie.setY(y);
			this.addZombie(x, y, zombie);
		}
	}
	
	public void reset()
	{
		cycles = 0;
		this.end = false;
		plantList = new PlantList();
		zombieList = new ZombieList();
		suncoinManager = new SuncoinManager(); 
		zombieManager = new ZombieManager(level, rand); 
	}
	
	public String listElements()
	{
		return System.lineSeparator() + "[S]unflower: Cost " + Sunflower.COST + " suncoins   Harm: "
				+ Sunflower.DAMAGE + System.lineSeparator() + "[P]eashooter: Cost " + Peashooter.COST
				+ " suncoins   Harm: "	+ Peashooter.DAMAGE + System.lineSeparator();
	}

	public String help()
	{
		return System.lineSeparator() + "Add <plant> <x> <y>: Adds a plant in position x, y." + System.lineSeparator()
				+ "List: Prints the list of available plants." + System.lineSeparator() + "Zombielist: Prints the list of available zombies." +System.lineSeparator() + "Reset: Starts a new game."
				+ System.lineSeparator() + "Help: Prints this help message." + System.lineSeparator()
				+ "Exit: Terminates the program." + System.lineSeparator() + "[NONE]: Skips cycle." + System.lineSeparator();
	}
	
	public String getCycleInfo()
	{
		StringBuilder cadena = new StringBuilder();

		cadena.append("Number of cycles: " + this.cycles + System.lineSeparator());
		cadena.append("Sun coins: " + this.suncoinManager.toString() + System.lineSeparator());
		cadena.append("Remaining zombies: " + this.zombieManager.toString()+ System.lineSeparator());

		return cadena.toString();
	}
	public String debugInfoToString()
	{
		return getCycleInfo()+"Level: "+level.name()+"\n"+"Seed: "+seed+"\n"+ System.lineSeparator();
	}
		
	public void addPlant(int x, int y, Plant plant) throws CommandExecuteException 
	{	
		String message = "Invalid position";
		String messageSuncoins = "Not enough suncoins";
		if (this.suncoinManager.enoughSuncoins(plant.getCost()))
		{	
			if((plantList.getPlant(x, y) != null) || (zombieList.getZombie(x,y)!=null)||(x>=NUM_ROWS || y>=NUM_COLUMNS-1 || x<0||y<0))
				throw new CommandExecuteException(message); 		
			else
			{
				this.plantList.addPlant(plant); //Añade la planta a la lista
				this.suncoinManager.useSuncoins(plant.getCost()); //Resta las Suncoins que corresponden al coste
			}
		}
		else
			throw new CommandExecuteException(messageSuncoins);
	}
	public void erasePlant(Plant plant)
	{
		this.plantList.erasePlant(plant);
		this.suncoinManager.addSuncoins(plant.getCost()/2);
	}
	public void addZombie(int x,int y, Zombie zombie)
	{
		
		Plant plantInPosition=plantList.getPlant(x, y);
		Zombie zombieInPosition=zombieList.getZombie(x, y);
		if((plantInPosition==null)&&(zombieInPosition==null))
		{
			zombie.setGame(this);
			this.zombieManager.addZombie();  //Decrementa en 1 el numero de Zombies que quedan por salir
			this.zombieList.addZombie(zombie); //Inserta el Zombie en la lista
		}
	}	
	
	public void removePlant(Plant plant, int x, int y)
	{
		this.plantList.erasePlant(this.plantList.getPlant(x, y)); //Lo elimina de la lista
	}
	
	public void removeZombie(Zombie zombie, int x, int y)
	{
		this.zombieList.eraseZombie(this.zombieList.getZombie(x, y)); //Lo elimina de la lista
	}
	
	public boolean moveZombie(Zombie zombie)
	{
		boolean moved = false;
		int x = zombie.getX();
		int y = zombie.getY() - 1;
		
		if(y >= 0)
		{
			if((plantList.getPlant(x, y) == null) && (zombieList.getZombie(x,y) == null)) // Si la casilla está vacía
				moved = true;
		}
		return moved;
	}
	public boolean moveInfected(Zombie zombie)
	{
		boolean moved = false;
		int x = zombie.getX();
		int y = zombie.getY() + 1;
		
		if(y <= NUM_COLUMNS)
		{
			if((plantList.getPlant(x, y) == null) && (zombieList.getZombie(x,y) == null)) // Si la casilla está vacía
				moved = true;
		}
		return moved;
	}
	public String[] loadLine(BufferedReader inStream, String prefix, boolean isList)
	throws IOException, FileContentsException{
		String[] words;
		String line = inStream.readLine().trim();
		// absence of the prefix is invalid
		if ( ! line . startsWith(prefix + ":") )
			throw new FileContentsException(wrongPrefixMsg + prefix);
		// cut the prefix and the following colon off the line then trim it to get attribute contents
		String contentString = line. substring(prefix . length()+1).trim();
		// the attribute contents are not empty
		if (! contentString. equals(""))
		{
			if (! isList )
			{
				// split non−list attribute contents into words
				// using 1−or−more−white−spaces as separator
				words = contentString.split ("\\s+");
				// a non−list attribute with contents of more than one word is invalid
				if (words.length != 1)
					throw new FileContentsException(lineTooLongMsg + prefix);
			}
			else
				// split list attribute contents into words
				// using comma+0−or−more−white−spaces as separator
				words = contentString.split (",\\s*");
			// the attribute contents are empty
		}
		else
		{
		// a non−list attribute with empty contents is invalid
			if (! isList )
				throw new FileContentsException(lineTooShortMsg + prefix);
			// a list attribute with empty contents is valid; use a zero−length array to store its words
			words = new String[0];
		}
		return words;
	}
	public void load(BufferedReader br) throws IOException, FileContentsException {
		
		Game gameCopy = clone(); // Copia de seguridad
		
		try {
			String[] cycle = loadLine(br,"cycle",false);
			cycles=Integer.parseInt(cycle[0]);
			
			if(cycles<0)
				throw new FileContentsException("Ciclos de fichero no válidos");
			
			String[] suncoins=loadLine(br,"suncoins",false);
			suncoinManager.setSuncoins(Integer.parseInt(suncoins[0]));
			
			if(Integer.parseInt(suncoins[0])<0)
				throw new FileContentsException("Suncoins de fichero no válidos");
			
			String[] level=loadLine(br,"level",false);
			Level lvl=Level.parse(level[0]);
			
			if(lvl==null)
				throw new FileContentsException("Nivel de fichero no válido");
			
			String[] zombiesLeft=loadLine(br,"remZombies", false);
			this.zombieManager=new ZombieManager(lvl,this.rand,Integer.parseInt(zombiesLeft[0]));
			
			if(Integer.parseInt(zombiesLeft[0])>lvl.getNumberOfZombies())
				throw new FileContentsException("Zombies restantes de fichero no válidos");
			
			loadPlantList(br);
			loadZombieList(br);
			
			this.gamePrinter=new ReleasePrinter(Game.NUM_ROWS,Game.NUM_COLUMNS);
		}catch(NumberFormatException|ArrayIndexOutOfBoundsException | NullPointerException ex) {
			restoreGame(gameCopy);
			throw new FileContentsException("Formato incorrecto de fichero.");
		} finally {
			br.close();
		}
	}
	
	public void loadPlantList(BufferedReader br) throws IOException, FileContentsException
	{
		String[] plants=loadLine(br,"plantList", true);
		this.plantList = new PlantList();
		plantList.plantsLoad(plants,this);
	}
	
	public void loadZombieList(BufferedReader br) throws IOException, FileContentsException
	{
		String[] zombies=loadLine(br,"zombieList",true);
		this.zombieList = new ZombieList();
		zombieList.zombiesLoad(zombies, this);
	}
	
	public Game clone() {
		Game game= new Game(this.level,this.seed);
		game.cycles=this.cycles;
		game.plantList=plantList.clone();
		game.zombieList=zombieList.clone();
		game.suncoinManager=suncoinManager.clone();
		game.zombieManager=zombieManager.clone();
		return game;
	}
	
	private void restoreGame(Game game) {
		this.cycles=game.cycles;
		this.plantList=game.plantList;
		this.zombieList=game.zombieList;
		this.suncoinManager=game.suncoinManager;
		this.zombieManager=game.zombieManager;
		this.level=game.level;
		this.seed=game.seed;
		System.out.println("Juego restaurado.");
	}
	
	public void store(BufferedWriter bw) throws IOException {
		bw.write("cycle: "+this.cycles+System.lineSeparator());
		bw.write("suncoins: "+this.suncoinManager.toString()+System.lineSeparator());
		bw.write("level: "+this.level.toString()+System.lineSeparator());
		bw.write("remZombies: "+this.zombieManager.zombiesLeftToAppear()+System.lineSeparator());
		bw.write("plantList: "+this.plantList.externalise()+System.lineSeparator());
		bw.write("zombieList: "+this.zombieList.externalise());
		bw.flush();
		bw.close();
	}
	
	public GameObject getGameObject(int x, int y)
	{
		GameObject gameObject=null;
		for(int i=0;i<plantList.getSize();++i)
		{
			Plant plant=plantList.getPlant(i);
			if((plant.getX()==x)&&(plant.getY()==y))
				gameObject=plant;
		}
		
		if(gameObject==null)
		{
			for(int i=0;i<zombieList.getSize();++i)
			{
				Zombie zombie=zombieList.getZombie(i);
				if((zombie.getX()==x)&&(zombie.getY()==y))
					gameObject=zombie;
			}
		}
		return gameObject;
	}
	
	public void addCycle()
	{
		cycles++;
	}
	
	public void addSuncoins()
	{
		suncoinManager.addSuncoins();
	}

	public PlantList getPlantList() {
		return plantList;
	}

	public ZombieList getZombieList() {
		return zombieList;
	}
	public int getSeed()
	{
		return this.seed;
	}
	public int getNumObjects()
	{
		return (plantList.getSize()+zombieList.getSize());
	}
	public int plantListSize()
	{
		return plantList.getSize();
	}
	public int zombieListSize()
	{
		return zombieList.getSize();
	}
	public String getPlantInfo(int i)
	{
		return plantList.getPlant(i).debugInfo();
	}
	public String getZombieInfo(int i)
	{
		return zombieList.getZombie(i).debugInfo();
	}
	public String getPlantToString(int i)
	{
		return plantList.getPlant(i).toString();
	}
	public String getZombieToString(int i)
	{
		return zombieList.getZombie(i).toString();
	}
	public int getPlantX(int i)
	{
		return plantList.getPlant(i).getX();
	}
	public int getPlantY(int i)
	{
		return plantList.getPlant(i).getY();
	}
	public int getZombieX(int i)
	{
		return zombieList.getZombie(i).getX();
	}
	public int getZombieY(int i)
	{
		return zombieList.getZombie(i).getY();
	}
	public boolean getEnd()
	{
		return this.end;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	public void draw()
	{
		if(!noPrint)
			System.out.println(this.gamePrinter.printGame(this));
	}

	public void setGamePrinter(GamePrinter gamePrinter)
	{
		this.gamePrinter=gamePrinter;
	}
	public void setNoPrintGameState()
	{
		this.noPrint=true;
	}
	public void setPrintGameState()
	{
		this.noPrint=false;
	}
	public void setExit(boolean exit)
	{
		this.exit = exit;
	}
	public boolean getExit()
	{
		return exit;
	}
	public void setCellsize(int cellSize)
	{
		this.cellSize=cellSize;
	}
	public int getCellSize()
	{
		return this.cellSize;
	}
}
