import java.awt.*;

public class VerticalLine extends CrossLine2 {
    public static void main(String[] args) {
	if (args.length == 0) {
	    System.err.println("Usage: java CrossLine2 <message>");
	}
	else {
	    new VerticalLine(args);
	}
    }

    private static final long serialVersionUID = 0;

    protected VerticalLine(String[] words) {   // CrossLine2 コンストラクタ
	super(words);                        // CrossLineのコンストラクタ実行
    }

    public void paint(Graphics g) {
	for (int x=0; x<width; x++) {
	    for (int y=0; y<height; y++) {
		float r = (float) (x+y)/(height+width-2);
		g.setColor(Color.getHSBColor(r, 1.0f, 1.0f));
		g.fillRect(x,y,1,1);
	    }
	}
	g.setColor(Color.black);
	g.drawLine((width-sw)/2,0,(width-sw)/2,height-1);
	g.drawLine((width+sw)/2,0,(width+sw)/2,height-1);
	g.drawString(message,(width-sw)/2,height/2); // message全体を中央に置くため、widthからswを引いている
    }
}
