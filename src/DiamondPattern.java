import java.awt.*;

public class DiamondPattern extends Circle{
    public static void main(String[] args){
	if (args.length == 0)
	    System.err.println("Usage: java DiamondPattern #");
	else
	    new DiamondPattern("DiamondPattern",Integer.parseInt(args[0]));
    }
    private static final long serialVersionUID=0;
    
    private DiamondPattern(String name, int numberOfPoints){
	super(name,numberOfPoints);
    }
    
    public void paint(Graphics g){
	for (int i=1; i<points.length-1; i++){
	    for (int j=0; j<i; j++){
		g.drawLine(points[i][0], points[i][1],
			   points[j][0], points[j][1]);
	    }
	}
    }
}