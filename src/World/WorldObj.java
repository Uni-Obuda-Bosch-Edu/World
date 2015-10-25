package World;

public class WorldObj {
	public static enum Type{Sign,Lane, Misc};
	public static enum Sign{None,
		Direction_Forward, Direction_Left, Direction_Right, Direction_ForwardLeft, Direction_ForwardRight, Direction_RoundAbout,
		Parking_Left, Parking_Right, 
		Priority_Yield, Priority_Stop, Priority_Highway,
		Speed_10, Speed_20, Speed_40, Speed_50, Speed_70, Speed_90, Speed_100};
	public static enum Lane{None,
		Advanced_CrossRoads, Advanced_Rotary, Advanced_JunctionLeft, Advanced_JunctionRight,
		Simple_45Left, Simple_45Right, Simple_65Left, Simple_65Right, Simple_90Left, Simple_90Right, Simple_Straight};
	public static enum Misc{ None,
		CrossWalk, Parking0, Parking90, Parking_Bollard, Man, Tree};
	private int ID;
	private String Name;
	private Type Type;
	private Sign Sign;
	private Lane Lane;
	private Misc Misc;
	private double[] Pos;
	private double[][] Transform;
	
	public WorldObj(int id,String name, Type type ,Sign sign,Lane lane, Misc misc, double[] pos,double[][]transform)
	{
		
		Transform= new double[2][2];
		ID=id;
		Name=name;
		Type=type;
		Sign=sign;
		Lane=lane;
		Misc=misc;
		Transform=transform;
		pos[0]+=40;
		pos[1]+=40;
		Pos=Transform(pos,Transform);
	}

	private double[] Transform(double[] pos,double[][] T)
	{
		double[] res=new double[2];
		res[0]=pos[0]*T[0][0]+pos[1]*T[1][0];
		res[1]=pos[0]*T[0][1]+pos[1]*T[1][1];
		return res;
		
	}
	
	int getID() {
		return ID;
	}
	
	String getName() {
		return Name;
	}
	
	Type getType() {
		return Type;
	}
	
	Sign getSign()
	{
		return Sign;
	}
	
	Misc getMisc()
	{
		return Misc;
	}
	
	Lane getLane()
	{
		return Lane;
	}
	
	double getPosX() {
		return Pos[0];
	}
	
	double getPosY() {
		return Pos[1];
	}
	
	double[][] getTransform() {
		return Transform;
	}
	
	




}
