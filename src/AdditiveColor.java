import java.awt.*;
import java.awt.event.*;

public class AdditiveColor extends Canvas {
    public static void main(String[] args) {
	new AdditiveColor("AdditiveColor");
    }
    private static final long serialVersionUID = 0;
    protected static final int width = 600;
    protected static final int height = 600;
    protected static final double radius2 = 180.0 * 180.0;
    protected double[][] centers = 
    {{300.0,225.0},{213.4,375.0},{386.6,375.0}};
    
    protected AdditiveColor(String name) {
	super();
	setSize(width,height);	
	Frame f = new Frame(name);
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
	f.setVisible(true);
    }

    public void paint(Graphics g) {
	for (int y = 0; y < height; y++) {
	    for (int x = 0; x < width; x++) {
		float[] color = new float[3];
		for (int i = 0; i < 3; i++) {
		    double dist2 =
			(x - centers[i][0]) * (x - centers[i][0]) + 
			(y - centers[i][1]) * (y - centers[i][1]);
		    color[i] = (dist2 > radius2) ? 0.0f : 1.0f;
		}
		g.setColor(new Color(color[0],color[1],color[2]));
		g.fillRect(x,y,1,1);
	    }
	}
    }
}