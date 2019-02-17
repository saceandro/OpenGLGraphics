     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class CrossLines2 extends Canvas {
     5	    public static void main (String[] args) {
     6		if (args.length == 0) {
     7		    System.err.println("Usage: java CrossLines <message>");
     8		}
     9		else {
    10		    new CrossLines2(args);
    11		    new CrossLines2(args);
    12		    new CrossLines2(args);
    13		}
    14	    }
    15	
    16	    private static final long serialVersionUID = 0;
    17	    private static final int width = 400;
    18	    private static final int height = 300;
    19	    private static int count=0;
    20	    private static String message=null;
    21	    private static int sw;
    22	
    23	    protected CrossLines2(String[] words) {
    24		super();
    25		setSize(width,height);
    26		setBackground(Color.black);
    27		setForeground(Color.yellow);
    28		message = words[0];
    29		for (int i=1; i<words.length; i++) {
    30		    message = message + " " + words[i];
    31		}
    32		
    33		Frame f = new Frame("CrossLine "+count); // ウィンドウを区別するため、フレームのタイトルに番号をつけた
    34		f.add(this);
    35		f.pack();
    36		f.addWindowListener(new WindowAdapter() {
    37			public void windowClosing(WindowEvent e) {
    38			    ((Window) e.getWindow()).dispose(); // イベントの発生源のWindowを破棄する
    39			}
    40		    });
    41		f.setVisible(true);
    42	
    43		Font ft = getFont(); // フォントの取得
    44		FontMetrics fm = getFontMetrics(ft); // フォントメトリックスの取得
    45		sw = fm.stringWidth(message); // 現在のフォントでmessageを表示するための有効幅の合計
    46	
    47		count++;
    48	    }
    49	    
    50	    public void paint(Graphics g) {
    51		g.drawLine(0,0,width-1,height-1);
    52		g.drawLine(0,height-1,width-1,0);
    53		g.drawString(message,(width-sw)/2,height/2); // message全体を中央に置くため、widthからswを引いている
    54	    }
    55	}