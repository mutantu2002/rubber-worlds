package home.mutantu.rubber;

import home.mutantu.rubber.controller.WorldFactory;
import home.mutantu.rubber.controller.WorldRunner;
import home.mutantu.rubber.model.Constants;
import home.mutantu.rubber.ui.WorldFrame;

/*
 * Idei
   	- fiecare obiect sa fie pe un thread separat - un obiect nu modifica, ci numai citeste coordonatele altuia
	- obiecte concave (un om de exemplu) sa fie format din mai multe obiecte convexe care vor avea puncte comune - articulatii
	- userul sa poate desena obiecte care apoi sa prinda viata
	- sa afisez temperatura, si sa controlez temperaturi initiala
	- mai multe tipuri de celule, fiecare pereche cu alt tip de interactiune intre ele
 */
public class Main
{

	public static void main(String[] args)
	{
		//new Thread(new WorldRunner(WorldFactory.createOneRectangleObjectWorld(200,50,15,13), new WorldFrame(Constants.WIDTH,Constants.HEIGHT))).start();
		//new Thread(new WorldRunner(WorldFactory.createOneRoundObjectWorld(400,300,20,10), new WorldFrame(Constants.WIDTH,Constants.HEIGHT))).start();
		new Thread(new WorldRunner(WorldFactory.create3ObjectsWorld(100,100,10,10), new WorldFrame(Constants.WIDTH,Constants.HEIGHT))).start();
	}
}
