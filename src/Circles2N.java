     1	import java.awt.*;
     2	
     3	public class Circles2 extends Circle {
     4	    public static void main(String[] args) {
     5		if (args.length == 0)
     6		    System.err.println("Usage: java Circles2 #");
     7		else
     8		    new Circles2("Circles2",Integer.parseInt(args[0]));
     9	    }
    10	
    11	    private static final long serialVersionUID=0;
    12	    private static int r=50;
    13	    
    14	    protected Circles2(String name, int numberOfPoints) {
    15		super(name, numberOfPoints);
    16	    }
    17	
    18	    public void paint(Graphics g) {
    19		super.paint(g);
    20		for (int i=0; i&lt;points.length-1; i++)
    21		    g.drawOval(points[i][0]-r, points[i][1]-r, 2*r, 2*r);
    22	    }
    23	}