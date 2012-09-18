package home.mutantu.rubber.controller;

import home.mutantu.rubber.model.RubberObject;
import home.mutantu.rubber.model.RubberWorld;

public class WorldFactory
{
	public static RubberWorld create3PointsWorld()
	{
		RubberWorld world  = new RubberWorld();
		RubberObject obj = new RubberObject();
		obj.addPoint();
		obj.addPoint();
		obj.addPoint(100,110);
		obj.linkPoints(0, 1, 10);
		obj.linkPoints(0, 2, 10);
		obj.linkPoints(2, 1, 10);
		world.addObject(obj);
		
		return world;
	}
	
	public static RubberWorld createOneRectangleWorld()
	{
		double distance = 13;
		double distanceDiagonal = distance*Math.sqrt(2);
		RubberWorld world  = new RubberWorld();
		RubberObject obj = new RubberObject();
		
		for (int x=100;x<=200;x+=10)
		{
			for(int y=100;y<=200;y+=10)
			{
				obj.addPoint(x,y);
			}
		}
		
		int index=12;
		for (int x=110;x<200;x+=10)
		{
			for(int y=110;y<200;y+=10)
			{
				obj.linkPoints(index, index+1, distance);
				obj.linkPoints(index, index-1, distance);
				obj.linkPoints(index, index-11, distance);
				obj.linkPoints(index, index+11, distance);
				
				obj.linkPoints(index, index+10, distanceDiagonal);
				obj.linkPoints(index, index-10, distanceDiagonal);
				obj.linkPoints(index, index-12, distanceDiagonal);
				obj.linkPoints(index, index+12, distanceDiagonal);
				
				index++;
			}
			index+=2;
		}
		
		index=24;
		for (int x=120;x<190;x+=10)
		{
			for(int y=120;y<190;y+=10)
			{
				obj.linkPoints(index, index+2, distance*2);
				obj.linkPoints(index, index-2, distance*2);
				obj.linkPoints(index, index-22, distance*2);
				obj.linkPoints(index, index+22, distance*2);
				
				obj.linkPoints(index, index-20, distanceDiagonal*2);
				obj.linkPoints(index, index-24, distanceDiagonal*2);
				obj.linkPoints(index, index+20, distanceDiagonal*2);
				obj.linkPoints(index, index+24, distanceDiagonal*2);
				
				index++;
			}
			index+=4;
		}
		
		obj.linkPoints(0, 1, distance);
		obj.linkPoints(0, 11, distance);
		obj.linkPoints(0, 12, distanceDiagonal);
		
		obj.linkPoints(9, 10, distance);
		obj.linkPoints(9, 20, distance);
		obj.linkPoints(9, 21, distanceDiagonal);
		
		obj.linkPoints(120, 119, distance);
		obj.linkPoints(120, 109, distance);
		obj.linkPoints(120, 108, distanceDiagonal);
		
		obj.linkPoints(110, 111, distance);
		obj.linkPoints(110, 99, distance);
		obj.linkPoints(110, 100, distanceDiagonal);
		
		world.addObject(obj);
		
		return world;
	}
}
