package home.mutantu.rubber;

import home.mutantu.rubber.controller.WorldFactory;
import home.mutantu.rubber.controller.WorldRunner;
import home.mutantu.rubber.ui.WorldFrame;

public class Main
{
	public static void main(String[] args)
	{
		new Thread(new WorldRunner(WorldFactory.createOneRectangleWorld(), new WorldFrame())).start();
	}
}
