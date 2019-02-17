     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class CrossLine extends Canvas {
     5	    public static void main(String[] args) {
     6		if (args.length == 0) {
     7		    System.err.println("Usage: java CrossLine <message>");
     8		}
     9		else {
    10		    new CrossLine(args);
    11		}
    12	    }
    13	
    14	    private static final long serialVersionUID = 0;
    15	    private static final int width = 400;
    16	    private static final int height = 300;
    17	    private static String message = null;
    18	    
    19	    protected CrossLine(String[] words) {
    20		super();
    21		setSize(width,height);
    22		setBackground(Color.white);
    23		setForeground(Color.black);
    24		message = words[0];
    25		
    26		Frame f = new Frame("CrossLine");
    27		f.add(this);
    28		f.pack();
    29		f.addWindowListener(new WindowAdapter() {
    30			public void windowClosing(WindowEvent e) {
    31			    System.exit(0);
    32			}
    33		    });
    34		f.setVisible(true);
    35	    }
    36	
    37	    public void paint(Graphics g) {
    38		g.drawLine(0,0,width-1,height-1);
    39		g.drawLine(0,height-1,width-1,0);
    40		g.drawString(message,width/2,height/2);
    41	    }
    42	}
    43		   
