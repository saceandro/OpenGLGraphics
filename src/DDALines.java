import java.awt.*;

public class DDALines extends Lines {
    public static void main(String[] args) {
	new DDALines("DDALines");
    }

    private static final long serialVersionUID = 0;

    private DDALines(String name) {
	super(name);
    }

    public void paint(Graphics g) {
	for(int i=0; i < points.length; i++) {
	    if (points[i][0] >= points[i][1]) {
		int n = points[i][0];
		int dn = points[i][1] << 1;
		int dr = dn - (n << 1);
		int e = dn - n;
		for (int x = 0, y = 0; x <= n; x++) {
		    g.fillRect(x,y,1,1);
		    if (e > 0) {
			y++; e += dr;
		    }
		    else {
			e += dn;
		    }
		}
	    }
	    else {
		int n = points[i][1];
		int dn = points[i][0] << 1;
		int dr = dn - (n <<1);
		int e = dn - n;
		for (int x = 0, y = 0; y <= n; y++) {
		    g.fillRect(x,y,1,1);
		    if (e > 0) {
			x++; e += dr;
		    }
		    else {
			e += dn;
		    }
		}
	    }
	}
    }
}