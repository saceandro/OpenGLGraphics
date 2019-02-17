import java.awt.*;

public class DotLines extends Lines {
    public static void main(String[] args) {
	new DotLines("DotLines");
    }

    private static final long serialVersionUID = 0;

    private DotLines(String name) {
	super(name);
    }

    public void paint(Graphics g) {
	for (int i = 0; i < points.length; i++)
	    if (points[i][0] >= points[i][1]) {
		int n = points[i][0];
		double d = ((double)points[i][1])/n;
		for (int x = 0; x <= n; x++) {
		    g.fillRect(x, (int) (d*x+0.5),1,1);
		}
	    }
	    else {
		int n = points[i][1];
		double d = ((double)points[i][0])/n;
		for (int y = 0; y <= n; y++) {
		    g.fillRect((int) (d*y+0.5),y,1,1);
		}
	    }
    }
}