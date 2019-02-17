     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class DrawColorDia extends Canvas {
     5	    public static void main(String[] args) {
     6		new DrawColorDia();
     7	    }
     8	
     9	    private static final long serialVerisonUID=0;
    10	    private static final int width=600, height=600;
    11	    private int centerX, centerY;
    12	    private int x,y;
    13	    private float h, s;
    14	    
    15	    private DrawColorDia() {
    16		super();
    17		setSize(width, height);
    18		setBackground(Color.white);
    19		
    20		addMouseListener(new MouseAdapter() {
    21			public void mousePressed(MouseEvent me) {
    22			    centerX = me.getX();
    23			    centerY = me.getY();
    24			}
    25	
    26			public void mouseReleased(MouseEvent me) {
    27			    Graphics g = getGraphics();
    28			    x = me.getX();
    29			    y = me.getY();
    30			    int dx = Math.abs(x - centerX);
    31			    int dy = Math.abs(y - centerY);
    32			    double r = Math.pow((double)dx, 2.0/3.0) + Math.pow((double)dy, 2.0/3.0);
    33			    for (int j=0; j&lt;height; j++) {
    34				for (int i=0; i&lt;width; i++) {
    35				    int di = Math.abs(i - centerX);
    36				    int dj = Math.abs(j - centerY);
    37				    double dist = Math.pow((double)di, 2.0/3.0)
    38					+ Math.pow((double)dj, 2.0/3.0);
    39				    if (dist &lt; r) {
    40					if ((me.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0) {
    41					    h = (float)(di+dj)/(dx+dy);
    42					    s = (float)(dist/r);
    43					}
    44					else {
    45					    s = (float)(di+dj)/(dx+dy);
    46					    h = (float)(dist/r);
    47					}
    48					    g.setColor(Color.getHSBColor(h, s, 1.0f));
    49					    g.fillRect(i,j,1,1);
    50				    }
    51				}
    52			    }
    53			}
    54		    });
    55	
    56		Frame f = new Frame("DrawColorDia");
    57		f.add(this);
    58		f.pack();
    59		f.addWindowListener(new WindowAdapter() {
    60			public void windowClosing(WindowEvent e) {
    61			    System.exit(0);
    62			}
    63		    });
    64		f.setVisible(true);
    65	    }
    66	}