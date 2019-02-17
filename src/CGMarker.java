import java.awt.*;
import java.awt.event.*;

public class CGMarker extends CGCanvas{
    public static void main(String[] args){
	new CGMarker().showFrame();
    }
    private static final long serialVersionUID=0;
    protected CGMarker(){
	super("CGMarker");
	markerSize = 10;
	addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent me){
		    int modifiers = me.getModifiers();
		    Graphics g = getGraphics();
		    if ((modifiers & MouseEvent.BUTTON3_MASK) != 0)
			g.setColor(Color.blue);
		    else
			g.setColor(Color.red);
		    int x = me.getX();
		    int y = me.getY();
		    fillMarker(g, point(x,y));
		}
	    });
    }
}
	    