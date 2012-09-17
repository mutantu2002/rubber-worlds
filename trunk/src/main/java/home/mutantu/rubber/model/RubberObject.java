package home.mutantu.rubber.model;


import java.util.HashMap;
import java.util.Map;

public class RubberObject 
{
	Map<Integer, RubberPoint> points = new HashMap<Integer,RubberPoint>();
	
	public RubberObject()
	{
		addPoint();
	}

	public Object getPointCount()
	{
		return points.size();
	}

	public synchronized void addPoint()
	{
		points.put(points.size(),new RubberPoint());
	}

	public void linkPoints(int index1, int index2, int distance)
	{
		if (index1>=points.size() || index2>= points.size())
		{
			throw new IndexOutOfBoundsException();
		}
		
		points.get(index1).addLink(points.get(index1),distance);
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
			
		}
		
	}
}
