     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class Lines extends Canvas {
     5	    public static void main(String[] args) {
     6		new Lines("Lines");
     7	    }
     8	
     9	    private static final long serialVersionUID = 0;
    10	    private static final int width = 600;
    11	    private static final int height = 600;
    12	    protected static final int[][] points =
    13	    {{10,599},{30,599},{75,599},{150,599},{300,599},{599,599},
    14	     {599,300},{599,150},{599,75},{599,30},{599,10}};
    15	    
    16	    protected Lines(String name) {
    17		super();
    18		setSize(width,height);
    19		setBackground(Color.white);
    20		setForeground(Color.black);
    21		Frame f = new Frame(name);
    22		f.add(this);
    23		f.pack();
    24		f.addWindowListener(new WindowAdapter() {
    25			public void windowClosing(WindowEvent e) {
    26			    System.exit(0);
    27			}
    28		    });
    29		f.setVisible(true);
    30	    }
    31	
    32	    public void paint(Graphics g) {
    33		for (int i = 0; i &lt; points.length; i++) {
    34		    g.drawLine(0,0,points[i][0],points[i][1]);
    35		}
    36	    }
    37	}