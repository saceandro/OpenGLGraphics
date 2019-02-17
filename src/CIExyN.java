     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class CIExy extends Canvas {
     5	    public static void main(String[] args) {
     6		new CIExy("CIExy");
     7	    }
     8	
     9	    private static final long serialVersionUID=0;
    10	    private static final int width=600, height=600;
    11	    private static int sample=1000;
    12	    
    13	    private CIExy(String name) {
    14		super();
    15		setSize(width, height);
    16		
    17		Frame f = new Frame(name);
    18		f.add(this);
    19		f.pack();
    20		f.addWindowListener(new WindowAdapter() {
    21			public void windowClosing(WindowEvent e) {
    22			    System.exit(0);
    23			}
    24		    });
    25		f.setVisible(true);
    26	    }
    27	
    28	    public void paint(Graphics g) {
    29		for (int rh=0; rh&lt;=sample; rh++) {
    30		    for (int gh=0; gh&lt;=sample-rh; gh++) {
    31			int bh = sample - rh - gh;
    32			float red = (float)(rh)/sample;
    33			float green = (float)(gh)/sample;
    34			float blue = (float)(bh)/sample;
    35			double largeX = 0.4124*red + 0.3576*green + 0.1805*blue;
    36			double largeY = 0.2126*red + 0.7152*green + 0.0722*blue;
    37			double largeZ = 0.0193*red + 0.1192*green + 0.9505*blue;
    38			double sam = largeX + largeY + largeZ;
    39			double x = largeX / sam;
    40			double y = largeY / sam;
    41			g.setColor(new Color(red, green, blue));
    42			g.fillRect((int)(600*x),(int)(600*y), 1, 1);
    43		    }
    44		}
    45	    }
    46	}