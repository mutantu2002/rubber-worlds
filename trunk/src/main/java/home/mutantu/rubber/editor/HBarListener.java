package home.mutantu.rubber.editor;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class HBarListener implements AdjustmentListener 
{
	private RubberEditor editor;
	public int position = 0;
	public HBarListener(RubberEditor rubberEditor) 
	{
		this.editor = rubberEditor;
	}
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		position = e.getValue();
		editor.paintWorld(-e.getValue());
	}

}
