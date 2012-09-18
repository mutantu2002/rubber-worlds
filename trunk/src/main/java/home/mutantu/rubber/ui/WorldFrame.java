package home.mutantu.rubber.ui;

import home.mutantu.rubber.model.RubberObject;
import home.mutantu.rubber.model.RubberPoint;
import home.mutantu.rubber.model.RubberWorld;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class WorldFrame extends JFrame
{
	private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    
    RasterPanel drawingPanel;
    
    public WorldFrame()
    {
    	setSize(WIDTH, HEIGHT);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	drawingPanel = new RasterPanel (WIDTH,HEIGHT);
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
