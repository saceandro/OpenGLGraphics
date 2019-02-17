import java.awt.*;
import java.awt.event.*;

public class Lines extends Canvas {
    public static void main(String[] args) {
	new Lines("Lines");
    }

    private static final long serialVersionUID = 0;
    private static final int width = 600;
    private static final int height = 600;
    protected static final int[][] points =
    {{10,599},{30,599},{75,599},{150,599},{300,599},{599,599},
     {599,300},{599,150},{599,75},{599,30},{599,10}};
    
    protected Lines(String name) {
	super();
	setSize(width,height);
	setBackground(Color.white);
	setForeground(Color.black);
	Frame f = new Frame(name);
	f.add(this);
	f.pack();
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
	f.setVisible(true);
    }

    public void paint(Graphics g) {
	for (int i = 0; i < points.length; i++) {
	    g.drawLine(0,0,points[i][0],points[i][1]);
	}
    }
}
