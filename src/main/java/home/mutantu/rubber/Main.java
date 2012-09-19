package home.mutantu.rubber;

import home.mutantu.rubber.controller.WorldFactory;
import home.mutantu.rubber.controller.WorldRunner;
import home.mutantu.rubber.ui.WorldFrame;

public class Main
{
	public static void main(String[] args)
	{
		new Thread(new WorldRunner(WorldFactory.createOneRectangleObjectWorld(200,50,15,13,13), new WorldFrame(800,600))).start();
	}
}
