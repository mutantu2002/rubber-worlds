package home.mutantu.rubber.editor;

import java.awt.event.ActionListener;

public abstract class MenuAction implements ActionListener 
{
	protected RubberEditor editor;
	
	public MenuAction(RubberEditor rubberEditor) 
	{
		this.editor = rubberEditor;
	}
}
