package UnitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import World.WorldObj;
import World.WorldObjectTypes;
import World.WorldObjectTypes.Lane;
import World.WorldObjectTypes.Misc;
import World.WorldObjectTypes.Sign;

public class WorldObjTest {

	private WorldObj testObj;
	
	private static final double NoDifferenceAllowed=0.0;
	
	private static final int id=0;
	private static final String name="data/road_tiles/2_lane_simple/2_simple_s.tile";
	private static final WorldObjectTypes.Type type=WorldObjectTypes.Type.Lane;
	private static final WorldObjectTypes.Sign sign=WorldObjectTypes.Sign.None;
	private static final WorldObjectTypes.Lane lane=WorldObjectTypes.Lane.Simple_Straight;
	private static final WorldObjectTypes.Misc misc=WorldObjectTypes.Misc.None;
	private static final double[] point=new double[]{100,100};
	private static final double[][] trans=new double[][]{{-1,0},{0,-1}};
	
	@Before
	public void setUp()
	{
		testObj=new WorldObj(id, name, type, sign, lane, WorldObjectTypes.Misc.None, point, trans);
	}
	
	@Test
	public void testID() {
		assertEquals(id,testObj.getID(),NoDifferenceAllowed);
	}
	
	@Test
	public void testName() {
		int res=name.compareTo(testObj.getName());
		assertEquals(0,res,NoDifferenceAllowed);
	}
	
	@Test
	public void testTransform() {
		assertEquals(2,testObj.getTransform().length,NoDifferenceAllowed);
		assertEquals(2,testObj.getTransform()[0].length,NoDifferenceAllowed);
		assertEquals(2,testObj.getTransform()[1].length,NoDifferenceAllowed);
		assertEquals(trans[0][0],testObj.getTransform()[0][0],NoDifferenceAllowed);
		assertEquals(trans[0][1],testObj.getTransform()[0][1],NoDifferenceAllowed);
		assertEquals(trans[1][0],testObj.getTransform()[1][0],NoDifferenceAllowed);
		assertEquals(trans[1][1],testObj.getTransform()[1][1],NoDifferenceAllowed);
	}
	
	@Test
	public void testPosition() {
		assertEquals(60,testObj.getPosition().getX(),NoDifferenceAllowed);
		assertEquals(60,testObj.getPosition().getY(),NoDifferenceAllowed);
	}
	
	@Test
	public void testType() {
		int res=type.compareTo(testObj.getType());
		assertEquals(0,res,NoDifferenceAllowed);
	}
	
	@Test
	public void testSubType() {
		int resLane=-1;
		int resSign=-1;
		int resMisc=-1;
		switch(testObj.getType())
		{
			case Lane:
				resLane=lane.compareTo(testObj.getLane());
				resSign=sign.compareTo(Sign.None);
				resMisc=misc.compareTo(Misc.None);
				break;
			case Sign:
				resLane=lane.compareTo(Lane.None);
				resSign=sign.compareTo(testObj.getSign());
				resMisc=misc.compareTo(Misc.None);
				break;
			case Misc:
				resLane=lane.compareTo(Lane.None);
				resSign=sign.compareTo(Sign.None);
				resMisc=misc.compareTo(testObj.getMisc());
				break;
		}
		assertEquals(0,resLane,NoDifferenceAllowed);
		assertEquals(0,resSign,NoDifferenceAllowed);
		assertEquals(0,resMisc,NoDifferenceAllowed);
	}
	
	

}
