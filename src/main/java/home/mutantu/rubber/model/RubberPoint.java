package home.mutantu.rubber.model;


import java.util.ArrayList;
import java.util.List;

public class RubberPoint
{
	public int index;
	public Coordinates t0 = new Coordinates(100,100,0,0);
	public Coordinates t1 = new Coordinates(100,100,0,0);
	public static final double FRICTION = 0.0;
	public static final double GRAVITY = 0.01;
	public static final double K=10;
	public static final double dt=0.007;
	
	List<LinkRubberPoint> links = new ArrayList<LinkRubberPoint>();
	
	public RubberPoint(int index)
	{
		this.index=index;
	}

	public void addLink(RubberPoint point, double distance)
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
	public void next(RubberWorld rubberWorld)
	{
		double forceX=0;
		double forceY=0;
		for (LinkRubberPoint link : links)
		{
			double realDistance = Math.sqrt((t0.x-link.point.t0.x)*(t0.x-link.point.t0.x)+(t0.y-link.point.t0.y)*(t0.y-link.point.t0.y));
			double force = K*(link.distance-realDistance)*Math.abs((link.distance-realDistance));//*(link.distance-realDistance);
			if (realDistance==0)
			{
				realDistance=0.1;
			}
			forceX+=(t0.x-link.point.t0.x)/realDistance * force;
			forceY+=(t0.y-link.point.t0.y)/realDistance * force;
			forceY+=GRAVITY;
		}
		
		t1.vx= t0.vx + (forceX - t0.vx*FRICTION)*dt;
		t1.vy= t0.vy + (forceY - t0.vy*FRICTION)*dt;
		
		t1.x = t0.x + t1.vx*dt;
		t1.y = t0.y + t1.vy*dt;
		
		if (t1.x<0)
		{
			t1.x=0;
			t1.vx=0;
		}
		if (t1.y<0)
		{
			t1.y=0;
			t1.vy=0;
		}
		if (t1.x>=rubberWorld.getWidth()-1)
		{
			t1.x=rubberWorld.getWidth()-2;
			t1.vx=0;
		}
		if (t1.y>=rubberWorld.getHeight()-1)
		{
			t1.y=rubberWorld.getHeight()-2;
			t1.vy=0;
		}
	}

	public void flipCoordonates()
	{
		t0.x=t1.x;
		t0.y=t1.y;
		t0.vx=t1.vx;
		t0.vy=t1.vy;
	}
}
