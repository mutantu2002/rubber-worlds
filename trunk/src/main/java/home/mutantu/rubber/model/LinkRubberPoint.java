package home.mutantu.rubber.model;


public class LinkRubberPoint
{
	public double distance;
	public RubberPoint point;
	public LinkRubberPoint(RubberPoint point, double distance)
	{
		this.point = point;
		this.distance = distance;
	}
}
