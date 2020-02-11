package tp.p3.lists;


import java.util.Arrays;
import tp.p3.exceptions.FileContentsException;
import tp.p3.logic.Game;
import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.factories.PlantFactory;

public class PlantList {
	public int counter;
	public final static int MAX=1; 
	private Plant[]plantList;
	
	public PlantList() {
		this.counter=0;
		this.plantList=new Plant[MAX];
	}
	
	public void addPlant(Plant plant) {
		if(counter+1==plantList.length)
		{
			Plant[]aux=Arrays.copyOf(plantList, plantList.length*2); // DINAMICO
			this.plantList=aux;
		}
		plantList[counter]=plant;
		this.counter++;
	}
	
	public void erasePlant(Plant plant)
	{
		int i=0;
		boolean found=false;
		while((!found)&&(i<this.counter))
		{
			if(plant==plantList[i])
			{
				found=true;
				movePlantFromList(i);
				--this.counter;
			}
			else
				++i;
		}
	}
	
	private void movePlantFromList(int position) {
		for(int i=position;i<counter;++i)
		{
			plantList[i]=plantList[i+1];
		} 
	}
	
	public Plant getPlant(int i) {
		return plantList[i];
	}
	
	public Plant getPlant(int x, int y) {
		Plant plant=null;
		boolean found=false;
		int i=0;
		while((!found)&&(i<counter))
		{
			if((plantList[i].getY()==y)&&(plantList[i].getX()==x))
			{
				found = true;
				plant = this.plantList[i];
			}
			else
				++i;
		}
		return plant;
	}
	
	public int getSize() {
		return counter;
	}
	
	public void plantsLoad(String[] plants, Game game) throws FileContentsException
	{
		Plant plant=null;
		for(int i=0;i<plants.length;++i)
		{
			try {
				String []plantElements=plants[i].split(":");
				plant = PlantFactory.getPlant(plantElements[0]);
				
				if(plant==null)
					throw new FileContentsException("Planta erronea leida del fichero");
				
				plant.loadObject(plantElements, game);
				
			}catch(NullPointerException ex) {
				System.out.println(ex.getClass());
				throw new FileContentsException("Fichero no valido.");
			}
			this.addPlant(plant);
		}
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
			
			str.append(plantList[i].externalise()+separator);
		}
		return str.toString();
	}
	
	public PlantList clone()
	{
		PlantList plantList=new PlantList();
		plantList.plantList=Arrays.copyOf(this.plantList, this.plantList.length);
		plantList.counter=this.counter;
		return plantList;
	}
}
