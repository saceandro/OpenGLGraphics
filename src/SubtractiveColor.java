import java.awt.*;
import java.awt.event.*;

public class SubtractiveColor extends AdditiveColor{
    public static void main(String[] args){
	new SubtractiveColor("SubtractiveColor");
    }
    
    private static final long serialVersionUID=0;
    
    protected SubtractiveColor(String name){
	super(name);
    }
    
    public void paint(Graphics g){
	for (int y=0; y<height; y++){
	    for (int x=0; x<width; x++){
		float[] color = new float[3];
		for (int i=0; i<3; i++){
		    double dist2 =
			(x-centers[i][0])*(x-centers[i][0])+
			(y-centers[i][1])*(y-centers[i][1]);
		    color[i] = (dist2 < radius2) ? 0.0f : 1.0f;
		}
		g.setColor(new Color(color[0],color[1],color[2]));
		g.fillRect(x,y,1,1);
	    }
	}
    }
}
