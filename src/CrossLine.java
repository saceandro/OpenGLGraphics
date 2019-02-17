import java.awt.*;
import java.awt.event.*;

public class CrossLine extends Canvas {
    public static void main(String[] args) {
	if (args.length == 0) {
	    System.err.println("Usage: java CrossLine <message>");
	}
	else {
	    new CrossLine(args);
	}
    }

    private static final long serialVersionUID = 0;
    private static final int width = 400;
    private static final int height = 300;
    private static String message = null;
    
    protected CrossLine(String[] words) {
	super();
	setSize(width,height);
	setBackground(Color.white);
	setForeground(Color.black);
	message = words[0];
	
	Frame f = new Frame("CrossLine");
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
	f.setVisible(true);
    }

    public void paint(Graphics g) {
	g.drawLine(0,0,width-1,height-1);
	g.drawLine(0,height-1,width-1,0);
	g.drawString(message,width/2,height/2);
    }
}
	   
