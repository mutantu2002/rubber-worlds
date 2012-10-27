package home.mutantu.rubber.ui;
import home.mutantu.rubber.model.RubberPoint;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Arrays;
import java.util.List;

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
		ColorModel color_model = ColorModel.getRGBdefault();
		image = new BufferedImage (color_model,raster,false,null);

	}

	public void paintComponent (Graphics g) 
	{
	    g.drawImage (image, 0, 0, this);
	}

	public void setPixel(int x, int y)
	{
		
	}

	public void empty()
	{
		Arrays.fill(pixels, 0xFF999999);
	}


	public void set4Pixels(int x, int y)
	{
		int maxIndex = height *width;
		int index= width*y+x % maxIndex;
		pixels[index] = 0xFF000000;
		pixels[(index+1)%maxIndex] = 0xFF000000;
		index= width*(y+1)+x % maxIndex;
		pixels[index] = 0xFF000000;
		pixels[(index+1)%maxIndex] = 0xFF000000;
	}
	
	public void drawObjectContour(List<RubberPoint> points)
	{
		Graphics g = image.getGraphics();
		int size = points.size();
		RubberPoint point = points.get(0);
		RubberPoint nextPoint = points.get(size-1);
		g.drawLine((int)point.t0.x, (int)point.t0.y, (int)nextPoint.t0.x, (int)nextPoint.t0.y);
		for (int index=0;index<size-1;index++)
		{
			point = points.get(index);
			nextPoint = points.get(index+1);
			g.drawLine((int)point.t0.x, (int)point.t0.y, (int)nextPoint.t0.x, (int)nextPoint.t0.y);
		}
		
		g.dispose();
	}

}