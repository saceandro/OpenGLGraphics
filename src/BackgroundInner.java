import java.awt.*;
import java.awt.event.*;

public class BackgroundInner extends Canvas{
    public static void main(String[] args){
	new BackgroundInner();
    }
    
    private static final long serialVersionUID=0;
    private static final int width=200;
    private static final int height=200;
    
    private BackgroundInner(){
	super();
	setSize(width,height);
	setBackground(Color.green);
	addMouseListener(new BgMouseAdapter());
	
	Frame f = new Frame("BackgroundInner");
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
		    System.exit(0);
		}
	    });
	f.setVisible(true);
    }
    
    class BgMouseAdapter extends MouseAdapter{
	public void mousePressed(MouseEvent me){
	    setBackground(Color.red);
	    repaint();
	}
	public void mouseReleased(MouseEvent me){
	    setBackground(Color.green);
	    repaint();
	}
    }
}
		