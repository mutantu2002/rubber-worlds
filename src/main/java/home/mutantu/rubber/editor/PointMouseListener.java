package home.mutantu.rubber.editor;

import java.awt.event.MouseAdapter;

public class PointMouseListener extends MouseAdapter
{
    private RubberEditor editor;

	public PointMouseListener(RubberEditor rubberEditor) 
	{
		this.editor = rubberEditor;
	}

	public void mouseClicked(java.awt.event.MouseEvent evt) 
    {
		int index = editor.world.getObjects().size()-1;
        editor.world.getObjects().get(index).addPoint(evt.getX(), evt.getY());
        editor.world.getObjects().get(index).computeContour();
        editor.paintWorld();
    }
}
