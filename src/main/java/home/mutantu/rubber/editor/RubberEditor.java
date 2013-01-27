package home.mutantu.rubber.editor;

import home.mutantu.rubber.model.Constants;
import home.mutantu.rubber.model.RubberObject;
import home.mutantu.rubber.model.RubberPoint;
import home.mutantu.rubber.model.RubberWorld;
import home.mutantu.rubber.model.StillRubberObject;
import home.mutantu.rubber.ui.RasterPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class RubberEditor 
{
	private static JFrame frame;
	public RubberWorld world;
	private RasterPanel drawingPanel;
	
	public static void main(String[] args) 
	{
		new RubberEditor();
	}

	public RubberEditor()
	{
		frame = new JFrame("Rubber world editor");
		frame.setSize(Constants.WORLD_WIDTH+20, Constants.WORLD_HEIGHT+50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawingPanel = new RasterPanel(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        world = new RubberWorld(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        StillRubberObject obj = new StillRubberObject();
        world.addObject(obj);
		drawingPanel.addMouseListener(new PointMouseListener(this));
		addMenu();
		frame.add(drawingPanel);
		frame.setVisible(true);
		paintWorld();
	}
	
	public void paintWorld()
	{
		drawingPanel.empty();
		for (RubberObject obj : world.getObjects())
		{
			for (RubberPoint point : obj.getPoints())
			{
				drawingPanel.set4Pixels((int)point.t0.x, (int)point.t0.y, 0);
			}
			if (obj instanceof StillRubberObject)
			{
				drawingPanel.drawObjectContour(obj.getContour(), 0);
			}
		}
		frame.repaint();
	}
	
	private void addMenu()
	{
		JMenu file = new JMenu("File");
		file.setMnemonic('F');
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.setMnemonic('S');
		file.add(saveItem);
		saveItem.addActionListener(new SaveAction(this));
		
		JMenuItem newObjectItem = new JMenuItem("New Object");
		newObjectItem.setMnemonic('P');
		file.add(newObjectItem);
		newObjectItem.addActionListener(new NewObjectAction(this));
		
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);
		bar.add(file);
	}
}
