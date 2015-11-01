package Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Common.Point2D;
import World.Filter;
import Interfaces.IWorldObject;

public class Test {
	
	private static final Point2D a=new Point2D(1675,2400);
	private static final Point2D b=new Point2D(1630,2600);
	private static final Point2D c=new Point2D(1680,2600);
	
	public Test() throws ParserConfigurationException, SAXException, IOException
	{
		URL url = getClass().getResource("road_1.xml");
		Filter filter=new Filter(url.getPath());
		Show(filter.getRelevantObjectsForRadar(a, b, c));
	}
	
	private void print(String str) {
		System.out.println(str);
	}
	public void Show(List<IWorldObject> Objs)
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
