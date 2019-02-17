import java.awt.*;
import java.awt.event.*;

public class Circles extends Canvas {
    public static void main(String[] args) {
	if (args.length==0)
	    System.err.println("Usage: java Circles #");
	else
	    new Circles("Circles", Integer.parseInt(args[0]));
    }

    private static final long serialVersionUID=0;
    private static final int width=600;
    private static final int height=600;
    private static int vertices;
    private static int[][] cen;
    

    protected Circles(String name, int n) {
	super();
	setSize(width, height);
	setBackground(Color.white);
	setForeground(Color.black);
	vertices = n;
	
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

    public  void anyCircle(Graphics g, int numberOfPoints, int centerX, int centerY, int radius) {
	int[][] points = new int[numberOfPoints][];
	for (int i=0; i<=numberOfPoints; i++) {
	    double theta = 2.0 * Math.PI * i / numberOfPoints;
	    points[i] = new int[2];
	    points[i][0] = (int)(radius * Math.cos(theta)) + centerX;
	    points[i][1] = (int)(radius * Math.sin(theta)) + centerY;
	}
	for (int i=0; i<points.length-1; i++) {
	    g.drawLine(points[i][0], points[i][1], points[i+1][0], points[i+1][1]);
	}
    }

    public void paint(Graphics g) {
	anyCircle(g, vertices, 300, 299, 250);
	/*
	cen = new int[vertices][];
	float r3 = 250.0f/3.0f;
	for (int i=0; i<vertices; i++) {
	    double theta = 2.0 * Math.PI * i / vertices;
	    
	    cen[i] = new int[2];
	    cen[i][0] = (int)(r3 * Math.cos(theta)) + 300;
	    cen[i][1] = (int)(r3 * Math.sin(theta)) + 299;
	    
	}
	for (int i=0; i<vertices; i++)
	    anyCircle(g, 64, cen[i][0], cen[i][1], (int) r3);
	*/
    }
}