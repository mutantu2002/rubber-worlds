package home.mutantu.rubber.model;


import home.mutantu.rubber.util.LinesUtil;

import java.util.ArrayList;
import java.util.List;

public class RubberPoint
{
	public int index;
	public Coordinates t0 = new Coordinates(100,100,0,0);
	public Coordinates t1 = new Coordinates(100,100,0,0);
	private List<RubberObject> parents = new ArrayList<RubberObject>();
	List<LinkRubberPoint> links = new ArrayList<LinkRubberPoint>();
	private boolean nextDone = false;
	
	public RubberPoint(int index, RubberObject parent)
	{
		this.index=index;
		this.parents.add(parent);
	}

	public void addLink(RubberPoint point, double distance)
	{
		links.add(new LinkRubberPoint(point, distance));
	}

	public void addParent(RubberObject parent)
	{
		for (RubberObject existingParent : parents)
		{
			if (existingParent==parent)
			{
				return;
			}
		}
		this.parents.add(parent);
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
	
	public boolean isLinkedTo(RubberPoint rubberPoint)
	{
		for (LinkRubberPoint link : links)
		{
			if (rubberPoint == link.point)
			{
				return true;
			}
		}
		return false;
	}
	
	public void next(RubberWorld rubberWorld)
	{
		if (nextDone)
		{
			return;
		}
		euler(rubberWorld.isDownPressed);
		//rungeKutta2Order();
		checkCollisions(rubberWorld);
		checkLimits(rubberWorld);
		nextDone = true;
	}

	private Force calculateForce(Coordinates coord, boolean isDownPressed)
	{
		Force force = new Force();
		for (LinkRubberPoint link : links)
		{
			double realDistance = LinesUtil.distance(coord, link.point.t0);
			double valForce = Constants.ELASTIC_CONSTANTS*(link.distance-realDistance);//*Math.abs((link.distance-realDistance));//*(link.distance-realDistance);
			if (realDistance==0)
			{
				realDistance=0.1;
			}
			force.fx+=(coord.x-link.point.t0.x)/realDistance * valForce;
			force.fy+=(coord.y-link.point.t0.y)/realDistance * valForce;
		}

		if (isDownPressed)
		{
			force.fy+=Constants.GRAVITY*8;
		}
		else
		{
			force.fy+=Constants.GRAVITY;
		}
		force.fx-=coord.vx*Constants.FRICTION;
		force.fy-=coord.vy*Constants.FRICTION;
		
		return force;
	}

	private boolean hasParent(RubberObject obj)
	{
		for (RubberObject object : parents)
		{
			if (object==obj)
			{
				return true;
			}
		}
		return false;
	}
	private void checkCollisions(RubberWorld rubberWorld)
	{
		for (RubberObject obj : rubberWorld.getObjects())
		{
			if (hasParent(obj))
			{
				continue;
			}
			if (obj.isInside(t1))
			{
				t1 = obj.closestPointToContour(t1);
			}
		}
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
		nextDone = false;
	}
	
	private void euler(boolean isDownPressed)
	{
		Force force = calculateForce(t0, isDownPressed);
		t1.vx= t0.vx + force.fx*Constants.DT;
		t1.vy= t0.vy + force.fy*Constants.DT;
		
		t1.x = t0.x + t1.vx*Constants.DT;
		t1.y = t0.y + t1.vy*Constants.DT;
	}
	
	@SuppressWarnings("unused")
	private void rungeKutta2Order()
	{
		Force forceN = calculateForce(t0, false);
		double vxN_1 = t0.vx+forceN.fx*Constants.DT;
		double vyN_1 = t0.vy+forceN.fy*Constants.DT;
		Coordinates tmpCoord = new Coordinates(t0.x+vxN_1*Constants.DT, t0.y+vyN_1*Constants.DT, vxN_1, vyN_1);
		Force forceN_1 = calculateForce(tmpCoord, false);
		
		System.out.println(forceN.fx+" "+forceN_1.fx);
		
		t1.vx= t0.vx + ((forceN.fx + forceN_1.fx)/2.)*Constants.DT;
		t1.vy= t0.vy + ((forceN.fy + forceN_1.fy)/2.)*Constants.DT;
		
		t1.x = t0.x + ((t0.vx+vxN_1)/2.)*Constants.DT;
		t1.y = t0.y + ((t0.vy+vyN_1)/2.)*Constants.DT;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((t0 == null) ? 0 : t0.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RubberPoint other = (RubberPoint) obj;
		if (t0 == null)
		{
			if (other.t0 != null)
				return false;
		} else if (!t0.equals(other.t0))
			return false;
		return true;
	}
}
