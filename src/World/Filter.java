package World;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import World.WorldObj;

public class Filter {
	
	private static void print(String str) {
		System.out.println(str);
	}
	public static void Show(List<WorldObj> Objs)
	{
		for(WorldObj obj:Objs)
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
			
			print("Position (X,Y): "+obj.getPosX()+", "+obj.getPosY() );
			print("Transform (11,12,21,11): "+obj.getTransform()[0][0]+", "+obj.getTransform()[0][1]+", "+obj.getTransform()[1][0]+", "+obj.getTransform()[1][1]);
			print("\n");
		}
	}

	public static void main(String[] args)
	{
		
		Show(Create());
	}
	
	
	
	public static List<WorldObj> Create()
	{
		List<WorldObj> WorldObjects=new ArrayList<>();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		
		try {
			File file = fileChooser.getSelectedFile();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			Document dom = (Document) db.parse(file);
			
			Element scene = dom.getDocumentElement(); 						// Scene node
			NodeList sceneContent = scene.getElementsByTagName("Objects");
			Node objectsRoot = sceneContent.item(0); 						// Objects node
			NodeList objects = objectsRoot.getChildNodes();
			Node object = null; 											// Object node
			NamedNodeMap attributes = null;
			
			int ID=0;
			String Name="";
			double[] Pos=new double[2];
			double[][] Transform=new double[2][2];
			
			

			for (int i = 0; i < objects.getLength(); ++i) {
				object = objects.item(i);
				attributes = object.getAttributes();
				
				if (attributes == null) // Warn: it finds newline characters as nodes!
					continue;
				
				for (int j = 0; j < attributes.getLength(); ++j) {
					switch(attributes.item(j).getNodeName()){
					case "id": ID=Integer.parseInt(attributes.item(j).getNodeValue());
					break;
					case "name": Name=attributes.item(j).getNodeValue();
					break;
					//case "type": Type=attributes.item(j).getNodeValue();
					//break;
					default:
						break;
					}
					
				}
				
				NodeList child = object.getChildNodes();
				for(int j=0;j<child.getLength();++j)
				{
					if(child.item(j).getNodeName().compareTo("#text")!=0)
					{
						NamedNodeMap childattributes=child.item(j).getAttributes();
						switch(child.item(j).getNodeName())
						{
						case "Position":
							Pos=new double[2];
							Pos[0]=Double.parseDouble(childattributes.item(0).getNodeValue());
							Pos[1]=Double.parseDouble(childattributes.item(1).getNodeValue());
							
							break;
						case "Transform":
							Transform=new double[2][2];
							Transform[0][0]=Double.parseDouble(childattributes.item(0).getNodeValue());
							Transform[0][1]=Double.parseDouble(childattributes.item(1).getNodeValue());
							Transform[1][0]=Double.parseDouble(childattributes.item(2).getNodeValue());
							Transform[1][1]=Double.parseDouble(childattributes.item(3).getNodeValue());
							break;
							default:
								break;
							
						}
					
					}
					
				}
				
				
				WorldObj.Type Type=GetType(Name);
				WorldObj.Sign Sign;
				WorldObj.Lane Lane;
				WorldObj.Misc Misc;
				if(Type==WorldObj.Type.Sign)
				{
					Misc=WorldObj.Misc.None;
					Lane=WorldObj.Lane.None;
					Sign=GetSign(Name);
				}
				else if(Type==WorldObj.Type.Lane)
				{
					Misc=WorldObj.Misc.None;
					Sign=WorldObj.Sign.None;
					Lane=GetLane(Name);
				}
				else
				{
					Misc=GetMisc(Name);
					Lane=WorldObj.Lane.None;
					Sign=WorldObj.Sign.None;
				}
				WorldObjects.add(new WorldObj(ID,Name,Type,Sign,Lane, Misc,Pos,Transform));
			}
		
			//Show(WorldObjects);
			
			
		} catch (ParserConfigurationException e) {
			print("Incorrect file format, please choose an XML file.");
		} catch (SAXException e) {
			print("Bad xml file, please choose another XML file.");
		}
		catch (IOException e) {
			print("File does not exists, please choose an XML file.");
		}
		
		return WorldObjects;
		
	}
	
	private static WorldObj.Type GetType(String type)
	{
		if(type.substring(10,11).compareTo("s")==0)
		{
			return WorldObj.Type.Sign;
		}
		else if(type.substring(10,11).compareTo("t")==0)
		{
			return WorldObj.Type.Lane;
		}
		else
		{
			return WorldObj.Type.Misc;
		}
	}
	
	private static WorldObj.Misc GetMisc(String name)
	{
		switch(name.substring(10,name.length()-4))
		{
		case "crosswalks/crosswalk_5_stripes":
			return WorldObj.Misc.CrossWalk;
		case "parking/parking_0":
			return WorldObj.Misc.Parking0;
		case "parking/parking_90":
			return WorldObj.Misc.Parking90;
		case "parking/parking_bollard":
			return WorldObj.Misc.Parking_Bollard;
		case "people/man03":
			return WorldObj.Misc.Man;
		case "trees/tree_top_view":
			return WorldObj.Misc.Tree;
			default:
				return WorldObj.Misc.None;
			
		}
	}
	
	private static WorldObj.Sign GetSign(String name)
	{
		String type=name.substring(16,18);
		if(type.compareTo("di")==0)
		{
			String x=name.substring(26,name.length()-5);
			switch(x)
			{
			case "209-30":
				return WorldObj.Sign.Direction_Forward;
			case "211-10":
				return WorldObj.Sign.Direction_Left;
			case "211-20":
				return WorldObj.Sign.Direction_Right;
			case "214-10":
				return WorldObj.Sign.Direction_ForwardLeft;
			case "214-20":
				return WorldObj.Sign.Direction_ForwardRight;
			case "215":
				return WorldObj.Sign.Direction_RoundAbout;
				}
			}
			else if(type.compareTo("pa")==0)
			{
				String x=name.substring(24,name.length()-5);
				switch(x)
				{
				case "314_10":
					return WorldObj.Sign.Parking_Left;
				case "314_20":
					return WorldObj.Sign.Parking_Right;
				}
			}
			else if(type.compareTo("pr")==0)
			{
				String x=name.substring(25,name.length()-5);
				switch(x)
				{
				case "205":
					return WorldObj.Sign.Priority_Yield;
				case "206":
					return WorldObj.Sign.Priority_Stop;
				case "306":
					return WorldObj.Sign.Priority_Highway;
				}
			}
			else if(type.compareTo("sp")==0)
			{
				String x=name.substring(22,name.length()-5);
				switch(x)
				{
				case "274_51":
					return WorldObj.Sign.Speed_10;
				case "274_52":
					return WorldObj.Sign.Speed_20;
				case "274_54":
					return WorldObj.Sign.Speed_40;
				case "274_55":
					return WorldObj.Sign.Speed_50;
				case "274_57":
					return WorldObj.Sign.Speed_70;
				case "274_59":
					return WorldObj.Sign.Speed_90;
				case "274_60":
					return WorldObj.Sign.Speed_100;
					
				}
			}
		return WorldObj.Sign.None;
	}
		

	
	private static WorldObj.Lane GetLane(String name)
	{
		String type=name.substring(23,24);
		if(type.compareTo("s")==0)
		{
			String x=name.substring(39,name.length()-5);
			switch(x)
			{
			case "s":
				return WorldObj.Lane.Simple_Straight;
			case "45l":
				return WorldObj.Lane.Simple_45Left;
			case "45r":
				return WorldObj.Lane.Simple_45Right;
			case "65l":
				return WorldObj.Lane.Simple_65Left;
			case "65r":
				return WorldObj.Lane.Simple_65Right;
			case "90l":
				return WorldObj.Lane.Simple_90Left;
			case "90r":
				return WorldObj.Lane.Simple_90Right;
			}
		}
		else if(type.compareTo("a")==0)
		{
			String x=name.substring(34,name.length()-5);
			switch(x)
			{
			case "crossroads_2":
				return WorldObj.Lane.Advanced_CrossRoads;
			case "rotary":
				return WorldObj.Lane.Advanced_Rotary;
			case "t_junction_l":
				return WorldObj.Lane.Advanced_JunctionLeft;
			case "t_junction_r":
				return WorldObj.Lane.Advanced_JunctionRight;
			}
		}
		
		return WorldObj.Lane.None;
	}
}

