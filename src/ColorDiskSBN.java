     1	import java.awt.*;
     2	
     3	public class ColorDiskSB extends ColorDisk {
     4	    public static void main(String[] args) {
     5		new ColorDiskSB("ColorDiskSB");
     6	    }
     7	
     8	    private static final long serialVersionUID=0;
     9	
    10	    private ColorDiskSB(String name) {
    11		super(name);
    12	    }
    13	
    14	    public void paint(Graphics g) {
    15		for (int y=0; y&lt;height; y++) {
    16		    for (int x=0; x&lt;width; x++) {
    17			double dx = (double)(x-centerX);
    18			double dy = (double)(y-centerY);
    19			float h = (float)(Math.atan2(dy,dx) / (2.0 * Math.PI));
    20			float s  = (float)(Math.sqrt(dx*dx + dy*dy) / radius);
    21			if (s&gt;1.0f)
    22			    s = 0.0f;
    23			float b = s;
    24			g.setColor(Color.getHSBColor(h,s,b));
    25			g.fillRect(x, y, 1, 1);
    26		    }
    27		}
    28	    }
    29	}