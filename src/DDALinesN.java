     1	import java.awt.*;
     2	
     3	public class DDALines extends Lines {
     4	    public static void main(String[] args) {
     5		new DDALines("DDALines");
     6	    }
     7	
     8	    private static final long serialVersionUID = 0;
     9	
    10	    private DDALines(String name) {
    11		super(name);
    12	    }
    13	
    14	    public void paint(Graphics g) {
    15		for(int i=0; i &lt; points.length; i++) {
    16		    if (points[i][0] &gt;= points[i][1]) {
    17			int n = points[i][0];
    18			int dn = points[i][1] &lt;&lt; 1;
    19			int dr = dn - (n &lt;&lt; 1);
    20			int e = dn - n;
    21			for (int x = 0, y = 0; x &lt;= n; x++) {
    22			    g.fillRect(x,y,1,1);
    23			    if (e &gt; 0) {
    24				y++; e += dr;
    25			    }
    26			    else {
    27				e += dn;
    28			    }
    29			}
    30		    }
    31		    else {
    32			int n = points[i][1];
    33			int dn = points[i][0] &lt;&lt; 1;
    34			int dr = dn - (n &lt;&lt;1);
    35			int e = dn - n;
    36			for (int x = 0, y = 0; y &lt;= n; y++) {
    37			    g.fillRect(x,y,1,1);
    38			    if (e &gt; 0) {
    39				x++; e += dr;
    40			    }
    41			    else {
    42				e += dn;
    43			    }
    44			}
    45		    }
    46		}
    47	    }
    48	}