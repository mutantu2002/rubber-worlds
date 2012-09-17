package home.mutantu.rubber.model;


import java.util.ArrayList;
import java.util.List;

public class RubberPoint
{
	public int index;
	public Coordinates t0;
	public Coordinates t1;
	public static final double K=10;
	
	List<LinkRubberPoint> links = new ArrayList<LinkRubberPoint>();
	
	public void addLink(RubberPoint point, int distance)
	{
		links.add(new LinkRubberPoint(point, distance));
	}

	public boolean isLinkedTo(int index)
	{
		for (LinkRubberPoint link : links)
		{
			if (index == link.point.index)
			{
				return true;
			}
		}
		return false;
	}
	public void next()
	{
		double forceX=0;
		double forceY=0;
		for (LinkRubberPoint link : links)
		{
			double realDistance = Math.sqrt((t0.x-link.point.t0.x)*(t0.x-link.point.t0.x)+(t0.y-link.point.t0.y)*(t0.y-link.point.t0.y));
			double force = K*(link.distance-realDistance);
		}
	}
}
