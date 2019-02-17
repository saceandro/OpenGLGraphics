     1	import java.awt.*;
     2	
     3	public class DiamondPattern extends Circle{
     4	    public static void main(String[] args){
     5		if (args.length == 0)
     6		    System.err.println("Usage: java DiamondPattern #");
     7		else
     8		    new DiamondPattern("DiamondPattern",Integer.parseInt(args[0]));
     9	    }
    10	    private static final long serialVersionUID=0;
    11	    
    12	    private DiamondPattern(String name, int numberOfPoints){
    13		super(name,numberOfPoints);
    14	    }
    15	    
    16	    public void paint(Graphics g){
    17		for (int i=1; i&lt;points.length-1; i++){
    18		    for (int j=0; j&lt;i; j++){
    19			g.drawLine(points[i][0], points[i][1],
    20				   points[j][0], points[j][1]);
    21		    }
    22		}
    23	    }
    24	}