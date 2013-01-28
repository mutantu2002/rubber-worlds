package home.mutantu.rubber.editor;

import java.awt.event.ActionEvent;

public class NewAction extends MenuAction 
{
	public NewAction(RubberEditor rubberEditor) 
	{
		super(rubberEditor);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		editor.initWorld();
	}
}
