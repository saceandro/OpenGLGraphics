import java.awt.*;
import java.awt.event.*;

public class DrawColorful extends Canvas{
    public static void main(String[] args){
	new DrawColorful();
    }
    
    private static final long serialVersionUID=0;
    private static final int width=400;
    private static final int height=400;
    private int prevX;
    private int prevY;
    private float h=0.0f;
    private double period = 1000.0;

    private DrawColorful(){
	super();
	setSize(height,width);
	setBackground(Color.white);
	
	addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent me){
		    prevX = me.getX();
		    prevY = me.getY();
		}
	    });
	
	addMouseMotionListener(new MouseMotionAdapter(){
		public void mouseDragged(MouseEvent me){
		    int x = me.getX();
		    int y = me.getY();
		    int dx = x - prevX;
		    int dy = y - prevY;
		    double dr = Math.sqrt(dx*dx + dy*dy)/period;
		    h += (float) dr;
		    if (h>1)
			h -= 1.0f;
		    Graphics g = getGraphics();
		    g.setColor(Color.getHSBColor(h, 1.0f, 1.0f));
		    g.drawLine(prevX,prevY,x,y);
		    prevX = x;
		    prevY = y;
		}
	    });
	
	Frame f = new Frame("DrawColorful");
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
		    System.exit(0);
		}
	    });
	f.setVisible(true);
    }
}