package home.mutantu.rubber.model;


import home.mutantu.rubber.util.ConvexHull;
import home.mutantu.rubber.util.LinesUtil;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RubberObject 
{
	Map<Integer, RubberPoint> points = new HashMap<Integer,RubberPoint>();
	List<RubberPoint> contour = new ArrayList<RubberPoint>();
	Point center = new Point();
	
	public int getPointCount()
	{
		return points.size();
	}

	public synchronized void addPoint(RubberObject parent)
	{
		RubberPoint point = new RubberPoint(points.size(), parent);
		point.t0.x=100+10*(points.size());
		point.t0.y=100+10*(points.size());
		points.put(points.size(),point);
	}
	
	public synchronized RubberPoint addPoint(int x, int y, RubberObject parent)
	{
		RubberPoint point = new RubberPoint(points.size(), parent);
		point.t0.x=x;
		point.t0.y=y;
		points.put(points.size(),point);
		return point;
	}

	public synchronized void addPoint(double d, double e, double vx, double vy, RubberObject parent)
	{
		int size = points.size();
		RubberPoint point = new RubberPoint(size, parent);
		point.t0.x=d;
		point.t0.y=e;
		
		point.t0.vx=vx;
		point.t0.vy=vy;
		points.put(size,point);
	}
	
	public void linkPoints(int index1, int index2, double distance)
	{
		if (index1>=points.size() || index2>= points.size())
		{
			throw new IndexOutOfBoundsException();
		}
		if (areLinked(index1, index2))
		{
			return;
		}
		points.get(index1).addLink(points.get(index2),distance);
		points.get(index2).addLink(points.get(index1),distance);
	}

	public boolean areLinked(int index1, int index2)
	{
		if (index1>=points.size() || index2>= points.size())
		{
			throw new IndexOutOfBoundsException();
		}
		return points.get(index1).isLinkedTo(index2);
	}

	public void next(RubberWorld rubberWorld)
	{
		for (Integer pointIndex : points.keySet())
		{
			points.get(pointIndex).next(rubberWorld);
		}
		contour = ConvexHull.compute(getPoints());
	}

	public List<RubberPoint> get2ClosestPoints(Coordinates coord)
	{
		List<RubberPoint> result = new ArrayList<RubberPoint>();
		double distance = Double.MAX_VALUE;
		int index = 0;
		for (int i=0;i<contour.size();i++)
		{
			double distanceTmp = LinesUtil.distance(coord, contour.get(i).t0);
			if (distance>distanceTmp)
			{
				distance = distanceTmp;
				index = i;
			}
		}
		double distanceBack  = LinesUtil.distance(coord, contour.get((index+contour.size()-1)%contour.size()).t0);
		double distanceFront  = LinesUtil.distance(coord, contour.get((index+1)%contour.size()).t0);
		if (distanceBack<distanceFront)
		{
			result.add(contour.get((index+contour.size()-1)%contour.size()));
			result.add(contour.get(index));
		}
		else
		{
			result.add(contour.get(index));
			result.add(contour.get((index+1)%contour.size()));
		}
		return result;
	}
	public Coordinates closestPointToContour(Coordinates coord)
	{
		List<RubberPoint> closer = get2ClosestPoints(coord);
		return LinesUtil.closestPointToSegment(coord, closer.get(0).t0, closer.get(1).t0);
	}
	public boolean isInside(Coordinates coord)
	{
		Polygon contourPolygon = new Polygon();
		for (RubberPoint pointContour : contour)
		{
			contourPolygon.addPoint((int)pointContour.t0.x, (int)pointContour.t0.y);
		}
		return contourPolygon.contains(coord.x, coord.y);
	}
	public List<RubberPoint> getPoints()
	{
		return Collections.unmodifiableList(new ArrayList<RubberPoint>(points.values()));
	}

	public RubberPoint getPoint(int index )
	{
		return points.get(index);
	}
	
	public void flipCoordinates()
	{
		for (Integer pointIndex : points.keySet())
		{
			points.get(pointIndex).flipCoordonates();
		}
	}

	public List<RubberPoint> getContour()
	{
		return Collections.unmodifiableList(contour);
	}
}
