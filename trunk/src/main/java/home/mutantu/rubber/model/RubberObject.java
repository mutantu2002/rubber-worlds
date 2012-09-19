package home.mutantu.rubber.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RubberObject 
{
	Map<Integer, RubberPoint> points = new HashMap<Integer,RubberPoint>();
	
	public RubberObject()
	{
	}

	public Object getPointCount()
	{
		return points.size();
	}

	public synchronized void addPoint()
	{
		RubberPoint point = new RubberPoint(points.size());
		point.t0.x=100+10*(points.size());
		point.t0.y=100+10*(points.size());
		points.put(points.size(),point);
	}
	
	public synchronized void addPoint(int x, int y)
	{
		RubberPoint point = new RubberPoint(points.size());
		point.t0.x=x;
		point.t0.y=y;
		points.put(points.size(),point);
	}

	public synchronized void addPoint(double d, double e, double vx, double vy)
	{
		RubberPoint point = new RubberPoint(points.size());
		point.t0.x=d;
		point.t0.y=e;
		
		point.t0.vx=vx;
		point.t0.vy=vy;
		points.put(points.size(),point);
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
	}
	
	public List<RubberPoint> getPoints()
	{
		return Collections.unmodifiableList(new ArrayList<RubberPoint>(points.values()));
	}

	public void flipCoordinates()
	{
		for (Integer pointIndex : points.keySet())
		{
			points.get(pointIndex).flipCoordonates();
		}
	}
}