import java.awt.*;

public class TrochoidCanvas extends CurveCanvas{
    public static void main(String[] args){
	//	if (args.length!=1)
	//	    System.err.println("Usage: java Trochoid #");
	//	else
	new TrochoidCanvas().showFrame();
    }
	 
    private static final long serialVersionUID=0;
    private static final double a = 0.8;
    private double[] ratio;
    private double[] yline;
	
    public TrochoidCanvas(/*double ratio*/){
	super("TrochoidCanvas");
	setRange(8.0);
	ratio = new double[3];
	ratio[0]=0.8; ratio[1]=1.0; ratio[2]=1.2;
	curves = new ParametricCurve[3];
	curves[0] = new Trochoid(ratio[0], -Math.PI, -3.0);
	curves[1] = new Trochoid(ratio[1], -Math.PI, -1.0);
	curves[2] = new Trochoid(ratio[2], -Math.PI, 1.0);
    }
    
    public void paint(Graphics g){
	g.setColor(Color.blue);
	super.paint(g);
	yline = new double[3];
	yline[0]=-3.0; yline[1]=-1.0; yline[2]=1.0;
	for (int j=0; j<=2; j++){
	    for (int i=0; i<=6; i++){
		g.setColor(Color.lightGray);
		double t = i*Math.PI/3.0;
		double x = a*(t-5.0*Math.PI/4.0);
		drawCircle(g, new Vector2D(x,a+yline[j]), a);
		Vector2D p = new Vector2D(a*(t - ratio[j]*Math.sin(t))-Math.PI, a*(1.0 - ratio[j]*Math.cos(t))+yline[j]);
		drawLine(g, new Vector2D(x,a+yline[j]), p);
	    }
	    g.setColor(Color.black);
	    drawLine(g, new Vector2D(-4.0,yline[j]), new Vector2D(4.0,yline[j]));
	}
	g.setColor(Color.black);
	drawLine(g, new Vector2D(-Math.PI,4.0), new Vector2D(-Math.PI,-4.0));
    }
}

abstract class TrochoidCurve implements ParametricCurve{
    protected Vector2D translation;
    protected double r;
    
    TrochoidCurve(double ratio, double x, double y){
	translation = new Vector2D(x,y);
	r = ratio;
    }

    public double begin(){
	return -0.5*Math.PI;
    }
    
    public double end(){
	return 2.5*Math.PI;
    }
}

class Trochoid extends TrochoidCurve{
    
    protected static final double a = 0.8;
    
    Trochoid(double ratio, double x, double y){
	super(ratio,x,y);
    }
    public Vector2D evaluate(double t){
	double x = a*(t - r*Math.sin(t));
	double y = a*(1.0 - r*Math.cos(t));
	return (new Vector2D(x,y)).add(translation);
    }
}