import java.awt.*;
import java.awt.event.*;

public class DrawColorDia extends Canvas {
    public static void main(String[] args) {
	new DrawColorDia();
    }

    private static final long serialVerisonUID=0;
    private static final int width=600, height=600;
    private int centerX, centerY;
    private int x,y;
    private float h, s;
    
    private DrawColorDia() {
	super();
	setSize(width, height);
	setBackground(Color.white);
	
	addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent me) {
		    centerX = me.getX();
		    centerY = me.getY();
		}

		public void mouseReleased(MouseEvent me) {
		    Graphics g = getGraphics();
		    x = me.getX();
		    y = me.getY();
		    int dx = Math.abs(x - centerX);
		    int dy = Math.abs(y - centerY);
		    double r = Math.pow((double)dx, 2.0/3.0) + Math.pow((double)dy, 2.0/3.0);
		    for (int j=0; j<height; j++) {
			for (int i=0; i<width; i++) {
			    int di = Math.abs(i - centerX);
			    int dj = Math.abs(j - centerY);
			    double dist = Math.pow((double)di, 2.0/3.0)
				+ Math.pow((double)dj, 2.0/3.0);
			    if (dist < r) {
				if ((me.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0) {
				    h = (float)(di+dj)/(dx+dy);
				    s = (float)(dist/r);
				}
				else {
				    s = (float)(di+dj)/(dx+dy);
				    h = (float)(dist/r);
				}
				    g.setColor(Color.getHSBColor(h, s, 1.0f));
				    g.fillRect(i,j,1,1);
			    }
			}
		    }
		}
	    });

	Frame f = new Frame("DrawColorDia");
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
	f.setVisible(true);
    }
}