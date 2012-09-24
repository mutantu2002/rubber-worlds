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
		obj.addPoint(obj);
		obj.addPoint(obj);
		obj.addPoint(100,110,obj);
		obj.linkPoints(0, 1, 10);
		obj.linkPoints(0, 2, 10);
		obj.linkPoints(2, 1, 10);
		world.addObject(obj);
		
		return world;
	}
	
	public static RubberWorld createOneRectangleObjectWorld(int initX,int initY,int numberPointsOnEdge, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		world.addObject(createRectangle(initX, initY, numberPointsOnEdge, distance));
		return world;
	}
	
	public static RubberWorld createOneRoundObjectWorld(int initX,int initY,int numberPointsOnDiameter, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		world.addObject(createCircle(initX, initY, numberPointsOnDiameter, distance));
		return world;
	}
	
	public static RubberWorld create2ObjectsWorld(int initX,int initY,int numberPoints, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		world.addObject(createCircle(initX+200, initY, numberPoints, distance));
		world.addObject(createRectangle(initX, initY, numberPoints, distance));
		return world;
	}
	
	public static RubberWorld create3ObjectsWorld(int initX,int initY,int numberPoints, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		world.addObject(createCircle(initX+200, initY, numberPoints, distance));
		world.addObject(createCircle(initX+200, initY+105, numberPoints, distance));
		world.addObject(createCircle(initX+200, initY+220, numberPoints, distance));
		world.addObject(createRectangle(initX+350, initY-100, numberPoints, distance));
		world.addObject(createCircle(initX+350, initY+105, numberPoints, distance));
		world.addObject(createRectangle(initX+350, initY+220, numberPoints, distance));
		world.addObject(createRectangle(initX, initY, numberPoints, distance));
		world.addObject(createRectangle(initX, initY+105, numberPoints, distance));
		world.addObject(createRectangle(initX, initY+220, numberPoints, distance));
		world.addObject(createRectangle(initX-100, initY, numberPoints, distance));
		world.addObject(createRectangle(initX-100, initY+105, numberPoints, distance));
		world.addObject(createRectangle(initX-100, initY+220, numberPoints, distance));
		return world;
	}
	
	public static RubberObject createCircle(int initX,int initY,int numberPointsOnDiameter, double distance)
	{
		double distanceDiagonal = distance*Math.sqrt(2);
		RubberObject obj = new RubberObject();
		if (numberPointsOnDiameter%2==0)
		{
			numberPointsOnDiameter++;
		}
		RubberPoint center = obj.addPoint(initX, initY, obj);
		
		double radius = distance*(numberPointsOnDiameter-1)/2;
		double initXRectangle = initX-radius;
		double initYRectangle = initY-radius;
		
		for (int y=0;y<numberPointsOnDiameter;y++)
		{
			for(int x=0;x<numberPointsOnDiameter;x++)
			{
				RubberPoint toAdd = new RubberPoint(0,obj);
				toAdd.t0.x=initXRectangle+x*distance;
				toAdd.t0.y=initYRectangle+y*distance;
				if (center.getDistanceFrom(toAdd)<=radius+Constants.EPSILON)
				{
					obj.addPoint(initXRectangle+x*distance,initYRectangle+y*distance,10,/*x>numberPointsOnEdge/2?10:-10*/0,obj);
				}
			}
		}
		linkAllLessThanDistance(obj, distanceDiagonal*3);
		return obj;
	}
	public static RubberObject createRectangle(int initX,int initY,int numberPointsOnEdge, double distance)
	{
		double distanceDiagonal = distance*Math.sqrt(2);
		RubberObject obj = new RubberObject();
		
		for (int y=0;y<numberPointsOnEdge;y++)
		{
			for(int x=0;x<numberPointsOnEdge;x++)
			{
				obj.addPoint(initX+x*distance,initY+y*distance,20,/*x>numberPointsOnEdge/2?10:-10*/0,obj);
			}
		}
		linkAllLessThanDistance(obj, distanceDiagonal*3);
		return obj;
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
