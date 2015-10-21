package World;

public class WorldObj {
	private int ID;
	private String Name;
	private String Type;
	private double PosX;
	private double PosY;
	private double[][] Transform;
	private int ZLevel;
	private int Opacity;
	
	public WorldObj(int id,String name,String type ,double posx,double posy,double[][]transform,int zlevel,int opacity)
	{
		Transform= new double[2][2];
		ID=id;
		Name=name;
		Type=type;
		PosX=posx;
		PosY=posy;
		Transform=transform;
		ZLevel=zlevel;
		Opacity=opacity;
		
	}

	int getOpacity() {
		return Opacity;
	}

	int getZLevel() {
		return ZLevel;
	}
	
	int getID() {
		return ID;
	}
	
	String getName() {
		return Name;
	}
	
	String getType() {
		return Type;
	}
	
	double getPosX() {
		return PosX;
	}
	
	double getPosY() {
		return PosY;
	}
	
	double[][] getTransform() {
		return Transform;
	}
	
	




}
