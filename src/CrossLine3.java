import java.awt.*;
import java.awt.event.*;

public class CrossLine3 extends Canvas {
    public static void main(String[] args) {
	new CrossLine3();
    }
    
    private static final long serialVersionUID = 0;
    private static final int width = 400;
    private static final int height = 300;
    private static String message="";
    private static int x, y;
    private static int sw=0;
    private static int count=0;
    
    private CrossLine3() {
	super();
	setSize(width,height);
	setBackground(Color.white);
	setForeground(Color.black);
	
	Frame f = new Frame("CrossLine");
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
	f.setVisible(true);
	
	addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent me) {
		    x = me.getX();
		    y = me.getY();
		    count++;
		    repaint();
		}
	    });
	addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent ke) {
		    char key = ke.getKeyChar();
		    if (key == "\b".charAt(0)) {
			if (message.length()>0)
			    message = message.substring(0, message.length()-1);
		    }
		    else
			message += Character.toString(key);
		    Font ft = getFont(); // フォントの取得
		    FontMetrics fm = getFontMetrics(ft); // フォントメトリックスの取得
		    sw = fm.stringWidth(message); // 現在のフォントでmessageを表示するための有効幅の合計
		    repaint();
		}
	    });
    }

    public void paint(Graphics g) {
	g.drawLine(0,0,width-1,height-1);
	g.drawLine(0,height-1,width-1,0);
	if (count>0)
	    g.drawString(message+"|",x-sw/2,y);
    }
}
