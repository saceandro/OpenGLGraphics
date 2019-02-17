import java.awt.*;
import java.awt.event.*;

public class Rubberband extends Canvas{
    public static void main(String[] args){
	new Rubberband();
    }

    private static final long serialVersionUID=0;
    private static final int width=400;
    private static final int height=400;
    private int startX;
    private int startY;
    
    private Rubberband(){
	super();
	setSize(width,height);
	setBackground(Color.white);
	setForeground(Color.black);
	
	addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent me){
		    startX = me.getX();
		    startY = me.getY();
		}
	    });
	addMouseMotionListener(new MouseMotionAdapter(){
		public void mouseDragged(MouseEvent me){
		    Graphics g = getGraphics();
		    update(g);
		    int x = me.getX();
		    int y = me.getY();
		    g.drawLine(startX,startY,x,y);
		}
	    });

	Frame f = new Frame("Rubberband");
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