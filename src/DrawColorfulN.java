     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class DrawColorful extends Canvas{
     5	    public static void main(String[] args){
     6		new DrawColorful();
     7	    }
     8	    
     9	    private static final long serialVersionUID=0;
    10	    private static final int width=400;
    11	    private static final int height=400;
    12	    private int prevX;
    13	    private int prevY;
    14	    private float h=0.0f;
    15	    private double period = 1000.0;
    16	
    17	    private DrawColorful(){
    18		super();
    19		setSize(height,width);
    20		setBackground(Color.white);
    21		
    22		addMouseListener(new MouseAdapter(){
    23			public void mousePressed(MouseEvent me){
    24			    prevX = me.getX();
    25			    prevY = me.getY();
    26			}
    27		    });
    28		
    29		addMouseMotionListener(new MouseMotionAdapter(){
    30			public void mouseDragged(MouseEvent me){
    31			    int x = me.getX();
    32			    int y = me.getY();
    33			    int dx = x - prevX;
    34			    int dy = y - prevY;
    35			    double dr = Math.sqrt(dx*dx + dy*dy)/period;
    36			    h += (float) dr;
    37			    if (h&gt;1)
    38				h -= 1.0f;
    39			    Graphics g = getGraphics();
    40			    g.setColor(Color.getHSBColor(h, 1.0f, 1.0f));
    41			    g.drawLine(prevX,prevY,x,y);
    42			    prevX = x;
    43			    prevY = y;
    44			}
    45		    });
    46		
    47		Frame f = new Frame("DrawColorful");
    48		f.add(this);
    49		f.pack();
    50		f.addWindowListener(new WindowAdapter(){
    51			public void windowClosing(WindowEvent e){
    52			    System.exit(0);
    53			}
    54		    });
    55		f.setVisible(true);
    56	    }
    57	}