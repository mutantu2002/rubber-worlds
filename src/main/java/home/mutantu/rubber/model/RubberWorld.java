package home.mutantu.rubber.model;

import java.util.ArrayList;
import java.util.List;

public class RubberWorld
{
	List<RubberObject> objects = new ArrayList<RubberObject>();
	public void next()
	{
		for (RubberObject obj : objects) 
		{
			obj.next(this);
		}
	}

}
