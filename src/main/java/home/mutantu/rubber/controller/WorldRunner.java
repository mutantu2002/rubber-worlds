package home.mutantu.rubber.controller;

import home.mutantu.rubber.model.RubberWorld;
import home.mutantu.rubber.ui.WorldFrame;

public class WorldRunner implements Runnable
{
	RubberWorld world;
	WorldFrame frame;
	int pauseMilisec = 3;
	private boolean running = true;
	
	public WorldRunner(RubberWorld world,WorldFrame frame)
	{
		this.world = world;
		this.frame = frame;
	}
	@Override
	public void run()
	{
		int frames=0;
		long t0=System.currentTimeMillis();
		while(running)
		{
			try
			{
				Thread.sleep(pauseMilisec);
			}
			catch (InterruptedException e)
			{
			}
			world.next();
			world.flipCoordinates();
			frame.buildFrame(world);
			frames++;
			
			if (frames==100)
			{
				long t1 = System.currentTimeMillis();
				double framesperSec = (double)100/((double)(t1-t0)/1000.);
				t0=System.currentTimeMillis();
				frames=0;
				frame.setTitle(new Double(framesperSec).toString());
			}
			
		}
	}

}
