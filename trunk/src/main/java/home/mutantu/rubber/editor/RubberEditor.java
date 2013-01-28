package home.mutantu.rubber.editor;

import home.mutantu.rubber.model.Constants;
import home.mutantu.rubber.model.RubberObject;
import home.mutantu.rubber.model.RubberPoint;
import home.mutantu.rubber.model.RubberWorld;
import home.mutantu.rubber.model.StillRubberObject;
import home.mutantu.rubber.ui.RasterPanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;

public class RubberEditor 
{
	public static JFrame frame;
	public RubberWorld world;
	public RasterPanel drawingPanel;
	
	public static void main(String[] args) 
	{
		new RubberEditor();
	}

	public RubberEditor()
	{
		frame = new JFrame("Rubber world editor");
		frame.setSize(Constants.WORLD_WIDTH+20, Constants.WORLD_HEIGHT+50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawingPanel = new RasterPanel(Constants.WORLD_WIDTH*2, Constants.WORLD_HEIGHT);
		JScrollBar hbar = new JScrollBar(
                JScrollBar.HORIZONTAL, 30, 20, 0, 300);
		hbar.addAdjustmentListener(new HBarListener());
		frame.add(hbar, BorderLayout.SOUTH);
		drawingPanel.addMouseListener(new PointMouseListener(this));
		addMenu();
		frame.add(drawingPanel);
		frame.setVisible(true);
		initWorld();
	}
	
	public void initWorld()
	{
        world = new RubberWorld(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        StillRubberObject obj = new StillRubberObject();
        world.addObject(obj);
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
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.setMnemonic('S');
		fileMenu.add(saveItem);
		saveItem.addActionListener(new SaveAction(this));

		JMenuItem newItem = new JMenuItem("New");
		newItem.setMnemonic('N');
		fileMenu.add(newItem);
		newItem.addActionListener(new NewAction(this));
		
		JMenu objectsMenu = new JMenu("Objects");
		objectsMenu.setMnemonic('O');
		
		JMenuItem newObjectItem = new JMenuItem("New Object");
		newObjectItem.setMnemonic('J');
		objectsMenu.add(newObjectItem);
		newObjectItem.addActionListener(new NewObjectAction(this));
		
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);
		bar.add(fileMenu);
		bar.add(objectsMenu);
	}
}
