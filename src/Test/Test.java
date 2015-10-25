package Test;

import java.util.List;

import World.Filter;
import Interfaces.IWorldObject;

public class Test {
	
	public static void main(String[] args)
	{
		Filter f=new Filter();
		Show(f.GetObjects());
		
	}
	
	private static void print(String str) {
		System.out.println(str);
	}
	public static void Show(List<IWorldObject> Objs)
	{
		for(IWorldObject obj:Objs)
		{
			print("Id: "+obj.getID());
			print("Name: "+obj.getName());
			print("Type: "+obj.getType());
			switch(obj.getType())
			{
				case Misc:
					print("SubType: "+obj.getMisc());
					break;
				case Sign:
					print("SubType: "+obj.getSign());
					break;
				case Lane:
					print("SubType: "+obj.getLane());
					break;
			}			
			
			print("Position (X,Y): "+obj.getPosition().getX()+", "+obj.getPosition().getY() );
			print("Transform (11,12,21,11): "+obj.getTransform()[0][0]+", "+obj.getTransform()[0][1]+", "+obj.getTransform()[1][0]+", "+obj.getTransform()[1][1]);
			print("\n");
		}
	}
}
