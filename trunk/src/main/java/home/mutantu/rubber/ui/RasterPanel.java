package home.mutantu.rubber.ui;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class RasterPanel extends JPanel
{
	BufferedImage image;
	int width, height;
	public RasterPanel(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	int [] pixels;
	
	public void init ()  
	{
		int numPixels = width * height;
		
		pixels = new int [numPixels];
		DataBuffer buffer = new DataBufferInt(pixels, numPixels);
		int [] masks = {0xFF0000, 0xFF00, 0xff, 0xff000000};
		
		WritableRaster raster = Raster.createPackedRaster (buffer, width, height, width, masks, null);
		ColorModel color_model = ColorModel.getRGBdefault ();
		image = new BufferedImage (color_model,raster,false,null);

	}


	public void newFrame () 
	{
		int frame=0;
	    int index = 0;
	    byte mask =  (byte)(frame & 0xff);

	    for (int y = 0; y < height; y++)
	    {
	        for (int x = 0; x < width; x++) 
	        {
	          int alpha = 255;
	          int red =  (y * 255) / (width - 1);
	          red = red & mask;

	          int green =  (x * 255) / (height - 1);
	          green = green & mask;

	          int blue = 255 -  (255 * (x - width))/width;
	          blue = blue & mask;

	          pixels[index++] =  (255 << alpha) | (red << 16) | (green << 8) | blue;
	       }
	    }
	    frame++;
	}

	public void paintComponent (Graphics g) 
	{
	    g.drawImage (image, 0, 0, this);
	}
}