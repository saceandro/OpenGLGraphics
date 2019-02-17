import java.awt.*;
import java.awt.event.*;

public class ColorRingRGB extends Canvas{
    public static void main(String[] args){
	if (args.length==0)
	    System.err.println("Usage: java ColorRingRGB #");
	else
	    new ColorRingRGB("ColorRingRGB",Integer.parseInt(args[0]));
    }

    private static final long serialVersionUID=0;
    protected static final int width=600;
    protected static final int height=600;
    protected static final int centerX=300;
    protected static final int centerY=299;
    protected static final int radius=250;
    protected Color[] colors;
    protected int[][] points;

    protected ColorRingRGB(String name, int numberOfPoints){
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
	final float oneSixth = 1.0f/6.0f;
	final float twoSixth = 2.0f/6.0f;
	final float threeSixth = 3.0f/6.0f;
	final float fourSixth = 4.0f/6.0f;
	final float fiveSixth = 5.0f/6.0f;
	final float six = 6.0f;

	colors = new Color[numberOfPoints+1];
	points = new int[numberOfPoints+1][];
	for (int i=0; i<=numberOfPoints; i++){
	    float ratio = (float)i / numberOfPoints;
	
	    if (ratio<=oneSixth){
		colors[i] = new Color(1.0f, ratio*six, 0.0f);}
	    else if (ratio<=twoSixth){
		colors[i] = new Color((twoSixth-ratio)*six, 1.0f, 0.0f);}
	    else if (ratio<=threeSixth){
		colors[i] = new Color(0.0f, 1.0f, (ratio-twoSixth)*six);}
	    else if (ratio<=fourSixth){
		colors[i] = new Color(0.0f, (fourSixth-ratio)*six, 1.0f);}
	    else if (ratio<=fiveSixth){
		colors[i] = new Color((ratio-fourSixth)*six, 0.0f, 1.0f);}
	    else{
		colors[i] = new Color(1.0f, 0.0f, (1.0f-ratio)*six);}

	    double theta = 2.0*Math.PI*ratio;
	    points[i] = new int[2];
	    points[i][0] = (int)(radius * Math.cos(theta)) + centerX;
	    points[i][1] = (int)(radius * Math.sin(theta)) + centerY;
	}
    }

    public void paint(Graphics g){
	for (int i=0; i<points.length-1; i++){
	    g.setColor(colors[i]);
	    g.drawLine(points[i][0], points[i][1],
		       points[i+1][0],points[i+1][1]);
	}
    }
}
	    




