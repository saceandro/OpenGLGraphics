     1	import java.awt.*;
     2	import java.awt.event.*;
     3	
     4	public class SubtractiveColor extends AdditiveColor{
     5	    public static void main(String[] args){
     6		new SubtractiveColor("SubtractiveColor");
     7	    }
     8	    
     9	    private static final long serialVersionUID=0;
    10	    
    11	    protected SubtractiveColor(String name){
    12		super(name);
    13	    }
    14	    
    15	    public void paint(Graphics g){
    16		for (int y=0; y&lt;height; y++){
    17		    for (int x=0; x&lt;width; x++){
    18			float[] color = new float[3];
    19			for (int i=0; i&lt;3; i++){
    20			    double dist2 =
    21				(x-centers[i][0])*(x-centers[i][0])+
    22				(y-centers[i][1])*(y-centers[i][1]);
    23			    color[i] = (dist2 &lt; radius2) ? 0.0f : 1.0f;
    24			}
    25			g.setColor(new Color(color[0],color[1],color[2]));
    26			g.fillRect(x,y,1,1);
    27		    }
    28		}
    29	    }
    30	}
