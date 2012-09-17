package home.mutant.rubber.model;

import static org.junit.Assert.*;
import home.mutantu.rubber.model.RubberObject;

import org.junit.Test;

public class RubberObjectTest
{

	@Test
	public void testAddPoint()
	{
		RubberObject obj = new RubberObject();
		assertEquals(1,obj.getPointCount());
		obj.addPoint();
		assertEquals(2,obj.getPointCount());
	}

	@Test
	public void testLinkPoints()
	{
		RubberObject obj = new RubberObject();
		obj.addPoint();
		obj.linkPoints(0,1,10);
		assertTrue(obj.areLinked(0,1));
		
		obj.addPoint();
		assertFalse(obj.areLinked(0,2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testLinkUnexistingPoints()
	{
		RubberObject obj = new RubberObject();
		assertTrue(obj.areLinked(0,1));
	}
}
