package home.mutantu.rubber.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteObjectAction implements ActionListener 
{

	private RubberEditor editor;
	public DeleteObjectAction(RubberEditor rubberEditor) 
	{
		this.editor = rubberEditor;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (editor.world.getObjects().size()>1)
		{
			editor.world.removeObject(editor.world.getObjects().size()-1);
			
		}
		else
		{
			editor.world.getObjects().get(0).clear();
		}
		editor.paintWorld();
	}

}
