package home.mutantu.rubber.controller;

import home.mutantu.rubber.model.RubberObject;
import home.mutantu.rubber.model.RubberPoint;
import home.mutantu.rubber.model.RubberWorld;

public class WorldFactory
{
	public static RubberWorld create3PointsObjectWorld()
	{
		RubberWorld world  = new RubberWorld(10,10);
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
	
	public static RubberWorld createOneRectangleObjectWorld(int initX,int initY,int numberPointsOnEdge, double initDistance, double distance)
	{
		double distanceDiagonal = distance*Math.sqrt(2);
		RubberWorld world  = new RubberWorld(800,600);
		RubberObject obj = new RubberObject();
		
		for (int y=0;y<numberPointsOnEdge;y++)
		{
			for(int x=0;x<numberPointsOnEdge;x++)
			{
				obj.addPoint(initX+x*initDistance,initY+y*initDistance,0,x>numberPointsOnEdge/2?10:-10);
			}
		}
		
		int index=numberPointsOnEdge+1;
		for (int y=1;y<numberPointsOnEdge-1;y++)
		{
			for(int x=1;x<numberPointsOnEdge-1;x++)
			{
				obj.linkPoints(index, index+1, distance);
				obj.linkPoints(index, index-1, distance);
				obj.linkPoints(index, index-numberPointsOnEdge, distance);
				obj.linkPoints(index, index+numberPointsOnEdge, distance);
				
				obj.linkPoints(index, index+(numberPointsOnEdge-1), distanceDiagonal);
				obj.linkPoints(index, index-(numberPointsOnEdge-1), distanceDiagonal);
				obj.linkPoints(index, index-(numberPointsOnEdge+1), distanceDiagonal);
				obj.linkPoints(index, index+(numberPointsOnEdge+1), distanceDiagonal);
				
				index++;
			}
			index+=2;
		}
		for (int y=0;y<numberPointsOnEdge-1;y++)
		{
			obj.linkPoints(y*numberPointsOnEdge, (y+1)*numberPointsOnEdge, distance);
			obj.linkPoints(y*numberPointsOnEdge+numberPointsOnEdge-1, (y+1)*numberPointsOnEdge+numberPointsOnEdge-1, distance);
		}
		
		for (int x=0;x<numberPointsOnEdge-1;x++)
		{
			obj.linkPoints(x, x+1, distance);
			obj.linkPoints((numberPointsOnEdge-1)*numberPointsOnEdge+x, (numberPointsOnEdge-1)*numberPointsOnEdge+x+1, distance);
		}
		
		world.addObject(obj);
		
		return world;
	}
	
	private void linkAllLessThanDistance(RubberObject obj, double distance)
	{
		for (RubberPoint point : obj.getPoints())
		{
			for (RubberPoint point1 : obj.getPoints())
			{
				
			}
		}
	}
}
