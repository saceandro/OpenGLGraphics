import java.awt.*;
import java.awt.event.*;

public class CIExy extends Canvas {
    public static void main(String[] args) {
	new CIExy("CIExy");
    }

    private static final long serialVersionUID=0;
    private static final int width=600, height=600;
    private static int sample=1000;
    
    private CIExy(String name) {
	super();
	setSize(width, height);
	
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
	for (int rh=0; rh<=sample; rh++) {
	    for (int gh=0; gh<=sample-rh; gh++) {
		int bh = sample - rh - gh;
		float red = (float)(rh)/sample;
		float green = (float)(gh)/sample;
		float blue = (float)(bh)/sample;
		double largeX = 0.4124*red + 0.3576*green + 0.1805*blue;
		double largeY = 0.2126*red + 0.7152*green + 0.0722*blue;
		double largeZ = 0.0193*red + 0.1192*green + 0.9505*blue;
		double sam = largeX + largeY + largeZ;
		double x = largeX / sam;
		double y = largeY / sam;
		g.setColor(new Color(red, green, blue));
		g.fillRect((int)(600*x),(int)(600*y), 1, 1);
	    }
	}
    }
}