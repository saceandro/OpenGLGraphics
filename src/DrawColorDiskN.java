     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class DrawColorDisk extends Canvas{
     5	    public static void main(String[] args){
     6		new DrawColorDisk();
     7	    }
     8	
     9	    private static final long serialVersionUID=0;
    10	    private static final int width=600;
    11	    private static final int height=600;
    12	    private static int numberOfPoints=64;
    13	    private int centerX,centerY;
    14	    private int x,y;
    15	    private int[][] points;
    16	    private float[] h;
    17	    
    18	    private DrawColorDisk(){
    19		super();
    20		setSize(width,height);
    21		setBackground(Color.white);
    22		
    23		addMouseListener(new MouseAdapter(){
    24			public void mousePressed(MouseEvent me){
    25			    centerX = me.getX();
    26			    centerY = me.getY();
    27			}
    28			
    29			public void mouseReleased(MouseEvent me) {
    30			    Graphics g = getGraphics();
    31			    update(g);
    32			    x = me.getX();
    33			    y = me.getY();
    34			    int r = (int)(Math.sqrt((x-centerX)*(x-centerX)+(y-centerY)*(y-centerY)));
    35			    for (int i=0; i&lt;height; i++) {
    36				for (int j=0; j&lt;width; j++) {
    37				    double dx = (double)(j-centerX);
    38				    double dy = (double)(i-centerY);
    39				    float h = (float)(Math.atan2(dy,dx)/(2.0 * Math.PI));
    40				    float b = (float)(Math.sqrt(dx*dx + dy*dy)/r);
    41				    float s = (b&gt;1.0f) ? 0.0f : 1.0f;
    42				    if (b&gt;1.0f) b = 1.0f;
    43				    g.setColor(Color.getHSBColor(h,s,b));
    44				    g.fillRect(j,i,1,1);
    45				}
    46			    }
    47			}  
    48		    });
    49		
    50		addMouseMotionListener(new MouseMotionAdapter(){
    51			public void mouseDragged(MouseEvent me){
    52			    Graphics g = getGraphics();
    53			    update(g);
    54			    x = me.getX();
    55			    y = me.getY();
    56			    int r = (int)(Math.sqrt((x-centerX)*(x-centerX)+(y-centerY)*(y-centerY)));
    57			    points = new int[numberOfPoints+1][];
    58			    h = new float[numberOfPoints+1];
    59			    for (int i=0; i&lt;=numberOfPoints; i++) {
    60				h[i] = (float)(1.0 * i / numberOfPoints);
    61				float theta = (float)(2.0 * Math.PI * h[i]);
    62				points[i] = new int[2];
    63				points[i][0] = (int)(r * Math.cos(theta)) + centerX;
    64				points[i][1] = (int)(r * Math.sin(theta)) + centerY;
    65			    }
    66			    for (int i=0; i&lt;points.length-1; i++) {
    67				g.setColor(Color.getHSBColor(h[i], 1.0f, 1.0f));
    68				g.drawLine(points[i][0], points[i][1],
    69					       points[i+1][0], points[i+1][1]);
    70			    }
    71			}
    72		    });
    73		    
    74		Frame f = new Frame("DrawColorDisk");
    75		f.add(this);
    76		f.pack();
    77		f.addWindowListener(new WindowAdapter(){
    78			public void windowClosing(WindowEvent e){
    79			    System.exit(0);
    80			}
    81		    });
    82		f.setVisible(true);
    83	    }
    84	}
    85	    