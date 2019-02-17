import java.awt.*;
import java.awt.event.*;

public class BackgroundAnonymous extends Canvas{
    public static void main(String[] args){
	new BackgroundAnonymous();
    }
    
    private static final long serialVersionUID=0;
    private static final int width=200;
    private static final int height=200;
    
    private BackgroundAnonymous(){
	super();
	setSize(width,height);
	setBackground(Color.green);
	
	addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent me){
		    setBackground(Color.red);
		    repaint();
		}
		public void mouseReleased(MouseEvent me){
		    setBackground(Color.green);
		    repaint();
		}
	    });
	
	Frame f = new Frame("BackgroundAnonymous");
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
		