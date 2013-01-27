package home.mutantu.rubber.controller;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

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
	
	public static RubberWorld createFromFile(String path)
	{
		RubberWorld world=null;;
        FileInputStream fis;
		try 
		{
			fis = new FileInputStream(path);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        world = (RubberWorld) ois.readObject();
	        ois.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if (world == null) world = new RubberWorld(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
		RubberObject circle = createCircle(200, 100, 8, 20);
		circle.controllable=true;
		world.addObject(circle);
		
		return world;

	}
	public static RubberWorld createOneSquareObjectWorld(int initX,int initY,int numberPointsOnEdge, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
		world.addObject(createSquare(initX, initY, numberPointsOnEdge, distance));
		return world;
	}
	
	public static RubberWorld createOneRectangleObjectWorld(int initX,int initY,int numberPointsOnW, int numberPointsOnH, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
		world.addObject(createRectangle(initX, initY, numberPointsOnW, numberPointsOnH, distance));
		return world;
	}
	
	public static RubberWorld createOneRoundObjectWorld(int initX,int initY,int numberPointsOnDiameter, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
		world.addObject(createCircle(initX, initY, numberPointsOnDiameter, distance));
		return world;
	}
	
	public static RubberWorld create2ObjectsWorld(int initX,int initY,int numberPoints, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
		world.addObject(createCircle(initX+200, initY, numberPoints, distance));
		world.addObject(createSquare(initX, initY, numberPoints, distance));
		return world;
	}
	
	public static RubberWorld create3ObjectsWorld(int initX,int initY,int numberPoints, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
		RubberObject circle = createCircle(initX+200, initY, numberPoints, distance);
		circle.controllable=true;
		world.addObject(circle);
		world.addObject(createStillRectangle(300, 300, 900, 400));
		world.addObject(createStillRectangle(-400, 0, -300, 800));
		world.addObject(createStillRectangle(2100, 0, 2000, 800));
		
		world.addObject(createStill());
		
//		world.addObject(createCircle(initX+200, initY+105, numberPoints, distance));
//		world.addObject(createCircle(initX+200, initY+220, numberPoints, distance));
//		world.addObject(createSquare(initX+350, initY-100, numberPoints, distance));
//		world.addObject(createCircle(initX+350, initY+105, numberPoints, distance));
//		world.addObject(createSquare(initX+350, initY+220, numberPoints, distance));
//		world.addObject(createSquare(initX, initY, numberPoints, distance));
//		world.addObject(createSquare(initX, initY+105, numberPoints, distance));
		world.addObject(createSquare(initX, initY+220, numberPoints, distance));
		world.addObject(createSquare(initX-100, initY, numberPoints, distance));
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
		rect.addPoint(0, 800);
		rect.addPoint(0, 700);
		rect.addPoint(1600, 800);
		rect.addPoint(1600, 600);
		rect.addPoint(1300, 600);
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
		RubberWorld world  = new RubberWorld(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
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
	
	public static RubberWorld createOneParallelogramObjectWorld(int initX,int initY,int numberPointsOnW, int numberPointsOnH, double distance)
	{
		RubberWorld world  = new RubberWorld(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
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
