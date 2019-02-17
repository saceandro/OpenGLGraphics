import java.awt.*;
import java.awt.event.*;

public class DrawColorDisk extends Canvas{
    public static void main(String[] args){
	new DrawColorDisk();
    }

    private static final long serialVersionUID=0;
    private static final int width=600;
    private static final int height=600;
    private static int numberOfPoints=64;
    private int centerX,centerY;
    private int x,y;
    private int[][] points;
    private float[] h;
    
    private DrawColorDisk(){
	super();
	setSize(width,height);
	setBackground(Color.white);
	
	addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent me){
		    centerX = me.getX();
		    centerY = me.getY();
		}
		
		public void mouseReleased(MouseEvent me) {
		    Graphics g = getGraphics();
		    update(g);
		    x = me.getX();
		    y = me.getY();
		    int r = (int)(Math.sqrt((x-centerX)*(x-centerX)+(y-centerY)*(y-centerY)));
		    for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
			    double dx = (double)(j-centerX);
			    double dy = (double)(i-centerY);
			    float h = (float)(Math.atan2(dy,dx)/(2.0 * Math.PI));
			    float b = (float)(Math.sqrt(dx*dx + dy*dy)/r);
			    float s = (b>1.0f) ? 0.0f : 1.0f;
			    if (b>1.0f) b = 1.0f;
			    g.setColor(Color.getHSBColor(h,s,b));
			    g.fillRect(j,i,1,1);
			}
		    }
		}  
	    });
	
	addMouseMotionListener(new MouseMotionAdapter(){
		public void mouseDragged(MouseEvent me){
		    Graphics g = getGraphics();
		    update(g);
		    x = me.getX();
		    y = me.getY();
		    int r = (int)(Math.sqrt((x-centerX)*(x-centerX)+(y-centerY)*(y-centerY)));
		    points = new int[numberOfPoints+1][];
		    h = new float[numberOfPoints+1];
		    for (int i=0; i<=numberOfPoints; i++) {
			h[i] = (float)(1.0 * i / numberOfPoints);
			float theta = (float)(2.0 * Math.PI * h[i]);
			points[i] = new int[2];
			points[i][0] = (int)(r * Math.cos(theta)) + centerX;
			points[i][1] = (int)(r * Math.sin(theta)) + centerY;
		    }
		    for (int i=0; i<points.length-1; i++) {
			g.setColor(Color.getHSBColor(h[i], 1.0f, 1.0f));
			g.drawLine(points[i][0], points[i][1],
				       points[i+1][0], points[i+1][1]);
		    }
		}
	    });
	    
	Frame f = new Frame("DrawColorDisk");
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
    