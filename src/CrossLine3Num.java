     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class CrossLine3 extends Canvas {
     5	    public static void main(String[] args) {
     6		new CrossLine3();
     7	    }
     8	    
     9	    private static final long serialVersionUID = 0;
    10	    private static final int width = 400;
    11	    private static final int height = 300;
    12	    private static String message="";
    13	    private static int x, y;
    14	    private static int sw=0;
    15	    private static int count=0;
    16	    
    17	    private CrossLine3() {
    18		super();
    19		setSize(width,height);
    20		setBackground(Color.white);
    21		setForeground(Color.black);
    22		
    23		Frame f = new Frame("CrossLine");
    24		f.add(this);
    25		f.pack();
    26		f.addWindowListener(new WindowAdapter() {
    27			public void windowClosing(WindowEvent e) {
    28			    System.exit(0);
    29			}
    30		    });
    31		f.setVisible(true);
    32		
    33		addMouseListener(new MouseAdapter() {
    34			public void mouseClicked(MouseEvent me) {
    35			    x = me.getX();
    36			    y = me.getY();
    37			    count++;
    38			    repaint();
    39			}
    40		    });
    41		addKeyListener(new KeyAdapter() {
    42			public void keyTyped(KeyEvent ke) {
    43			    char key = ke.getKeyChar();
    44			    if (key == "\b".charAt(0)) {
    45				if (message.length()>0)
    46				    message = message.substring(0, message.length()-1);
    47			    }
    48			    else
    49				message += Character.toString(key);
    50			    Font ft = getFont(); // フォントの取得
    51			    FontMetrics fm = getFontMetrics(ft); // フォントメトリックスの取得
    52			    sw = fm.stringWidth(message); // 現在のフォントでmessageを表示するための有効幅の合計
    53			    repaint();
    54			}
    55		    });
    56	    }
    57	
    58	    public void paint(Graphics g) {
    59		g.drawLine(0,0,width-1,height-1);
    60		g.drawLine(0,height-1,width-1,0);
    61		if (count>0)
    62		    g.drawString(message+"|",x-sw/2,y);
    63	    }
    64	}
