import home.mutantu.rubber.ui.RasterPanel;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class PixelCanvas  extends JFrame  implements Runnable {
    /**
	 * 
	 */
	private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    
    Thread fThread;
    //msecs of sleep time between frames
    int fDeltaT = 100;
    // Need a reference to the panel for the thread loop.
    RasterPanel fDrawingPanel;

    /**
      * Add an instance of the DrawingPanelWR on which
      * the animation will take place.
     **/
    public void init () {
      // Create an instance of DrawingPanel
      fDrawingPanel = new RasterPanel (WIDTH, HEIGHT);

      // Add the DrawingPanel to the JApplet pane.
      add (fDrawingPanel);

    } // init

    /** Called by browser when web page and applet loaded. **/
    public void start () {
      // Tell the panel to create the image source.
      fDrawingPanel.init ();

      // If the thread reference not null then a
      // thread is already running. Otherwise, create
      // a thread and start it.
      if (fThread == null) {
          fThread = new Thread (this);
          fThread.start ();
      }
    }

    /**  Called by browser when web page and applet unloaded. **/
    public void stop () {
       // Setting thread to null will cause loop in
       // run () to finish and the process return from
       // run () thus killing the thread.
       fThread = null;
    } // stop
    
    public static void main(String[] args) {
        PixelCanvas frame = new PixelCanvas();

        frame.setSize(WIDTH, HEIGHT);
        frame.fDrawingPanel = new RasterPanel(WIDTH, HEIGHT);
        frame.add(frame.fDrawingPanel);

        frame.setVisible(true);
        
        frame.fDrawingPanel.init ();

        // If the thread reference not null then a
        // thread is already running. Otherwise, create
        // a thread and start it.
        if (frame.fThread == null) {
        	frame.fThread = new Thread (frame);
        	frame. fThread.start ();
        }
        
    }

	@Override
	public void run()
	{
	    while (fThread != null) {
	        try {
	          Thread.sleep (fDeltaT);
	        }
	        catch (InterruptedException e)
	        { }

	        // Send request for create new image
	        fDrawingPanel.newFrame ();

	        // Repaint now that the pixel data has changed.
	        repaint ();
	      }
		
	}
}

