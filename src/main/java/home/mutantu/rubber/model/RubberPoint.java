package home.mutantu.rubber.model;


import java.util.ArrayList;
import java.util.List;

public class RubberPoint
{
	public int index;
	public Coordinates t0 = new Coordinates(100,100,0,0);
	public Coordinates t1 = new Coordinates(100,100,0,0);
	public static final double K=10;
	public static final double dt=0.01;
	
	List<LinkRubberPoint> links = new ArrayList<LinkRubberPoint>();
	
	public RubberPoint(int index)
	{
		this.index=index;
	}

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
			forceX+=(t0.x-link.point.t0.x)/realDistance * force;
			forceY+=(t0.y-link.point.t0.y)/realDistance * force;
		}
		t1.vx= t0.vx + forceX*dt;
		t1.vy= t0.vy + forceY*dt;
		
		t1.x = t0.x + t1.vx*dt;
		t1.y = t0.y + t1.vy*dt;
	}

	public void flipCoordonates()
	{
		t0.x=t1.x;
		t0.y=t1.y;
		t0.vx=t1.vx;
		t0.vy=t1.vy;
	}
}
