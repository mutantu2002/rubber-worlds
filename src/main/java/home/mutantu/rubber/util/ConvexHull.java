package home.mutantu.rubber.util;

import home.mutantu.rubber.model.RubberPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ConvexHull
{
	public static ArrayList<RubberPoint> compute(List<RubberPoint> points) 
	{
		ArrayList<RubberPoint> xSorted = new ArrayList<RubberPoint>(points);
		Collections.sort(xSorted, new XCompare());
		
		int n = xSorted.size();
		RubberPoint[] lUpper = new RubberPoint[n];
		
		lUpper[0] = xSorted.get(0);
		lUpper[1] = xSorted.get(1);
		
		int lUpperSize = 2;
		for (int i = 2; i < n; i++)
		{
			lUpper[lUpperSize] = xSorted.get(i);
			lUpperSize++;
			while (lUpperSize > 2 && !rightTurn(lUpper[lUpperSize - 3], lUpper[lUpperSize - 2], lUpper[lUpperSize - 1]))
			{
				// Remove the middle point of the three last
				lUpper[lUpperSize - 2] = lUpper[lUpperSize - 1];
				lUpperSize--;
			}
		}
		
		RubberPoint[] lLower = new RubberPoint[n];
		
		lLower[0] = xSorted.get(n - 1);
		lLower[1] = xSorted.get(n - 2);
		
		int lLowerSize = 2;
		
		for (int i = n - 3; i >= 0; i--)
		{
			lLower[lLowerSize] = xSorted.get(i);
			lLowerSize++;
			
			while (lLowerSize > 2 && !rightTurn(lLower[lLowerSize - 3], lLower[lLowerSize - 2], lLower[lLowerSize - 1]))
			{
				// Remove the middle point of the three last
				lLower[lLowerSize - 2] = lLower[lLowerSize - 1];
				lLowerSize--;
			}
		}
		
		ArrayList<RubberPoint> result = new ArrayList<RubberPoint>();
		
		for (int i = 0; i < lUpperSize; i++)
		{
			result.add(lUpper[i]);
		}
		
		for (int i = 1; i < lLowerSize - 1; i++)
		{
			result.add(lLower[i]);
		}
		
		return result;
	}
	
	private static boolean rightTurn(RubberPoint a, RubberPoint b, RubberPoint c)
	{
		return (b.t0.x - a.t0.x)*(c.t0.y - a.t0.y) - (b.t0.y - a.t0.y)*(c.t0.x - a.t0.x) > 0;
	}
	
	public static class XCompare implements Comparator<RubberPoint>
	{
		@Override
		public int compare(RubberPoint o1, RubberPoint o2) 
		{
			return (new Double(o1.t0.x)).compareTo(new Double(o2.t0.x));
		}
	}
}


