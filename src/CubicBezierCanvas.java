import java.awt.*;

public class CubicBezierCanvas extends CurveCanvas{
    public static void main(String[] args){
	if (args.length!=8)
	    System.err.println("Usage: java CubicBezierCanvas x0 y0 x1 y1 x2 y2 x3 y3");
	else{
	    Vector2D[] points = new Vector2D[4];
	    for (int i=0; i<4; i++)
		points[i] = new Vector2D(Double.parseDouble(args[2*i]),
					 Double.parseDouble(args[2*i+1]));
	    (new CubicBezierCanvas(points)).showFrame();
	}
    }

    private static final long serialVersionUID=0;
    
    public CubicBezierCanvas(){
	super("Cubic Bezier Canvas");
    }

    public CubicBezierCanvas(Vector2D[] points){
	this();
	curves = new ParametricCurve[1];
	curves[0] = new CubicBezier(points);
    }

    public void paint(Graphics g){
	super.paint(g);
	if (curves!=null)
	    for (int i=0; i<curves.length; i++)
		drawPoints(g, ((CubicBezier)curves[i]).points());
    }

    private void drawPoints(Graphics g, Vector2D[] points){
	if (points!=null){
	    Color tmpColor = g.getColor();
	    g.setColor(Color.BLUE);
	    fillMarker(g, points[0]);
	    for (int i=1; i<points.length; i++){
		fillMarker(g,points[i]);
		drawLine(g, points[i-1], points[i]);
	    }
	    g.setColor(tmpColor);
	}
    }
}