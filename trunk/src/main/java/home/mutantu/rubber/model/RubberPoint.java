package home.mutantu.rubber.model;


import java.util.ArrayList;
import java.util.List;

public class RubberPoint
{
	public int index;
	public Coordinates t0 = new Coordinates(100,100,0,0);
	public Coordinates t1 = new Coordinates(100,100,0,0);
	
	List<LinkRubberPoint> links = new ArrayList<LinkRubberPoint>();
	
	public RubberPoint(int index)
	{
		this.index=index;
	}

	public void addLink(RubberPoint point, double distance)
	{
		links.add(new LinkRubberPoint(point, distance));
	}

	public double getDistanceFrom(RubberPoint point)
	{
		return Math.sqrt((t0.x-point.t0.x)*(t0.x-point.t0.x)+(t0.y-point.t0.y)*(t0.y-point.t0.y));
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
		euler();
		//rungeKutta2Order();
		checkLimits(rubberWorld);
	}

	private Force calculateForce(Coordinates coord)
	{
		Force force = new Force();
		for (LinkRubberPoint link : links)
		{
			double realDistance = Math.sqrt((coord.x-link.point.t0.x)*(coord.x-link.point.t0.x)+(coord.y-link.point.t0.y)*(coord.y-link.point.t0.y));
			double valForce = Constants.ELASTIC_CONSTANTS*(link.distance-realDistance);//*Math.abs((link.distance-realDistance));//*(link.distance-realDistance);
			if (realDistance==0)
			{
				realDistance=0.1;
			}
			force.fx+=(coord.x-link.point.t0.x)/realDistance * valForce;
			force.fy+=(coord.y-link.point.t0.y)/realDistance * valForce;
		}
		force.fy+=Constants.GRAVITY;
		force.fx-=coord.vx*Constants.FRICTION;
		force.fy-=coord.vy*Constants.FRICTION;
		
		return force;
	}

	private void checkLimits(RubberWorld rubberWorld)
	{
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
	
	private void euler()
	{
		Force force = calculateForce(t0);
		t1.vx= t0.vx + force.fx*Constants.DT;
		t1.vy= t0.vy + force.fy*Constants.DT;
		
		t1.x = t0.x + t1.vx*Constants.DT;
		t1.y = t0.y + t1.vy*Constants.DT;
	}
	
	@SuppressWarnings("unused")
	private void rungeKutta2Order()
	{
		Force forceN = calculateForce(t0);
		double vxN_1 = t0.vx+forceN.fx*Constants.DT;
		double vyN_1 = t0.vy+forceN.fy*Constants.DT;
		Coordinates tmpCoord = new Coordinates(t0.x+vxN_1*Constants.DT, t0.y+vyN_1*Constants.DT, vxN_1, vyN_1);
		Force forceN_1 = calculateForce(tmpCoord);
		
		System.out.println(forceN.fx+" "+forceN_1.fx);
		
		t1.vx= t0.vx + ((forceN.fx + forceN_1.fx)/2.)*Constants.DT;
		t1.vy= t0.vy + ((forceN.fy + forceN_1.fy)/2.)*Constants.DT;
		
		t1.x = t0.x + ((t0.vx+vxN_1)/2.)*Constants.DT;
		t1.y = t0.y + ((t0.vy+vyN_1)/2.)*Constants.DT;
	}
}
