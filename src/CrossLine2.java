import java.awt.*;
import java.awt.event.*;

public class CrossLine2 extends Canvas {
    public static void main(String[] args) {
	if (args.length == 0) {
	    System.err.println("Usage: java CrossLine <message>");
	}
	else {
	    new CrossLine2(args);
	}
    }

    private static final long serialVersionUID = 0;
    protected static final int width = 400;  // サブクラスからもアクセスできるように、protectedにしている
    protected static final int height = 300;
    protected static String message = null;
    protected static int sw;
    
    protected CrossLine2(String[] words) {
	super();
	setSize(width,height);
	setBackground(Color.white);
	setForeground(Color.black);
	message = words[0];
	for (int i=1; i<words.length; i++) {
	    message = message + " " + words[i];
	}

	Frame f = new Frame("CrossLine");
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
	f.setVisible(true);

	Font ft = getFont(); // フォントの取得
	FontMetrics fm = getFontMetrics(ft); // フォントメトリックスの取得
	sw = fm.stringWidth(message); // 現在のフォントでmessageを表示するための有効幅の合計
    }

    public void paint(Graphics g) {
	g.drawLine(0,0,width-1,height-1);
	g.drawLine(0,height-1,width-1,0);
	g.drawString(message,(width-sw)/2,height/2);
    }
}
	   
