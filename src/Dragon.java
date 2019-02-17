import java.awt.*;

public class Dragon extends CGCanvas{
    public static void main(String[] args){
	if (args.length!=1)
	    System.err.println("Usage: Dragon #iteration");
	else
	    (new Dragon(Integer.parseInt(args[0]))).showFrame();
    }
    
    private static final long serialVersionUID=0;
    private int times;
    private Vector2D initP0,initP1;
    private Matrix2X2[] m;
    private Vector2D[] v;
    
    protected Dragon(int times){
	super("Dragon");
	this.times = times;
	setOrigin(150,300);
	setRange(2.0);
	initP0 = new Vector2D(0.0,0.0);
	initP1 = new Vector2D(1.0,0.0);
	
	m = new Matrix2X2[2];
	v = new Vector2D[2];
	m[0] = Matrix2X2.rotate(Math.PI/4.0).
	    multiply(Matrix2X2.scale(Math.sqrt(2.0)/2.0));
	m[1] = Matrix2X2.rotate(3.0*Math.PI/4.0).
	    multiply(Matrix2X2.scale(Math.sqrt(2.0)/2.0));
	v[0] = new Vector2D(0.0,0.0);
	v[1] = new Vector2D(1.0,0.0);
    }
    
    public void paint(Graphics g){
	drawSegment(g,initP0,initP1,times);
    }

    private void drawSegment(Graphics g, Vector2D p0, Vector2D p1, int l){
	if (l>0)
	    for (int i=0; i<m.length; i++)
		drawSegment(g, m[i].apply(p0).add(v[i]),
			    m[i].apply(p1).add(v[i]), l-1);
	else
	    drawLine(g,p0,p1);
    }
}