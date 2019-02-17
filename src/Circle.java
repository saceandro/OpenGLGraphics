import java.awt.*;
import java.awt.event.*;

public class Circle extends Canvas {
    public static void main(String[] args) {
	if (args.length == 0)
	    System.err.println("Usage: java Circle #");
	else
	    new Circle("Circle",Integer.parseInt(args[0]));
    }

    private static final long serialVersionUID = 0; //static: class variable
    private static final int width = 600; //final : cannot be inherited by subclass 
    private static final int height = 600;
    private static final int centerX = 300;
    private static final int centerY = 299;
    private static final int radius = 250;
    protected int[][] points;

    protected Circle(String name, int numberOfPoints) {
	super();
	setSize(width,height);
	setBackground(Color.white);
	setForeground(Color.black);

	points = new int[numberOfPoints+1][];
	for (int i = 0; i <= numberOfPoints; i++) {
	    double theta = 2.0 * Math.PI * i / numberOfPoints;
	    points[i] = new int[2];
	    points[i][0] = (int)(radius * Math.cos(theta)) + centerX;
	    points[i][1] = (int)(radius * Math.sin(theta)) + centerY;
	}

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
	for (int i = 0; i < points.length -1; i++) {
	    g.drawLine(points[i][0],points[i][1],
		       points[i+1][0],points[i+1][1]);
	}
    }
}
	     