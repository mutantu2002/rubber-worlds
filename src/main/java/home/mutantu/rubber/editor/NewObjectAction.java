package home.mutantu.rubber.editor;

import home.mutantu.rubber.model.StillRubberObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class NewObjectAction implements ActionListener {

	private RubberEditor editor;

	public NewObjectAction(RubberEditor rubberEditor) 
	{
		this.editor = rubberEditor;
	}

	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		editor.world.addObject(new StillRubberObject());
	}
}
