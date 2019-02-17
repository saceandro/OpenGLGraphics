import java.awt.*;
import java.awt.event.*;

public class BackgroundListener extends Canvas
    implements MouseListener{
    public static void main(String[] args){
	new BackgroundListener();
    }
    
    private static final long serialVersionUID=0;
    private static final int width=200;
    private static final int height=200;
    
    private BackgroundListener(){
	super();
	setSize(width,height);
	setBackground(Color.green);
	addMouseListener(this);
	
	Frame f = new Frame("BackgroundListener");
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
		    System.exit(0);
		}
	    });
	f.setVisible(true);
    }

    public void mousePressed(MouseEvent me){
	setBackground(Color.red);
	repaint();
    }
    
    public void mouseReleased(MouseEvent me){
	setBackground(Color.green);
	repaint();
    }
    
    public void mouseEntered(MouseEvent me){}
    public void mouseExited(MouseEvent me){}
    public void mouseClicked(MouseEvent me){}
}