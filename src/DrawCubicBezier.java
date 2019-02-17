import java.awt.*;
import java.awt.event.*;

public class DrawCubicBezier extends CubicBezierCanvas{
    public static void main(String[] args){
	new DrawCubicBezier("Draw Cubic Bezier").showFrame();
    }

    private static final long serialVersionUID=0;
    private Graphics g = getGraphics();
    private Vector2D[] points = new Vector2D[4];
    int count=0;
 
    public DrawCubicBezier(String name){
	addMouseListener(new MouseAdapter(){
		public void MousePressed(MouseEvent me){
		    if (count<4){
			if ((me.getModifiers() & MouseEvent.BUTTON1_MASK)!=0){
			    points[count] = point(me.getX(),me.getY());
			    g.setColor(Color.BLUE);
			    fillMarker(g,points[count]);
			    count += 1;
			}
		    }
		}
	    });
	if (count==4){
	    curves = new ParametricCurve[1];
	    curves[0] = new CubicBezier(points);
	    drawPoints();
	    g.setColor(Color.black);
	    paint(g);
	    count += 1;
	}
    }
	    
    private void drawPoints(){
	if (points!=null){
	    Color tmpColor = g.getColor();
	    g.setColor(Color.BLUE);
	    for (int i=1; i<points.length; i++){
		drawLine(g, points[i-1], points[i]);
	    }
	    g.setColor(tmpColor);
	}
    }
  
}