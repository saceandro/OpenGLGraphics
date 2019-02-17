import java.awt.*;

public class Circles2 extends Circle {
    public static void main(String[] args) {
	if (args.length == 0)
	    System.err.println("Usage: java Circles2 #");
	else
	    new Circles2("Circles2",Integer.parseInt(args[0]));
    }

    private static final long serialVersionUID=0;
    private static int r=50;
    
    protected Circles2(String name, int numberOfPoints) {
	super(name, numberOfPoints);
    }

    public void paint(Graphics g) {
	super.paint(g);
	for (int i=0; i<points.length-1; i++)
	    g.drawOval(points[i][0]-r, points[i][1]-r, 2*r, 2*r);
    }
}