package tp.p3.logic.objects;

import tp.p3.exceptions.FileContentsException;
import tp.p3.logic.Game;

public abstract class GameObject {
	
	private int x;
	private int y;
	private int resistance;
	private int damage;
	private int remainingCycles;
	private int cycles;
	private int initialCycles;
	private String name;
	private String initial;
	private Game game;
	
	public GameObject(int resistance,int damage,int cycles,String name,String initial)
	{
		this.resistance=resistance;
		this.damage=damage;
		this.remainingCycles=cycles;
		this.cycles=0;
		this.initialCycles=cycles;
		this.name=name;
		this.initial=initial;
	}
	public abstract void update();
	public abstract void damage(int pain);
	
	public String debugInfo() {
		return initial+"[l:"+resistance+", x:"+x+", y:"+y+", t:"+cycles+"]";
	}
	
	public String externalise() {
		StringBuilder str=new StringBuilder();
		str.append(initial+":"+resistance+":"+x+":"+y+":"+cycles);
		return str.toString();
	}
	
	public void loadObject(String[] elements, Game game) throws FileContentsException {
		this.setResistance(Integer.parseInt(elements[1]));
		if(this.getResistance()>this.resistance)
			throw new FileContentsException("Resistencia de fichero no válida.");
		
		this.setX(Integer.parseInt(elements[2]));
		this.setY(Integer.parseInt(elements[3]));
		if(this.getY()<0||this.getX()<0)
			throw new FileContentsException("Coordenadas de fichero no válidas.");
		
		this.setCycles(Integer.parseInt(elements[4]));
		if(this.getCycles()<0)
			throw new FileContentsException("Ciclos de fichero no válidos.");
		
		this.setGame(game);
	}
	
	public String toString() {
		return initial+" ["+resistance+"]";
	}
	
	public int getInitialCycles() {
		return this.initialCycles;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String getName() {
		return name;
	}
	
	public String getInitial() {
		return initial;
	}
	
	public int getResistance() {
		return resistance;
	}
	
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void setDamage(int damage)
	{
		this.damage = damage;
	}
	
	public int getRemainingCycles()
	{
		return remainingCycles;
	}
	
	public void setRemainingCycles(int remainingCycles) {
		this.remainingCycles = remainingCycles;
	}
	
	public int getCycles() {
		return cycles;
	}
	
	public void setCycles(int cycles)
	{
		this.cycles = cycles;
	}
	
	public void addCycle()
	{
		this.cycles++;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setInitial(String initial) {
		this.initial = initial;
	}
	
	public void removeCycles() {
		this.cycles--;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
}
