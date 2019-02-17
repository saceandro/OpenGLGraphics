     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class Circle extends Canvas {
     5	    public static void main(String[] args) {
     6		if (args.length == 0)
     7		    System.err.println("Usage: java Circle #");
     8		else
     9		    new Circle("Circle",Integer.parseInt(args[0]));
    10	    }
    11	
    12	    private static final long serialVersionUID = 0;
    13	    private static final int width = 600;
    14	    private static final int height = 600;
    15	    private static final int centerX = 300;
    16	    private static final int centerY = 299;
    17	    private static final int radius = 250;
    18	    protected int[][] points;
    19	
    20	    protected Circle(String name, int numberOfPoints) {
    21		super();
    22		setSize(width,height);
    23		setBackground(Color.white);
    24		setForeground(Color.black);
    25	
    26		points = new int[numberOfPoints+1][];
    27		for (int i = 0; i &lt;= numberOfPoints; i++) {
    28		    double theta = 2.0 * Math.PI * i / numberOfPoints;
    29		    points[i] = new int[2];
    30		    points[i][0] = (int)(radius * Math.cos(theta)) + centerX;
    31		    points[i][1] = (int)(radius * Math.sin(theta)) + centerY;
    32		}
    33	
    34		Frame f = new Frame(name);
    35		f.add(this);
    36		f.pack();
    37		f.addWindowListener(new WindowAdapter() {
    38		    public void windowClosing(WindowEvent e) {
    39			System.exit(0);
    40		    }
    41		    });
    42		f.setVisible(true);
    43	    }
    44	    
    45	    public void paint(Graphics g) {
    46		for (int i = 0; i &lt; points.length -1; i++) {
    47		    g.drawLine(points[i][0],points[i][1],
    48			       points[i+1][0],points[i+1][1]);
    49		}
    50	    }
    51	}