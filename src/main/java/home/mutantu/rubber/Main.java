package home.mutantu.rubber;

import home.mutantu.rubber.controller.WorldFactory;
import home.mutantu.rubber.controller.WorldRunner;
import home.mutantu.rubber.model.Constants;
import home.mutantu.rubber.ui.WorldFrame;

public class Main
{
	//fiecare obiect sa fie pe un thread separat - un obiect nu modifica, ci numai citeste coordonatele altuia
	//obiecte concave (un om de exemplu) sa fie format din mai multe obiecte convexe care vor avea puncte comune - articulatii
	public static void main(String[] args)
	{
		//new Thread(new WorldRunner(WorldFactory.createOneRectangleObjectWorld(200,50,15,13), new WorldFrame(Constants.WIDTH,Constants.HEIGHT))).start();
		//new Thread(new WorldRunner(WorldFactory.createOneRoundObjectWorld(400,300,20,10), new WorldFrame(Constants.WIDTH,Constants.HEIGHT))).start();
		new Thread(new WorldRunner(WorldFactory.create2ObjectsWorld(100,100,10,10), new WorldFrame(Constants.WIDTH,Constants.HEIGHT))).start();
	}
}
