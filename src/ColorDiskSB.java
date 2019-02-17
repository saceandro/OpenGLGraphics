import java.awt.*;

public class ColorDiskSB extends ColorDisk {
    public static void main(String[] args) {
	new ColorDiskSB("ColorDiskSB");
    }

    private static final long serialVersionUID=0;

    private ColorDiskSB(String name) {
	super(name);
    }

    public void paint(Graphics g) {
	for (int y=0; y<height; y++) {
	    for (int x=0; x<width; x++) {
		double dx = (double)(x-centerX);
		double dy = (double)(y-centerY);
		float h = (float)(Math.atan2(dy,dx) / (2.0 * Math.PI));
		float s  = (float)(Math.sqrt(dx*dx + dy*dy) / radius);
		if (s>1.0f)
		    s = 0.0f;
		float b = s;
		g.setColor(Color.getHSBColor(h,s,b));
		g.fillRect(x, y, 1, 1);
	    }
	}
    }
}