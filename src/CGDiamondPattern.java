import java.awt.*;

public class CGDiamondPattern extends CGCanvas{
    public static void main(String[] args){
	if(args.length==0)
	    System.err.println("Usage: java CGDiamondPattern #");
	else
	    (new CGDiamondPattern(Integer.parseInt(args[0]))).showFrame();
    }

    private static final long serialVersionUID=0;
    private final static double radius=0.8;
    private Vector2D[] points;

    protected CGDiamondPattern(int numberOfPoints){
	super("CGDiamondPattern");
	points = new Vector2D[numberOfPoints];
	for (int i=0; i<numberOfPoints; i++){
	    double theta = 2.0*Math.PI*i/numberOfPoints;
	    points[i] = new Vector2D(radius*Math.cos(theta),radius*Math.sin(theta));
	}
    }
    
    public void paint(Graphics g){
	for (int i=0; i<points.length-1; i++)
	    for (int j=i+1; j<points.length; j++)
		drawLine(g, points[i], points[j]);
    }
}
