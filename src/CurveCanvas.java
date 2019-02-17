import java.awt.*;

public abstract class CurveCanvas extends CGCanvas{
    final static int NOP = 128;
    protected ParametricCurve[] curves;
    
    protected CurveCanvas(String name){
	super(name);
    }

    protected void drawCurve(Graphics g, ParametricCurve curve, double ts,
			     double te, int nOfPoints){
	double delta = (te-ts)/nOfPoints;
	Vector2D prev = null;
	boolean prevIn = false;
	for (int i=0; i<=nOfPoints; i++){
	    Vector2D point = curve.evaluate(ts + delta*i);
	    boolean in = inside(point);
	    if (prev!=null && (prevIn || in))
		drawLine(g, prev, point);
	    prev = point;
	    prevIn = in;
	}
    }
    
    protected void drawCurve(Graphics g, ParametricCurve curve){
	drawCurve(g, curve, curve.begin(), curve.end(), NOP);
    }

    public void paint(Graphics g){
	if (curves!=null){
	    for (int i=0; i<curves.length; i++){
		if (curves[i]!=null){
		    drawCurve(g, curves[i]);
		}
	    }
	}
    }
}