package home.mutantu.rubber.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import home.mutantu.rubber.model.KeyboardState;
import home.mutantu.rubber.model.RubberObject;
import home.mutantu.rubber.model.RubberPoint;
import home.mutantu.rubber.model.RubberWorld;
import home.mutantu.rubber.model.StillRubberObject;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class WorldFrame extends JFrame implements KeyListener
{
	private int width = 800;
    private int height = 600;
    
    RasterPanel drawingPanel;
    public KeyboardState keyboardState = new KeyboardState();

    
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
        addKeyListener(this);
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
			if (obj instanceof StillRubberObject)
			{
				drawingPanel.drawObjectContour(obj.getContour());
			}
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode()==40)
		{
			keyboardState.isDownPressed = true;
		}
		else if (e.getKeyCode()==37)
		{
			keyboardState.isLeftPressed  = true;
		}
		else if (e.getKeyCode()==39)
		{
			keyboardState.isRightPressed  = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode()==40)
		{
			keyboardState.isDownPressed = false;
		}
		else if (e.getKeyCode()==37)
		{
			keyboardState.isLeftPressed  = false;
		}
		else if (e.getKeyCode()==39)
		{
			keyboardState.isRightPressed  = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}
}
