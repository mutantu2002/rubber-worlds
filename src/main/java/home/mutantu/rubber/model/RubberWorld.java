package home.mutantu.rubber.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RubberWorld implements Serializable
{
	private static final long serialVersionUID = 4945277472151111878L;
	List<RubberObject> objects = new ArrayList<RubberObject>();
	private int width = 800;
    private int height = 600;
    public KeyboardState keyboardState;
    
	public RubberWorld(int width, int height)
	{
		super();
		this.width = width;
		this.height = height;
	}

	public void next(KeyboardState keysState)
	{
		this.keyboardState = keysState;
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

	public void addObjects(List<RubberObject> listObj)
	{
		for (RubberObject rubberObject : listObj)
		{
			objects.add(rubberObject);
		}
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

	public void removeObject(int index) 
	{
		if (objects.size()>0)
		{
			objects.remove(objects.size()-1);
		}
	}   
}
