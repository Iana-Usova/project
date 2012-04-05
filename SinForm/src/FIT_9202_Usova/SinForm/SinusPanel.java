package FIT_9202_Usova.SinForm;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

public class SinusPanel extends JPanel {

	public final static int DEFAULT_PHASE = 100;
	public final static int DEFAULT_AMPLITUDE = 20;
	public final static int DEFAULT_GRID_WIDTH = 20;
	public final static int DEFAULT_GRID_HEIGHT = 20;

	int width;
	int height;

	private int GridWidth = 20;
	private int GridHeight = 20;
	private int Amplitude = 20;
	private int Phase = 100;

	public void setGridWidth(int gridWidth) {
		GridWidth = gridWidth;
	}

	public void setGridHeight(int gridHeight) {
		GridHeight = gridHeight;
	}

	public void setAmplitude(int amplitude) {
		Amplitude = amplitude;
	}

	public void setPhase(int phase) {
		Phase = phase;
	}

	@Override
	public void paintComponent(Graphics g) {

		width = getSize().width; // - getInsets().left - getInsets().right;
		height = getSize().height; // - getInsets().top - getInsets().bottom;
		
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int[] plane = img.getRGB(0, 0, width, height, null, 0, width);

		int oX = width / 2;
		int oY = height / 2;

		int y0, y1 = 0;
		int delta;

		for (int i=0;i<height;i++)
			for(int j=0;j<width;j++)
				plane[width*i+j] = Color.BLACK.getRGB();

		// net y
		for (int y = height / 2; y < height; y += GridHeight)
			for (int i = 1; i < width; i++)
				plane[y * width + i] = Color.DARK_GRAY.getRGB();

		for (int y = height / 2; y > 0; y -= GridHeight)
			for (int i = 1; i < width; i++)
				plane[y * width + i] = Color.DARK_GRAY.getRGB();

		// net õ
		for (int x = width / 2; x < width; x += GridWidth)
			for (int i = 1; i < height; i++)
				plane[x + width * i] = Color.DARK_GRAY.getRGB();

		for (int x = width / 2; x > 0; x -= GridWidth)
			for (int i = 1; i < height; i++)
				plane[x + width * i] = Color.DARK_GRAY.getRGB();

		// center lines
		for (int lineY = 0; lineY < height; lineY++) {
			plane[lineY * width + oX] = Color.WHITE.getRGB();
		}
		for (int lineX = 0; lineX < width; lineX++) {
			plane[lineX + ((oY) * (width))] = Color.WHITE.getRGB();
		}
		
				
		// Sinus
       	for(int x = 0; x < width ; x++)
        {
            y0 = y1;
            y1 =(int) ((double)oY + GridHeight * (Math.sin(((((double)oX - x)/GridWidth)/(Phase))) * Amplitude));
            delta = (y1 - y0);
                for(int p = 0; p <= Math.abs(delta); p++)
                {
                    int point;
                    if(delta > 0)
                    {
                        point = x + (y0 + p) * width;
                    }
                    else
                    {
                        point = x + (y0 - p) * width;
                    }

                    if(point< width*height && point > 0)
                    {
                        plane[point] =  Color.GREEN.getRGB();
                    }

                }
        }
		
		super.paintComponent(g);
		img.setRGB(0, 0, width, height, plane, 0, width);
		TexturePaint tex = new TexturePaint(img, new Rectangle(0, 0, width,	height));
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setPaint(tex);
		g2D.fillRect(0, 0, width, height);
	}
}