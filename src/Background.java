import java.awt.*;
import java.awt.event.*;

public class Background extends Canvas{
    public static void main(String[] args){
	new Background();
    }

    private static final long serialVersionUID=0;
    private static final int width=200;
    private static final int height=200;
    
    private Background(){
	super();
	setSize(width,height);
	setBackground(Color.green);
	addMouseListener(new BgMouseAdapter(this));
	
	Frame f = new Frame("Background");
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
		    System.exit(0);
		}
	    });
	f.setVisible(true);
    }

    protected void mousePressed(MouseEvent me){
	setBackground(Color.red);
	repaint();
    }

    protected void mouseReleased(MouseEvent me){
	setBackground(Color.green);
	repaint();
    }
}

class BgMouseAdapter extends MouseAdapter{
    private Background bg;
    
    public BgMouseAdapter(Background bg){
	this.bg = bg;
    }
    public void mousePressed(MouseEvent me){
	bg.mousePressed(me);
    }
    public void mouseReleased(MouseEvent me){
	bg.mouseReleased(me);
    }
}
