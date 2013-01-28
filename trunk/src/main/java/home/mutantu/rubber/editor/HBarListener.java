package home.mutantu.rubber.editor;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class HBarListener implements AdjustmentListener 
{

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		System.out.println(e.getValue());
	}

}
