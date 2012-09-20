package home.mutantu.rubber.controller;

import home.mutantu.rubber.model.Constants;
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
	
	public static RubberWorld createOneRectangleObjectWorld(int initX,int initY,int numberPointsOnEdge, double distance)
	{
		double distanceDiagonal = distance*Math.sqrt(2);
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		RubberObject obj = new RubberObject();
		
		for (int y=0;y<numberPointsOnEdge;y++)
		{
			for(int x=0;x<numberPointsOnEdge;x++)
			{
				obj.addPoint(initX+x*distance,initY+y*distance,0,/*x>numberPointsOnEdge/2?10:-10*/30);
			}
		}
		
		linkAllLessThanDistance(obj, distanceDiagonal*2);
		
		world.addObject(obj);
		
		return world;
	}
	
	public static RubberWorld createOneRoundObjectWorld(int initX,int initY,int numberPointsOnDiameter, double distance)
	{
		double distanceDiagonal = distance*Math.sqrt(2);
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		RubberObject obj = new RubberObject();
		if (numberPointsOnDiameter%2==0)
		{
			numberPointsOnDiameter++;
		}
		RubberPoint center = obj.addPoint(initX, initY);
		
		double radius = distance*(numberPointsOnDiameter-1)/2;
		
		double initXRectangle = initX-radius;
		double initYRectangle = initY-radius;
		
		for (int y=0;y<numberPointsOnDiameter;y++)
		{
			for(int x=0;x<numberPointsOnDiameter;x++)
			{
				RubberPoint toAdd = new RubberPoint(0);
				toAdd.t0.x=initXRectangle+x*distance;
				toAdd.t0.y=initYRectangle+y*distance;
				if (center.getDistanceFrom(toAdd)<=radius+Constants.EPSILON)
				{
					obj.addPoint(initXRectangle+x*distance,initYRectangle+y*distance,0,/*x>numberPointsOnEdge/2?10:-10*/-20);
				}
			}
		}
		
		linkAllLessThanDistance(obj, distanceDiagonal*2);
		
		world.addObject(obj);
		
		return world;
	}
	
	private static void linkAllLessThanDistance(RubberObject obj, double maxDistance)
	{
		for (int x=0;x<obj.getPointCount();x++)
		{
			for (int y=0;y<obj.getPointCount();y++)
			{
				double actualDistance = obj.getPoint(x).getDistanceFrom(obj.getPoint(y));
				if (actualDistance<=maxDistance+Constants.EPSILON && x!=y)
				{
					obj.linkPoints(x, y, actualDistance);
				}
			}
		}
	}
}
