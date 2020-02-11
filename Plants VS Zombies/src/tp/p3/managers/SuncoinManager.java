package tp.p3.managers;

import tp.p3.objects.plants.Sunflower;

public class SuncoinManager {
	
	private int numSuncoins;
	
	public SuncoinManager()
	{
		this.numSuncoins = 600;
	}
	public void addSuncoins()
	{
		this.numSuncoins+=Sunflower.SUNS; 
	}
	public void useSuncoins(int suncoins)
	{
		this.numSuncoins-=suncoins;
	}
	public boolean enoughSuncoins(int suncoins)
	{
		boolean enough = false;
		if(this.numSuncoins >= suncoins)
			enough = true;
		
		return enough;
	}
	public String toString()
	{
		return Integer.toString(this.numSuncoins);
	}
	public void setSuncoins(int suncoins)
	{
		this.numSuncoins=suncoins;	
	}
	public SuncoinManager clone()
	{
		SuncoinManager suncoinManager=new SuncoinManager();
		suncoinManager.setSuncoins(this.numSuncoins);
		return suncoinManager;
	}
	public void addSuncoins(int i)
	{
		this.numSuncoins += i;
	}
}
