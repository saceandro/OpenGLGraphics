import java.awt.*;
import javax.media.opengl.*;

public class ColorCone extends ObjectTranslate {
    public static void main(String[] args) {
	//if (args.length==0)
	//  System.err.println("Usage: java ColorCubeHSB #");
	//else
	(new ColorCone("ColorCone"/*, Integer.parseInt(args[0])*/)).showFrame();
    }
    
    protected ColorCone(String name/*, int numberOfPoints*/) {
	super(name);
	//	object(numberOfPoints);
    }
    
    protected void object(/*int numberOfPoints*/) {
	int numberOfPoints = 100;
	float[][] center = { { 0.0f, -1.0f, 0.0f }, { 0.0f, 1.0f, 0.0f } };
	float[][] centerRGB = { { 0.0f, 0.0f, 0.0f }, { 1.0f, 1.0f, 1.0f } };
	gl.glBegin(GL.GL_TRIANGLE_FAN);
	gl.glColor3fv(centerRGB[0], 0);
	gl.glVertex3fv(center[0], 0);
	for (int i=0; i<=numberOfPoints; i++) {
	    float rate = (float)i / numberOfPoints;
	    float[] p = { (float)(-Math.sin(2*Math.PI*rate)), 1.0f, (float)(Math.cos(2*Math.PI*rate)) };
	    gl.glColor3fv(Color.getHSBColor((float)(1.0f-rate), 1.0f, 1.0f).getRGBColorComponents(null), 0);
	    gl.glVertex3fv(p, 0);
	}
	gl.glEnd();
	gl.glBegin(GL.GL_TRIANGLE_FAN);
	gl.glColor3fv(centerRGB[1], 0);
	gl.glVertex3fv(center[1], 0);
	for (int i=0; i<=numberOfPoints; i++) {
	    float rate = (float)i / numberOfPoints;
	    float[] p = { (float)(Math.sin(2*Math.PI*rate)), 1.0f, (float)(Math.cos(2*Math.PI*rate)) };
	    gl.glColor3fv(Color.getHSBColor(rate, 1.0f, 1.0f).getRGBColorComponents(null), 0);
	    gl.glVertex3fv(p, 0);
	}
	gl.glEnd();
    }
}