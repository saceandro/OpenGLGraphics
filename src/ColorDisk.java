import java.awt.*;
import java.awt.event.*;

public class ColorDisk extends Canvas{
    public static void main(String[] args){
	new ColorDisk("ColorDisk");
    }

    private static final long serialVersionUID=0;
    protected static final int width=600;
    protected static final int height=600;
    protected static final int centerX=300;
    protected static final int centerY=299;
    protected static final int radius=250;
    
    protected ColorDisk(String name){
	super();
	setSize(width,height);
	
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
	for (int y=0; y<height; y++){
	    for (int x=0; x<width; x++){
		double dx = (double)(x-centerX);
		double dy = (double)(y-centerY);
		float h = (float)(Math.atan2(dy,dx)/(2.0*Math.PI));
		float s = (float)(Math.sqrt(dx*dx+dy*dy)/radius);
		float b = (s>1.0f) ? 0.0f : 1.0f;
		if (s>1.0f) s = 0.0f;
		g.setColor(Color.getHSBColor(h,s,b));
		g.fillRect(x,y,1,1);
	    }
	}
    }
}