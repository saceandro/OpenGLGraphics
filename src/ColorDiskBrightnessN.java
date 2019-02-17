     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class ColorDiskBrightness extends ColorDisk{
     5	    public static void main(String[] args){
     6		new ColorDiskBrightness("ColorDiskBrightness");
     7	    }
     8	
     9	    private static final long serialVerionUID=0;
    10	    
    11	    protected ColorDiskBrightness(String name){
    12		super(name);
    13	    }
    14	    
    15	    public void paint(Graphics g){
    16		for (int y=0; y&lt;height; y++){
    17		    for (int x=0; x&lt;width; x++){
    18			double dx = (double)(x-centerX);
    19			double dy = (double)(y-centerY);
    20			float h = (float)(Math.atan2(dy,dx)/(2.0*Math.PI));
    21			float b = (float)(Math.sqrt(dx*dx + dy*dy)/radius);
    22			float s = (b &gt; 1.0f) ? 0.0f : 1.0f;
    23			if (b&gt;1.0f) b=1.0f;
    24			g.setColor(Color.getHSBColor(h,s,b));
    25			g.fillRect(x,y,1,1);
    26		    }
    27		}
    28	    }
    29	}