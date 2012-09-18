package home.mutantu.rubber;

import home.mutantu.rubber.controller.WorldRunner;
import home.mutantu.rubber.model.RubberObject;
import home.mutantu.rubber.model.RubberWorld;
import home.mutantu.rubber.ui.WorldFrame;

public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RubberWorld world  = new RubberWorld();
		RubberObject obj = new RubberObject();
		obj.addPoint();
		obj.addPoint(100,110);
		obj.linkPoints(0, 1, 10);
		obj.linkPoints(0, 2, 10);
		obj.linkPoints(2, 1, 10);
		world.addObject(obj);
		WorldFrame frame = new WorldFrame();
		WorldRunner runner = new WorldRunner(world, frame);
		new Thread(runner).start();
	}

}
