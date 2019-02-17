import java.awt.*;
import java.awt.event.*;

public class ClickSpeed extends Canvas implements MouseListener{
    public static void main(String[] args){
	new ClickSpeed("ClickSpeed");
    }
    protected static final int width=400, height=400;
    protected static final int SIZE=10;
    static final int TIMES = 10;
    static long fastest = -1;
    int count = 0;
    long time;
    int x,y;
    boolean error = false;
    
    protected ClickSpeed(String name){
	super();
	setSize(width,height);
	addMouseListener(this);
	Frame f = new Frame(name);
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
		    System.exit(0);
		}
	    });
	f.setVisible(true);
    }
    
    public void paint(Graphics g){
	if (count>0){
	    Dimension d = getSize();
	    x = (int) ((d.width-SIZE) * Math.random());
	    y = (int) ((d.height-SIZE) * Math.random());
	    
	    if (error)
		g.setColor(Color.RED);
	    else
		g.setColor(Color.BLACK);
	    g.fillRect(x,y,SIZE,SIZE);
	}
    }

    public void mousePressed(MouseEvent me){
	if(count>0){
	    if ((me.getModifiers() & MouseEvent.BUTTON1_MASK) != 0){
		int xc = me.getX();
		int yc = me.getY();
		if ((x<xc) && (xc<(x+SIZE)) && (y<yc) && (yc<(y+SIZE))){
		    count--;
		    error = false;
		}
		else{
		    count++;
		    error = true;
		}
		if (count>0){
		    System.out.println(""+count+" more!!");
		    repaint();
		}
		else{
		    time += System.currentTimeMillis();
		    if((fastest<0) || (fastest>time))
			fastest = time;
		    System.out.println("Finished in "+(time/1000.0)+" secs.  "
				       +"Fastest time: "+(fastest/1000.0)+" secs.  ");
		}
	    }
	    
	}
	else{
	    if(fastest<0)
		System.out.println("Click right button to start.");
	    else
		System.out.println("Click right button to start. Fastest time: "+(fastest/1000.0)+" secs.");
	}
    }
    public void mouseReleased(MouseEvent me){
	if ((me.getModifiers() & InputEvent.BUTTON3_MASK) != 0){
	    count = TIMES;
	    System.out.println("Start clicking ... "+TIMES+" more!!");
	    error = false;
	    time = -System.currentTimeMillis();
	    repaint();
	}
    }
    public void mouseEntered(MouseEvent me){}
    public void mouseExited(MouseEvent me){}
    public void mouseClicked(MouseEvent me){}
}
