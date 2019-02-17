import java.awt.*;
import java.awt.event.*;

public class DrawCircle extends Canvas{
    public static void main(String[] args){
	new DrawCircle();
    }

    private static final long serialVersionUID=0;
    private static final int width=400;
    private static final int height=400;
    private int centerX,centerY;
    
    private DrawCircle(){
	super();
	setSize(width,height);
	setBackground(Color.white);
	setForeground(Color.black);
	
	addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent me){
		    centerX = me.getX();
		    centerY = me.getY();
		}
	    });
	
	addMouseMotionListener(new MouseMotionAdapter(){
		public void mouseDragged(MouseEvent me){
		    Graphics g = getGraphics();
		    update(g);
		    int x = me.getX();
		    int y = me.getY();
		    int r = (int)(Math.sqrt((x-centerX)*(x-centerX)+(y-centerY)*(y-centerY)));
		    g.drawOval(centerX-r,centerY-r,2*r,2*r);
		}
	    });
	
	Frame f = new Frame("DrawCircle");
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
    