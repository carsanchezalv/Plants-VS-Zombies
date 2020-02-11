package tp.p3.lists;

import java.util.Arrays;

import tp.p3.exceptions.FileContentsException;
import tp.p3.logic.Game;
import tp.p3.logic.objects.Zombie;
import tp.p3.logic.objects.factories.ZombieFactory;

public class ZombieList {
	public int counter;
	public final static int MAX=1; 
	private Zombie[]zombieList;
	
	public ZombieList()
	{
		this.counter=0;
		this.zombieList=new Zombie[MAX];
	}
	
	public void addZombie(Zombie zombie)
	{
		if(counter+1==zombieList.length)
		{
			Zombie[]aux=Arrays.copyOf(zombieList, zombieList.length*2); // DINAMICO
			this.zombieList=aux;
		}
		this.zombieList[counter]=zombie;
		this.counter++;
	}
	public ZombieList clone()
	{
		ZombieList zombieList=new ZombieList();
		zombieList.zombieList=Arrays.copyOf(this.zombieList, this.zombieList.length);
		zombieList.counter=this.counter;
		return zombieList;
	}
	public void eraseZombie(Zombie zombie)
	{
		int i=0;
		boolean found=false;
		while((!found) && (i<this.counter))
		{
			if(zombie == zombieList[i])
			{
				found=true;
				moveZombieFromList(i);
				--this.counter;
				zombie=null;
			}
			else
				++i;
		}
	}
	
	private void moveZombieFromList(int position)
	{
		for(int i=position;i<counter;++i)
		{
			zombieList[i]=zombieList[i+1];
		} 
		
	}
	
	public Zombie getZombie(int i)
	{
		return zombieList[i];
	}
	
	public Zombie getZombie(int x, int y)
	{
		Zombie zombie = null;
		boolean found = false;
		int i = 0;
		while((!found)&&(i<counter))
		{
			if((zombieList[i].getY() == y)&&(zombieList[i].getX() == x))
			{
				found = true;
				zombie = this.zombieList[i];
			}
			else
				++i;
		}
		return zombie;
	}
	
	public int getSize()
	{
		return this.counter;
	}

	public String externalise() {
		StringBuilder str=new StringBuilder();
		String separator;
		
		for(int i=0;i<counter;++i)
		{
			if(i==counter-1)
				separator="";	
			else
				separator=", ";
			
			str.append(zombieList[i].externalise()+separator);
		}
		return str.toString();
	}
	
	public void zombiesLoad(String []zombies,Game game) throws FileContentsException
	{
		Zombie zombie=null;
		for(int i=0;i<zombies.length;++i)
		{
			try {
				String []plantElements=zombies[i].split(":");
				zombie = ZombieFactory.getZombie(plantElements[0]);
				if(zombie==null)
					throw new FileContentsException("Planta erronea leida del fichero");
				
				zombie.loadObject(plantElements, game);
				
			}catch(NullPointerException ex) {
				System.out.println(ex.getClass());
				throw new FileContentsException("Fichero no valido.");
			}
			this.addZombie(zombie);
		}
	}
}
