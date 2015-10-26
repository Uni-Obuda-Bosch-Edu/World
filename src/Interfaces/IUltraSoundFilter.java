package Interfaces;

import java.util.List;

public interface IUltraSoundFilter {
	
	List<IWorldObject> getRelevantObjectsForUltraSounds(I2DPoint a, I2DPoint b, I2DPoint c);
}
