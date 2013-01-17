package home.mutantu.rubber.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import home.mutantu.rubber.model.Constants;
import home.mutantu.rubber.model.RubberObject;
import home.mutantu.rubber.model.RubberPoint;
import home.mutantu.rubber.model.RubberWorld;
import home.mutantu.rubber.model.StillRubberObject;

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
	
	public static RubberWorld createOneSquareObjectWorld(int initX,int initY,int numberPointsOnEdge, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		world.addObject(createSquare(initX, initY, numberPointsOnEdge, distance));
		return world;
	}
	
	public static RubberWorld createOneRectangleObjectWorld(int initX,int initY,int numberPointsOnW, int numberPointsOnH, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		world.addObject(createRectangle(initX, initY, numberPointsOnW, numberPointsOnH, distance));
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
		world.addObject(createSquare(initX, initY, numberPoints, distance));
		return world;
	}
	
	public static RubberWorld create3ObjectsWorld(int initX,int initY,int numberPoints, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		world.addObject(createCircle(initX+200, initY, numberPoints, distance));
		world.addObject(createStillRectangle(300, 300, 900, 400));
		
		world.addObject(createStill());
		
//		world.addObject(createCircle(initX+200, initY+105, numberPoints, distance));
//		world.addObject(createCircle(initX+200, initY+220, numberPoints, distance));
//		world.addObject(createSquare(initX+350, initY-100, numberPoints, distance));
//		world.addObject(createCircle(initX+350, initY+105, numberPoints, distance));
//		world.addObject(createSquare(initX+350, initY+220, numberPoints, distance));
//		world.addObject(createSquare(initX, initY, numberPoints, distance));
//		world.addObject(createSquare(initX, initY+105, numberPoints, distance));
		//world.addObject(createSquare(initX, initY+220, numberPoints, distance));
		//world.addObject(createSquare(initX-100, initY, numberPoints, distance));
		//world.addObject(createSquare(initX-100, initY+105, numberPoints, distance));
		//world.addObject(createSquare(initX-100, initY+220, numberPoints, distance));
		return world;
	}
	
	public static StillRubberObject createStillRectangle(int x1, int y1, int x2, int y2)
	{
		StillRubberObject rect = new StillRubberObject();
		rect.addPoint(x1, y1, rect);
		rect.addPoint(x1, y2, rect);
		rect.addPoint(x2, y1, rect);
		rect.addPoint(x2, y2, rect);
		rect.computeContour();
		return rect;
	}
	
	public static StillRubberObject createStill()
	{
		StillRubberObject rect = new StillRubberObject();
		rect.addPoint(0, 800, rect);
		rect.addPoint(0, 700, rect);
		rect.addPoint(1500, 800, rect);
		rect.addPoint(1500, 400, rect);
		rect.computeContour();
		return rect;
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
					obj.addPoint(initXRectangle+x*distance,initYRectangle+y*distance,10,/*x>numberPointsOnEdge/2?10:-10*/0);
				}
			}
		}
		linkAllLessThanDistance(obj, distanceDiagonal*3);
		return obj;
	}
	public static RubberObject createSquare(int initX,int initY,int numberPointsOnEdge, double distance)
	{
		return createRectangle(initX, initY, numberPointsOnEdge, numberPointsOnEdge, distance);
	}
	
	public static RubberObject createRectangle(int initX,int initY,int numberPointsOnW,int numberPointsOnH, double distance)
	{
		double distanceDiagonal = distance*Math.sqrt(2);
		RubberObject obj = new RubberObject();
		
		for (int y=0;y<numberPointsOnH;y++)
		{
			for(int x=0;x<numberPointsOnW;x++)
			{
				obj.addPoint(initX+x*distance,initY+y*distance,20,0);
			}
		}
		linkAllLessThanDistance(obj, distanceDiagonal*3);
		return obj;
	}
	
	public static RubberWorld createWorldFromImage(int initX,int initY, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		world.addObject(createObjectFromImage("/om1.bmp", initX, initY, distance));
		return world;
	}
	
	public static RubberObject createObjectFromImage(String imageResourcePath, int initX,int initY, double distance)
	{
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(WorldFactory.class.getResourceAsStream(imageResourcePath));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
		double distanceDiagonal = distance*Math.sqrt(2);
		RubberObject obj = new RubberObject();
		
		for (int y=0;y<image.getHeight();y++)
		{
			for(int x=0;x<image.getWidth();x++)
			{
				int color = image.getRGB(x,y) & 0x00ffffff;
				if (color == 0x00ffffff)
				{
					obj.addPoint(initX+x*distance,initY+y*distance,20,0);
				}
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
	
	public static RubberWorld create2ConnectedObjectsWorld(int initX,int initY,int numberPoints, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		world.addObjects(create2connectedRectangles(100, 121, 30, 10, 121, 100, 10, 30, distance));
		return world;
	}
	
	public static RubberObject createConnectedRectangle(int initX,int initY,int numberPointsOnW,int numberPointsOnH, double distance, RubberObject friend)
	{
		RubberObject obj = new RubberObject();
		for (int y=0;y<numberPointsOnH;y++)
		{
			for(int x=0;x<numberPointsOnW;x++)
			{
				RubberPoint point = null;
				if (friend != null)
				{
					point = friend.hasPoint(initX+x*distance, initY+y*distance);
				}
				if (point!=null)
				{
					obj.addPoint(point);
				}
				else
				{
					obj.addPoint(initX+x*distance,initY+y*distance,20,0,friend);
				}
			}
		}
		return obj;
	}
	
	private static List<RubberObject> create2connectedRectangles( int initX1,int initY1,int numberPointsOnW1,int numberPointsOnH1,
																  int initX2,int initY2,int numberPointsOnW2,int numberPointsOnH2, double distance)
	{
		double distanceDiagonal = distance*Math.sqrt(2);
		RubberObject obj1 = new RubberObject();
		RubberObject obj2 = new RubberObject();
		
		for (int y=0;y<numberPointsOnH1;y++)
		{
			for(int x=0;x<numberPointsOnW1;x++)
			{
				obj1.addPoint(initX1+x*distance,initY1+y*distance,20,0,obj2);
			}
		}
		
		for (int y=0;y<numberPointsOnH2;y++)
		{
			for(int x=0;x<numberPointsOnW2;x++)
			{
				RubberPoint point = obj1.hasPoint(initX2+x*distance, initY2+y*distance);
				if (point!=null)
				{
					obj2.addPoint(point);
				}
				else
				{
					obj2.addPoint(initX2+x*distance,initY2+y*distance,20,0,obj1);
				}
			}
		}
		linkAllLessThanDistance(obj1, distanceDiagonal*3);
		linkAllLessThanDistance(obj2, distanceDiagonal*3);
		
		List<RubberObject> list = new ArrayList<RubberObject>();
		list.add(obj1);
		list.add(obj2);
		return list;
	}
	
	public static RubberWorld createOneParallelogramObjectWorld(int initX,int initY,int numberPointsOnW, int numberPointsOnH, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WIDTH,Constants.HEIGHT);
		world.addObject(createParallelogram(initX, initY, numberPointsOnW, numberPointsOnH, distance,false));
		return world;
	}
	
	public static RubberObject createParallelogram(int initX,int initY,int numberPointsOnW,int numberPointsOnH, double distance, boolean directionRight)
	{
		double distanceDiagonal = distance*Math.sqrt(2);
		RubberObject obj = new RubberObject();
		int dir = -1;
		if (directionRight)
		{
			dir=1;
		}
		for (int y=0;y<numberPointsOnH;y++)
		{
			for(int x=0;x<numberPointsOnW;x++)
			{
				obj.addPoint(initX+dir*y*distance+x*distance,initY+y*distance,20,0);
			}
		}
		linkAllLessThanDistance(obj, distanceDiagonal*3);
		return obj;
	}
}
