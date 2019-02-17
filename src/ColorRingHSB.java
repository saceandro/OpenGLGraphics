import java.awt.*;
import java.awt.event.*;

public class ColorRingHSB extends Canvas{
    public static void main(String[] args){
	if (args.length==0)
	    System.err.println("Usage: java ColorRingHSB #");
	else
	    new ColorRingHSB("ColorRingHSB",Integer.parseInt(args[0]));
    }

    private static final long serialVersionUID=0;
    protected static final int width=600;
    protected static final int height=600;
    protected static final int centerX=300;
    protected static final int centerY=299;
    protected static final int radius=250;
    protected int[][] points;
    protected float[] h;

    protected ColorRingHSB(String name, int numberOfPoints){
	super();
	setSize(width,height);
	setBackground(Color.black);
	setData(numberOfPoints);
	
	Frame f = new Frame(name);
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
		    System.exit(0);
		}
	    });
	f.setVisible(true);
    }

    protected void setData(int numberOfPoints){
	points = new int[numberOfPoints+1][];
	h = new float[numberOfPoints+1];
	for (int i=0; i<=numberOfPoints; i++){
	    h[i] = (float)(1.0 * i / numberOfPoints);
	    float theta = (float)(2.0 * Math.PI * h[i]);
	    points[i] = new int[2];
	    points[i][0] = (int)(radius * Math.cos(theta)) + centerX;
	    points[i][1] = (int)(radius * Math.sin(theta)) + centerY;
	}
    }
    
    public void paint(Graphics g){
	for (int i=0; i<points.length-1; i++){
	    g.setColor(Color.getHSBColor(h[i], 1.0f, 1.0f));
	    g.drawLine(points[i][0],points[i][1],
		       points[i+1][0],points[i+1][1]);
	}
    }
}
	    