import java.awt.*;

public class Sierpinski extends CGCanvas{
    public static void main(String[] args){
	if (args.length!=1)
	    System.err.println("Usage: Sierpinski #iteraton");
	else
	    (new Sierpinski(Integer.parseInt(args[0]))).showFrame();
    }

    private static final long serialVersionUID=0;
    private int times;
    private Vector2D[] initPoints;
    private Matrix2X2[] m;
    private Vector2D[] v;

    protected Sierpinski(int times){
	super("Sierpinski");
	this.times = times;
	setOrigin(300,84);
	setRange(2.4);
	initPoints = new Vector2D[3];
	initPoints[0] = new Vector2D( 0.0, 0.0);
	initPoints[1] = new Vector2D(-1.0,-Math.sqrt(3.0));
	initPoints[2] = new Vector2D( 1.0,-Math.sqrt(3.0));
	
	m = new Matrix2X2[3];
	v = new Vector2D[3];
	m[0] = Matrix2X2.scale(1.0/2.0);
	m[1] = Matrix2X2.scale(1.0/2.0);
	m[2] = Matrix2X2.scale(1.0/2.0);
	v[0] = new Vector2D( 0.0, 0.0);
	v[1] = new Vector2D(-0.5,-Math.sqrt(3.0)/2.0);
	v[2] = new Vector2D( 0.5,-Math.sqrt(3.0)/2.0);
    }
    
    public void paint(Graphics g){
	drawTriangle(g, initPoints, times);
    }
    
    private void drawTriangle(Graphics g, Vector2D[] points, int l){
	if (l>0){
	    for (int i=0; i<m.length; i++){
		Vector2D[] newPoints = new Vector2D[3];
		for (int j=0; j<3; j++)
		    newPoints[j] = m[i].apply(points[j]).add(v[i]);
		drawTriangle(g, newPoints, l-1);
	    }
	}
	else{
	    g.setColor(Color.lightGray);
	    fillPolygon(g,points);
	    g.setColor(Color.black);
	    drawPolygon(g,points);
	}
    }
}