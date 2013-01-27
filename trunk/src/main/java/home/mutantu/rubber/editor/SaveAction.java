package home.mutantu.rubber.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveAction implements ActionListener {

	private RubberEditor editor;

	public SaveAction(RubberEditor rubberEditor) 
	{
		this.editor = rubberEditor;
	}

	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		try
		{
	        FileOutputStream fos = new FileOutputStream("tempdata.ser");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(editor.world);
	        oos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
