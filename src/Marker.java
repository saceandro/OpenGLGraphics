import java.awt.*;
import java.awt.event.*;

public class Marker extends Canvas{
    public static void main(String[] args){
	new Marker();
    }
    
    private static final long serialVersionUID=0;
    private static final int width=400;
    private static final int height=400;
    private static final int SIZE=10;
    
    private Marker(){
	super();
	setSize(width,height);
	setBackground(Color.white);
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
		    g.fillRect(x-(SIZE/2), y-(SIZE/2), SIZE, SIZE);
		}
	    });
	
	Frame f = new Frame("Marker");
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
		
    
    