import java.awt.*;
import java.awt.event.*;

public class ColorDiskBrightness extends ColorDisk{
    public static void main(String[] args){
	new ColorDiskBrightness("ColorDiskBrightness");
    }

    private static final long serialVerionUID=0;
    
    protected ColorDiskBrightness(String name){
	super(name);
    }
    
    public void paint(Graphics g){
	for (int y=0; y<height; y++){
	    for (int x=0; x<width; x++){
		double dx = (double)(x-centerX);
		double dy = (double)(y-centerY);
		float h = (float)(Math.atan2(dy,dx)/(2.0*Math.PI));
		float b = (float)(Math.sqrt(dx*dx + dy*dy)/radius);
		float s = (b > 1.0f) ? 0.0f : 1.0f;
		if (b>1.0f) b=1.0f;
		g.setColor(Color.getHSBColor(h,s,b));
		g.fillRect(x,y,1,1);
	    }
	}
    }
}