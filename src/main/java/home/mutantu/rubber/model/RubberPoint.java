package home.mutantu.rubber.model;


import java.util.ArrayList;
import java.util.List;

public class RubberPoint
{
	public int index;
	List<LinkRubberPoint> links = new ArrayList<LinkRubberPoint>();
	
	public void addLink(int index, int distance)
	{
		links.add(new LinkRubberPoint(index, distance));
	}

	public boolean isLinkedTo(int index)
	{
		for (LinkRubberPoint link : links)
		{
			if (index == link.index)
			{
				return true;
			}
		}
		return false;
	}
}
