package home.mutantu.rubber.ui;

import home.mutantu.rubber.model.RubberWorld;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class WorldFrame extends JFrame
{
	private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    
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
		
	}

}
