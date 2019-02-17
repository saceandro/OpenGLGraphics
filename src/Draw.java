import java.awt.*;
import java.awt.event.*;

public class Draw extends Canvas{
    public static void main(String[] args){
	new Draw();
    }
    
    private static final long serialVersionUID=0;
    private static final int width=400;
    private static final int height=400;
    private int oldX;
    private int oldY;
    
    private Draw(){
	super();
	setSize(height,width);
	setBackground(Color.white);
	setForeground(Color.black);
	
	addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent me){
		    oldX = me.getX();
		    oldY = me.getY();
		}
	    });
	
	addMouseMotionListener(new MouseMotionAdapter(){
		public void mouseDragged(MouseEvent me){
		    int x = me.getX();
		    int y = me.getY();
		    getGraphics().drawLine(oldX,oldY,x,y);
		    oldX = x;
		    oldY = y;
		}
	    });
	
	Frame f = new Frame("Draw");
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