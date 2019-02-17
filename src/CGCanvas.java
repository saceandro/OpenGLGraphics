import java.awt.*;
import java.awt.event.*;

public abstract class CGCanvas extends Canvas{
    private Frame f;
    private int width=600, height=600, originX=300, originY=299;
    protected double range=2.0;
    protected double scale = width/range;
    protected int markerSize=5;
    
    protected CGCanvas(String name){
	super();
	setSize(width,height);
	setBackground(Color.white);
	setForeground(Color.black);
	
	f = new Frame(name);
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
		    System.exit(0);
		}
	    });
    }
    protected void showFrame(){
	f.setVisible(true);
    }
    public void setOrigin(int x, int y){
	originX = x;
	originY = y;
    }
    public void setRange(double r){
	Dimension d = getSize();
	width = d.width;
	height = d.height;
	range = r;
	scale = Math.min(width,height)/range;
    }
    public void drawLine(Graphics g, Vector2D from, Vector2D to){
	g.drawLine(x(from), y(from), x(to), y(to));
    }
    public void drawCircle(Graphics g, Vector2D point, double radius){
	int dr = (int)(radius*scale);
	g.drawOval(x(point)-dr, y(point)-dr, dr*2, dr*2);
    }
    public void drawPolygon(Graphics g, Vector2D[] points){
	int[] x = new int[points.length];
	int[] y = new int[points.length];
	for (int i=0; i<points.length; i++){
	    x[i] = x(points[i]);
	    y[i] = y(points[i]);
	}
	g.drawPolygon(x,y,points.length);
    }
    public void fillPolygon(Graphics g, Vector2D[] points){
	int[] x = new int[points.length];
	int[] y = new int[points.length];
	for (int i=0; i<points.length; i++){
	    x[i] = x(points[i]);
	    y[i] = y(points[i]);
	}
	g.fillPolygon(x,y,points.length);
    }
    public void fillMarker(Graphics g, Vector2D point){
	g.fillRect(x(point)-markerSize/2, y(point)-markerSize/2,
		   markerSize, markerSize);
    }
    public Vector2D point(int x, int y){
	return new Vector2D((x-originX)/scale, (originY-y)/scale);
    }
    protected final int x(Vector2D point){
	return (int) (scale*point.x()) + originX;
    }
    protected final int y(Vector2D point){
	return -(int) (scale*point.y()) + originY;
    }
    protected final boolean inside(Vector2D point){
	int x = (int) (scale*point.x()) + originX;
	if (x<0 || x>=width)
	    return false;
	int y = -(int) (scale*point.y()) + originY;
	if (y<0 || y>=height)
	    return false;
	return true;
    }
}