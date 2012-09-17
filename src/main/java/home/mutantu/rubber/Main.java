package home.mutantu.rubber;

import home.mutantu.rubber.controller.WorldRunner;
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
		WorldFrame frame = new WorldFrame();
		WorldRunner runner = new WorldRunner(world, frame);
		new Thread(runner).start();
	}

}
