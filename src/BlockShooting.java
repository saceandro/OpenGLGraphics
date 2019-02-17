import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class BlockShooting extends Applet implements Runnable {
    public static void main(String[] args) {
	new BlockShooting("BlockShooting");
    }
    
    private static Thread runner;
    private static final int width=400, height=400;
    private static int SIZE=10;
    private static int count=0;
    private static int s;
    private static int x,y;
    
    private static boolean error = false;
    
    private BlockShooting(String name) {
	
    }

    public void start() {
	if (runner == null) {
	    runner = new Thread(this);
	    runner.start();
	}
    }
    
    public void stop() {
	if (runner != null) {
	    runner.stop();
	    runner = null;
	}
    }

    public void run() {
	while(true) {
	    for (int i=0; i<400; i++) {
		s = i;
		repaint();
	    }
	}
    }
    
    public void paint(Graphics g) {
	if (count>0) {
	    Dimension d = getSize();
	    x = (int) ((d.width-SIZE) * Math.random());
	    if (error)
		g.setColor(Color.RED);
	    else
		g.setColor(Color.BLACK);
	    g.fillRect(x,s,SIZE,SIZE);
	}
    }    
}