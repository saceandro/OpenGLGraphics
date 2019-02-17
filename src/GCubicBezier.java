import java.awt.*;
import java.awt.event.*;

public class GCubicBezier extends CubicBezierCanvas{
    public static void main(String[] args){
	new GCubicBezier().showFrame();
    }
    
    private static final long serialVersionUID=0;
    private Vector2D[] points = new Vector2D[4];
    private Vector2D[] delta = new Vector2D[4];
    int count=0;
    int regulator=-1;
        
    protected GCubicBezier(){
	addMouseListener(new MouseAdapter (){
		public void mousePressed(MouseEvent me){
		    Graphics g = getGraphics();
		    if ((me.getModifiers() & MouseEvent.BUTTON1_MASK)!=0){
			if (count<4){
			    points[count] = point(me.getX(),me.getY());
			    Color tmpColor = g.getColor();
			    g.setColor(Color.blue);
			    fillMarker(g,points[count]);
			    g.setColor(tmpColor);
			    if (count==3){
				curves = new ParametricCurve[1];
				curves[0] = new CubicBezier(points);
				paint(g);
			    }
			    count ++;
			}
			else{
			    Vector2D p = point(me.getX(), me.getY());
			    for (int i=0; i<points.length; i++){
				delta[i] = p.subtract(points[i]);
				if((Math.abs(x(delta[i])) <= markerSize/2.0) &&
				   (Math.abs(y(delta[i])) <= markerSize/2.0))
				    regulator = i;
			    }
			}
		    }
		    else{
			update(g);
			count = 0;
			regulator = -1;
		    }
		}
	    });
	addMouseMotionListener(new MouseMotionAdapter(){
		public void mouseDragged(MouseEvent me){
		    Graphics g = getGraphics();
		    if (regulator != -1){
			update(g);
			points[regulator] = point(me.getX(),me.getY()).subtract(delta[regulator]);
			Color tmpColor = g.getColor();
			g.setColor(Color.blue);
			for (int i=0; i<points.length; i++){
			    fillMarker(g,points[i]);
			}
			g.setColor(tmpColor);
			curves = new ParametricCurve[1];
			curves[0] = new CubicBezier(points);
			paint(g);
		    }
		}
	    });
    }	

    public void paint(Graphics g){
	super.paint(g);
    }
    
    private void drawPoints(Graphics g, Vector2D[] points){
	Color tmpColor = g.getColor();
	g.setColor(Color.BLUE);
	for (int i=1; i<points.length; i++){
	    drawLine(g, points[i-1], points[i]);
	}
	g.setColor(tmpColor);
    }
}
