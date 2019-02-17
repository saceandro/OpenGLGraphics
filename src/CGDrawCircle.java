import java.awt.*;
import java.awt.event.*;

public class CGDrawCircle extends CGCanvas{
    public static void main(String[] args){
	new CGDrawCircle().showFrame();
    }

    private static final long serialVersionUID=0;
    private Vector2D center;

    protected CGDrawCircle(){
	super("CGDrawCircle");
	addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent me){
		    center = point(me.getX(),me.getY());
		}
	    });
	addMouseMotionListener(new MouseMotionAdapter(){
		public void mouseDragged(MouseEvent me){
		    Graphics g = getGraphics();
		    update(g);
		    Vector2D p = point(me.getX(),me.getY());
		    double radius = p.subtract(center).norm();
		    drawCircle(g, center, radius);
		}
	    });
    }
}    