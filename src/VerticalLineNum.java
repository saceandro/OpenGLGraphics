     1	import java.awt.*;
     2	
     3	public class VerticalLine extends CrossLine2 {
     4	    public static void main(String[] args) {
     5		if (args.length == 0) {
     6		    System.err.println("Usage: java CrossLine2 <message>");
     7		}
     8		else {
     9		    new VerticalLine(args);
    10		}
    11	    }
    12	
    13	    private static final long serialVersionUID = 0;
    14	
    15	    protected VerticalLine(String[] words) {   // CrossLine2 コンストラクタ
    16		super(words);                        // CrossLineのコンストラクタ実行
    17	    }
    18	
    19	    public void paint(Graphics g) {
    20		for (int x=0; x<width; x++) {
    21		    for (int y=0; y<height; y++) {
    22			float r = (float) (x+y)/(height+width-2);
    23			g.setColor(Color.getHSBColor(r, 1.0f, 1.0f));
    24			g.fillRect(x,y,1,1);
    25		    }
    26		}
    27		g.setColor(Color.black);
    28		g.drawLine((width-sw)/2,0,(width-sw)/2,height-1);
    29		g.drawLine((width+sw)/2,0,(width+sw)/2,height-1);
    30		g.drawString(message,(width-sw)/2,height/2); // message全体を中央に置くため、widthからswを引いている
    31	    }
    32	}
