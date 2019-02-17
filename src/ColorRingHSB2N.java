     1	import java.awt.*;
     2	
     3	public class ColorRingHSB2 extends ColorRingRGB {
     4	    public static void main(String[] args) {
     5		if (args.length==0)
     6		    System.err.println("Usage: java ColorRingHSB2 #");
     7		else
     8		    new ColorRingHSB2("ColorRingHSB", Integer.parseInt(args[0]));
     9	    }
    10	
    11	    private static final long serialVersionUID = 0;
    12	    private float[] h;
    13	
    14	    protected ColorRingHSB2(String name, int numberOfPoints) {
    15		super(name, numberOfPoints);
    16	    }
    17	    
    18	    protected void setData(int numberOfPoints){
    19		points = new int[numberOfPoints+1][];
    20		h = new float[numberOfPoints+1];
    21		for (int i=0; i&lt;=numberOfPoints; i++){
    22		    h[i] = (float)(1.0 * i / numberOfPoints);
    23		    float theta = (float)(2.0 * Math.PI * h[i]);
    24		    points[i] = new int[2];
    25		    points[i][0] = (int)(radius * Math.cos(theta)) + centerX;
    26		    points[i][1] = (int)(radius * Math.sin(theta)) + centerY;
    27		}
    28	    }
    29	    
    30	    public void paint(Graphics g){
    31		for (int i=0; i&lt;points.length-1; i++){
    32		    g.setColor(Color.getHSBColor(h[i], 1.0f, 1.0f));
    33		    g.drawLine(points[i][0],points[i][1],
    34			       points[i+1][0],points[i+1][1]);
    35		}
    36	    }
    37	}