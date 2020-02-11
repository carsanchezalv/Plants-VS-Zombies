package tp.p3.logic;

import tp.p3.lists.PlantList;
import tp.p3.lists.ZombieList;
import tp.p3.managers.SuncoinManager;
import tp.p3.managers.ZombieManager;

public class GameHistory {
	
	private PlantList plantList;
	private ZombieList zombieList;
	private int cycles;
	private SuncoinManager suncoinManager;
	private ZombieManager zombieManager;
	public Level level;
	
	public GameHistory(PlantList plantList, ZombieList zombieList, int cycles, SuncoinManager suncoinManager, ZombieManager zombieManager, Level level)
	{
		this.plantList = plantList;
		this.zombieList = zombieList;
		this.cycles = cycles;
		this.suncoinManager = suncoinManager;
		this.zombieManager = zombieManager;
		this.level = level;
	}

	public PlantList getPlantList() {
		return plantList;
	}

	public ZombieList getZombieList() {
		return zombieList;
	}

	public int getCycles() {
		return cycles;
	}

	public SuncoinManager getSuncoinManager() {
		return suncoinManager;
	}

	public ZombieManager getZombieManager() {
		return zombieManager;
	}

	public Level getLevel() {
		return level;
	}
}
