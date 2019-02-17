import javax.media.opengl.*;

public class ConeShade extends ObjectShade {
    public static void main(String[] args) {
	if (args.length!=1 || Integer.parseInt(args[0])<3)
	    System.err.println("Usage: java ConeShade #(>=3)");
	else {
	    numberOfPoints = Integer.parseInt(args[0]);
	    (new ConeShade("Cone Shade")).showFrame();
	}
    }
    
    static int numberOfPoints=0;

    protected ConeShade(String name) {
	super(name);
    }

    protected void object() {
	float[] diffuse  = { 0.8f, 0.3f, 0.8f, 1.0f };
	float[] specular = { 0.9f, 0.9f, 0.9f, 1.0f };
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, diffuse, 0);
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, specular, 0);
	gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 100.0f);
	
	gl.glShadeModel(GL.GL_FLAT);
	gl.glBegin(GL.GL_POLYGON);
	gl.glNormal3fv(new float[] { 0.0f, -1.0f, 0.0f }, 0);
	for (int i=numberOfPoints; i>0; i--) {
	    float theta = (float)(2.0*Math.PI*i/numberOfPoints);
	    gl.glVertex3fv(new float[] { (float)Math.sin(theta), -1.0f, (float)Math.cos(theta) }, 0);
	}
	gl.glEnd();
	
	gl.glShadeModel(GL.GL_SMOOTH);

	/*gl.glBegin(GL.GL_TRIANGLE_FAN);
	gl.glNormal3fv(new float[] { 0.0f, 1.0f, 0.0f }, 0);
	gl.glVertex3fv(new float[] { 0.0f, 1.0f, 0.0f }, 0);
	for (int i=0; i<=numberOfPoints; i++) {
	    float theta = (float)(2.0*Math.PI*i/numberOfPoints);
	    gl.glNormal3fv(new float[] { (float)Math.sin(theta),  0.5f, (float)Math.cos(theta) }, 0);
	    gl.glVertex3fv(new float[] { (float)Math.sin(theta), -1.0f, (float)Math.cos(theta) }, 0);
	}
	gl.glEnd();
	*/

	gl.glBegin(GL.GL_TRIANGLES);
	for (int i=0; i<numberOfPoints; i++) {
	    float theta0 = (float)(2.0*Math.PI*((float)(i+0.5)/numberOfPoints));
	    gl.glNormal3fv(new float[] { (float)Math.sin(theta0), 0.5f, (float)Math.cos(theta0) }, 0);
	    gl.glVertex3fv(new float[] { 0.0f, 1.0f, 0.0f }, 0);
	    float theta1 = (float)(2.0*Math.PI*i/numberOfPoints);
	    gl.glNormal3fv(new float[] { (float)Math.sin(theta1),  0.5f, (float)Math.cos(theta1) }, 0);
	    gl.glVertex3fv(new float[] { (float)Math.sin(theta1), -1.0f, (float)Math.cos(theta1) }, 0);
	    float theta2 = (float)(2.0*Math.PI*(i+1)/numberOfPoints);
	    gl.glNormal3fv(new float[] { (float)Math.sin(theta2),  0.5f, (float)Math.cos(theta2) }, 0);
	    gl.glVertex3fv(new float[] { (float)Math.sin(theta2), -1.0f, (float)Math.cos(theta2) }, 0);
	}
	gl.glEnd();
    }
}