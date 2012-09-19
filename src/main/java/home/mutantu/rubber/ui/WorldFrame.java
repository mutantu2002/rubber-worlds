package home.mutantu.rubber.ui;

import home.mutantu.rubber.model.RubberObject;
import home.mutantu.rubber.model.RubberPoint;
import home.mutantu.rubber.model.RubberWorld;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class WorldFrame extends JFrame
{
	private int width = 800;
    private int height = 600;
    
    RasterPanel drawingPanel;
    
    public WorldFrame(int width, int height)
    {
    	this.width = width;
    	this.height = height;
    	setSize(this.width+20, this.height+50);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	drawingPanel = new RasterPanel (this.width,this.height);
        add (drawingPanel);
        drawingPanel.init();
        setVisible(true);
    }

	public void buildFrame(RubberWorld world)
	{
		drawingPanel.empty();
		for (RubberObject obj : world.getObjects())
		{
			for (RubberPoint point : obj.getPoints())
			{
				drawingPanel.set4Pixels((int)point.t0.x, (int)point.t0.y);
			}
		}
		repaint();
	}
}
