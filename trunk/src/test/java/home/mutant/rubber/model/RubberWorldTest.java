package home.mutant.rubber.model;

import home.mutantu.rubber.model.RubberWorld;
import home.mutantu.rubber.model.StillRubberObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Assert;
import org.junit.Test;

public class RubberWorldTest 
{
	
	@Test
	public void testSerialization() throws Exception
	{
        FileOutputStream fos = new FileOutputStream("tempdata.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        RubberWorld world = new RubberWorld(300,300);
        StillRubberObject obj = new StillRubberObject();
        obj.addPoint(100, 0);
        world.addObject(obj);
        oos.writeObject(world);
        oos.close();
        
        FileInputStream fis = new FileInputStream("tempdata.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        RubberWorld newWorld = (RubberWorld) ois.readObject();
        ois.close();

        Assert.assertEquals(world.getHeight(), newWorld.getHeight());
        new File("tempdata.ser").delete();
	}
}
