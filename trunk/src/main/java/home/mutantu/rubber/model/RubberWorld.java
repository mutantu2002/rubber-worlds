package home.mutantu.rubber.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RubberWorld
{
	List<RubberObject> objects = new ArrayList<RubberObject>();
	private int width = 800;
    private int height = 600;
    
	public RubberWorld(int width, int height)
	{
		super();
		this.width = width;
		this.height = height;
	}

	public void next()
	{
		for (RubberObject obj : objects) 
		{
			obj.next(this);
		}
	}
	
	public List<RubberObject> getObjects()
	{
		return Collections.unmodifiableList(objects);
	}

	public void addObject(RubberObject obj)
	{
		objects.add(obj);
	}

	public void flipCoordinates()
	{
		for (RubberObject obj : objects) 
		{
			obj.flipCoordinates();
		}		
	}

	public double getWidth()
	{
		return width;
	}
	public double getHeight()
	{
		return height;
	}
}
