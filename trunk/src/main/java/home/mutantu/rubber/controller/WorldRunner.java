package home.mutantu.rubber.controller;

import home.mutantu.rubber.model.RubberWorld;
import home.mutantu.rubber.ui.WorldFrame;

public class WorldRunner implements Runnable
{
	RubberWorld world;
	WorldFrame frame;
	int pauseMilisec = 100;
	
	public WorldRunner(RubberWorld world,WorldFrame frame)
	{
		this.world = world;
		this.frame = frame;
	}
	@Override
	public void run()
	{
		try
		{
			Thread.sleep(pauseMilisec);
		}
		catch (InterruptedException e)
		{
		}
		world.next();
		frame.buildFrame(world);
	}

}
