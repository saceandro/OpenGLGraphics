import java.awt.*;

public class ColorRingHSB2 extends ColorRingRGB {
    public static void main(String[] args) {
	if (args.length==0)
	    System.err.println("Usage: java ColorRingHSB2 #");
	else
	    new ColorRingHSB2("ColorRingHSB", Integer.parseInt(args[0]));
    }

    private static final long serialVersionUID = 0;
    private float[] h;

    protected ColorRingHSB2(String name, int numberOfPoints) {
	super(name, numberOfPoints);
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