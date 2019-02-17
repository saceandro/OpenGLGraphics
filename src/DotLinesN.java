     1	import java.awt.*;
     2	
     3	public class DotLines extends Lines {
     4	    public static void main(String[] args) {
     5		new DotLines("DotLines");
     6	    }
     7	
     8	    private static final long serialVersionUID = 0;
     9	
    10	    private DotLines(String name) {
    11		super(name);
    12	    }
    13	
    14	    public void paint(Graphics g) {
    15		for (int i = 0; i &lt; points.length; i++) {
    16		    if (points[i][0] &gt;= points[i][1]) {
    17			int n = points[i][0];
    18			double d = ((double)points[i][1])/n;
    19			for (int x = 0; x &lt;= n; x++) {
    20			    g.fillRect(x, (int) (d*x+0.5),1,1);
    21			}
    22		    }
    23		    else {
    24			int n = points[i][1];
    25			double d = ((double)points[i][0])/n;
    26			for (int y = 0; y &lt;= n; y++) {
    27			    g.fillRect((int) (d*y+0.5),y,1,1);
    28			}
    29		    }
    30		}
    31	    }
    32	}