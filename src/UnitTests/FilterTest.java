package UnitTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import Common.Point2D;
import Interfaces.IWorldObject;
import World.Filter;
import World.WorldObjectTypes;
import World.WorldObjectTypes.Lane;
import World.WorldObjectTypes.Misc;

public class FilterTest {
	private Filter filter;
	
	private static final double NoDifferenceAllowed = 0.0;
	private static final Point2D a=new Point2D(1675,2400);
	private static final Point2D b=new Point2D(1630,2600);
	private static final Point2D c=new Point2D(1680,2600);
	
	private static final int id=33;
	private static final String name="data/road_signs/priority/206_.svg";
	private static final WorldObjectTypes.Type type=WorldObjectTypes.Type.Sign;
	private static final WorldObjectTypes.Sign sign=WorldObjectTypes.Sign.Priority_Stop;
	private static final WorldObjectTypes.Lane lane=WorldObjectTypes.Lane.None;
	private static final WorldObjectTypes.Misc misc=WorldObjectTypes.Misc.None;
	private static final Point2D point=new Point2D(1675, 2540);
	private static final double[][] trans=new double[][]{{1,0},{0,1}};
	
	@Before
	public void setUp() throws ParserConfigurationException, SAXException, IOException
	{
		URL url = getClass().getResource("road_1.xml");
		filter=new Filter(url.getPath());
	}
	
	@Test
	public void testFoundObjectNumberForRadar() {
		assertEquals(2,filter.getRelevantObjectsForRadar(a, b, c).size(),NoDifferenceAllowed);
	}
	
	@Test
	public void testFoundObjectNumberForUltraSound() {
		assertEquals(2,filter.getRelevantObjectsForUltraSound(a, b, c).size(),NoDifferenceAllowed);
	}
	
	@Test
	public void testFoundObjectNumberForVideoCamera() {
		assertEquals(2,filter.getRelevantObjectsForVideoCamera(a, b, c).size(),NoDifferenceAllowed);
	}
	
	@Test
	public void testFoundObjectForRadar() {
		IWorldObject testObj= filter.getRelevantObjectsForRadar(a, b, c).get(0);
		
		assertEquals(id,testObj.getID(),NoDifferenceAllowed);
		
		int res=name.compareTo(testObj.getName());
		assertEquals(0,res,NoDifferenceAllowed);
		
		assertEquals(2,testObj.getTransform().length,NoDifferenceAllowed);
		assertEquals(2,testObj.getTransform()[0].length,NoDifferenceAllowed);
		assertEquals(2,testObj.getTransform()[1].length,NoDifferenceAllowed);
		assertEquals(trans[0][0],testObj.getTransform()[0][0],NoDifferenceAllowed);
		assertEquals(trans[0][1],testObj.getTransform()[0][1],NoDifferenceAllowed);
		assertEquals(trans[1][0],testObj.getTransform()[1][0],NoDifferenceAllowed);
		assertEquals(trans[1][1],testObj.getTransform()[1][1],NoDifferenceAllowed);
		
		assertEquals(point.getX(),testObj.getPosition().getX(),NoDifferenceAllowed);
		assertEquals(point.getY(),testObj.getPosition().getY(),NoDifferenceAllowed);
		
		res=type.compareTo(testObj.getType());
		assertEquals(0,res,NoDifferenceAllowed);
		
		
		int resLane=lane.compareTo(Lane.None);
		int resSign=sign.compareTo(testObj.getSign());
		int resMisc=misc.compareTo(Misc.None);
		assertEquals(0,resLane,NoDifferenceAllowed);
		assertEquals(0,resSign,NoDifferenceAllowed);
		assertEquals(0,resMisc,NoDifferenceAllowed);
		
	}
	
	@Test
	public void testFoundObjectForUltraSound() {
		IWorldObject testObj= filter.getRelevantObjectsForUltraSound(a, b, c).get(0);
		
		assertEquals(id,testObj.getID(),NoDifferenceAllowed);
		
		int res=name.compareTo(testObj.getName());
		assertEquals(0,res,NoDifferenceAllowed);
		
		assertEquals(2,testObj.getTransform().length,NoDifferenceAllowed);
		assertEquals(2,testObj.getTransform()[0].length,NoDifferenceAllowed);
		assertEquals(2,testObj.getTransform()[1].length,NoDifferenceAllowed);
		assertEquals(trans[0][0],testObj.getTransform()[0][0],NoDifferenceAllowed);
		assertEquals(trans[0][1],testObj.getTransform()[0][1],NoDifferenceAllowed);
		assertEquals(trans[1][0],testObj.getTransform()[1][0],NoDifferenceAllowed);
		assertEquals(trans[1][1],testObj.getTransform()[1][1],NoDifferenceAllowed);
		
		assertEquals(point.getX(),testObj.getPosition().getX(),NoDifferenceAllowed);
		assertEquals(point.getY(),testObj.getPosition().getY(),NoDifferenceAllowed);
		
		res=type.compareTo(testObj.getType());
		assertEquals(0,res,NoDifferenceAllowed);
		
		
		int resLane=lane.compareTo(Lane.None);
		int resSign=sign.compareTo(testObj.getSign());
		int resMisc=misc.compareTo(Misc.None);
		assertEquals(0,resLane,NoDifferenceAllowed);
		assertEquals(0,resSign,NoDifferenceAllowed);
		assertEquals(0,resMisc,NoDifferenceAllowed);
		
	}
	
	@Test
	public void testFoundObjectForVideCamera() {
		IWorldObject testObj= filter.getRelevantObjectsForVideoCamera(a, b, c).get(0);
		
		assertEquals(id,testObj.getID(),NoDifferenceAllowed);
		
		int res=name.compareTo(testObj.getName());
		assertEquals(0,res,NoDifferenceAllowed);
		
		assertEquals(2,testObj.getTransform().length,NoDifferenceAllowed);
		assertEquals(2,testObj.getTransform()[0].length,NoDifferenceAllowed);
		assertEquals(2,testObj.getTransform()[1].length,NoDifferenceAllowed);
		assertEquals(trans[0][0],testObj.getTransform()[0][0],NoDifferenceAllowed);
		assertEquals(trans[0][1],testObj.getTransform()[0][1],NoDifferenceAllowed);
		assertEquals(trans[1][0],testObj.getTransform()[1][0],NoDifferenceAllowed);
		assertEquals(trans[1][1],testObj.getTransform()[1][1],NoDifferenceAllowed);
		
		assertEquals(point.getX(),testObj.getPosition().getX(),NoDifferenceAllowed);
		assertEquals(point.getY(),testObj.getPosition().getY(),NoDifferenceAllowed);
		
		res=type.compareTo(testObj.getType());
		assertEquals(0,res,NoDifferenceAllowed);
		
		
		int resLane=lane.compareTo(Lane.None);
		int resSign=sign.compareTo(testObj.getSign());
		int resMisc=misc.compareTo(Misc.None);
		assertEquals(0,resLane,NoDifferenceAllowed);
		assertEquals(0,resSign,NoDifferenceAllowed);
		assertEquals(0,resMisc,NoDifferenceAllowed);
		
	}

}
