     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class CrossLine2 extends Canvas {
     5	    public static void main(String[] args) {
     6		if (args.length == 0) {
     7		    System.err.println("Usage: java CrossLine <message>");
     8		}
     9		else {
    10		    new CrossLine2(args);
    11		}
    12	    }
    13	
    14	    private static final long serialVersionUID = 0;
    15	    protected static final int width = 400;  // サブクラスからもアクセスできるように、protectedにしている
    16	    protected static final int height = 300;
    17	    protected static String message = null;
    18	    protected static int sw;
    19	    
    20	    protected CrossLine2(String[] words) {
    21		super();
    22		setSize(width,height);
    23		setBackground(Color.white);
    24		setForeground(Color.black);
    25		message = words[0];
    26		for (int i=1; i<words.length; i++) {
    27		    message = message + " " + words[i];
    28		}
    29	
    30		Frame f = new Frame("CrossLine");
    31		f.add(this);
    32		f.pack();
    33		f.addWindowListener(new WindowAdapter() {
    34			public void windowClosing(WindowEvent e) {
    35			    System.exit(0);
    36			}
    37		    });
    38		f.setVisible(true);
    39	
    40		Font ft = getFont(); // フォントの取得
    41		FontMetrics fm = getFontMetrics(ft); // フォントメトリックスの取得
    42		sw = fm.stringWidth(message); // 現在のフォントでmessageを表示するための有効幅の合計
    43	    }
    44	
    45	    public void paint(Graphics g) {
    46		g.drawLine(0,0,width-1,height-1);
    47		g.drawLine(0,height-1,width-1,0);
    48		g.drawString(message,(width-sw)/2,height/2);
    49	    }
    50	}
    51		   
