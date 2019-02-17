import java.awt.*;

public class DiagonalCanvas extends CurveCanvas{
    public static void main(String[] args){
	new DiagonalCanvas().showFrame();
    }

    private static final long serialVersionUID=0;
    
    public DiagonalCanvas(){
	super("Diagonal Canvas");
	setRange(8.0);
	curves = new ParametricCurve[3];
	curves[0] = new Sine(-Math.PI, 0.0);
	curves[1] = new Cosine(-Math.PI,0.0);
	curves[2] = new Tangent(-Math.PI,0.0);
    }

    public void paint(Graphics g){
	g.setColor(Color.blue);
	super.paint(g);
	g.setColor(Color.black);
	drawLine(g, new Vector2D(-Math.PI, 4.0), new Vector2D(-Math.PI, -4.0));
	drawLine(g, new Vector2D(-4.0,0.0), new Vector2D(4.0,0.0));
    }
}

abstract class Diagonal implements ParametricCurve{
    protected Vector2D translation;
    
    Diagonal(double x, double y){
	translation = new Vector2D(x,y);
    }

    public double begin(){
	return -0.5*Math.PI;
    }

    public double end(){
	return 2.5*Math.PI;
    }
}

class Sine extends Diagonal{
    Sine(double x, double y){
	super(x, y);
    }
    public Vector2D evaluate(double t){
	return (new Vector2D(t, Math.sin(t))).add(translation);
    }
}

class Cosine extends Diagonal{
    Cosine(double x, double y){
	super(x, y);
    }
    public Vector2D evaluate(double t){
	return (new Vector2D(t, Math.cos(t))).add(translation);
    }
}

class Tangent extends Diagonal{
    Tangent(double x, double y){
	super(x, y);
    }
    public Vector2D evaluate(double t){
	return (new Vector2D(t, Math.tan(t))).add(translation);
    }
}
