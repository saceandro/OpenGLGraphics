import java.awt.*;
import java.awt.event.*;

public class CrossLines2 extends Canvas {
    public static void main (String[] args) {
	if (args.length == 0) {
	    System.err.println("Usage: java CrossLines <message>");
	}
	else {
	    new CrossLines2(args);
	    new CrossLines2(args);
	    new CrossLines2(args);
	}
    }

    private static final long serialVersionUID = 0;
    private static final int width = 400;
    private static final int height = 300;
    private static int count=0;
    private static String message=null;
    private static int sw;

    protected CrossLines2(String[] words) {
	super();
	setSize(width,height);
	setBackground(Color.black);
	setForeground(Color.yellow);
	message = words[0];
	for (int i=1; i<words.length; i++) {
	    message = message + " " + words[i];
	}
	
	Frame f = new Frame("CrossLine "+count); // ウィンドウを区別するため、フレームのタイトルに番号をつけた
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    ((Window) e.getWindow()).dispose(); // イベントの発生源のWindowを破棄する
		}
	    });
	f.setVisible(true);

	Font ft = getFont(); // フォントの取得
	FontMetrics fm = getFontMetrics(ft); // フォントメトリックスの取得
	sw = fm.stringWidth(message); // 現在のフォントでmessageを表示するための有効幅の合計

	count++;
    }
    
    public void paint(Graphics g) {
	g.drawLine(0,0,width-1,height-1);
	g.drawLine(0,height-1,width-1,0);
	g.drawString(message,(width-sw)/2,height/2); // message全体を中央に置くため、widthからswを引いている
    }
}