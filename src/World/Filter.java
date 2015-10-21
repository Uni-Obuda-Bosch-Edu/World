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
			print("Position (X,Y): "+obj.getPosX()+", "+obj.getPosY() );
			print("Transform (11,12,21,11): "+obj.getTransform()[0][0]+", "+obj.getTransform()[0][1]+", "+obj.getTransform()[1][0]+", "+obj.getTransform()[1][1]);
			print("ZLevel: "+obj.getZLevel());
			print("Opacity: "+obj.getOpacity());
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
			String Type="";
			double PosX=0;
			double PosY=0;
			double[][] Transform=new double[2][2];
			int ZLevel=0;
			int Opacity=0;
			
			

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
					case "type": Type=attributes.item(j).getNodeValue();
					break;
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
							PosX=Double.parseDouble(childattributes.item(0).getNodeValue());
							PosY=Double.parseDouble(childattributes.item(1).getNodeValue());
							break;
						case "Transform":
							Transform[0][0]=Double.parseDouble(childattributes.item(0).getNodeValue());
							Transform[0][1]=Double.parseDouble(childattributes.item(1).getNodeValue());
							Transform[1][0]=Double.parseDouble(childattributes.item(2).getNodeValue());
							Transform[1][1]=Double.parseDouble(childattributes.item(3).getNodeValue());
							break;
						case "ZLevel":
							ZLevel=Integer.parseInt(childattributes.item(0).getNodeValue());
							break;
						case "Opacity":
							Opacity=Integer.parseInt(childattributes.item(0).getNodeValue());
							break;
							default:
								break;
							
						}
					
					}
					
				}
				
				WorldObjects.add(new WorldObj(ID,Name,Type,PosX,PosY,Transform,ZLevel,Opacity));
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
}

