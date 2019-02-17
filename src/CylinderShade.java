import javax.media.opengl.*;

public class CylinderShade extends ObjectShade {
    public static void main(String[] args) {
	if (args.length!=1 || Integer.parseInt(args[0])<3)
	    System.err.println("Usage: java CylinderShade #(>=3)");
	else {
	    numberOfPoints = Integer.parseInt(args[0]);
	    (new CylinderShade("Cylinder Shade")).showFrame();
	}
    }
    
    static int numberOfPoints=0;
    //    static final float L = 2.0f;
    
    protected CylinderShade(String name) {
	super(name);
    }
    
    protected void object() {
	float[] diffuse  = { 0.8f, 0.3f, 0.8f, 1.0f };
	float[] specular = { 0.9f, 0.9f, 0.9f, 1.0f };
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, diffuse, 0);
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, specular, 0);
	gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 100.0f);
		
	float[][] normaltb = { { 0.0f,  1.0f, 0.0f }, { 0.0f, -1.0f, 0.0f } };

	/*float[] tb = { 1.0f, 0.0f };
	float[][] pnts = new float[2][numberOfPoints];
	for (int j=0; j<2; j++) {
	for (int i=0; i<=numberOfPoints; i++) {
	    float theta = (float)(2.0*Math.PI*i/numberOfPoints);
	    float[] pnts[0][i] = { Math.sin(theta), 1.0f, Math.cos(theta) };
	}
	for (int i=0; i<=numberOfPoints; i++) {
	    float theta = (float)(2.0*Math.PI*i/numberOfPoints);
	    float pnts[1][i] = { Math.sin(theta), -1.0f, Math.cos(theta) };
	}
	    }
	for (int j=0; j<=1; j++) {
	    gl.glBegin(GL.GL_POLYGON);
	    gl.glNormal3fv(normaltb[j], 0);
	    for (int i=0; i<numberOfPoints; i++) {
		gl.glVertex3fv(pnts[j][i], 0);
	    }
	    gl.glEnd();
	}
	}*/
	gl.glShadeModel(GL.GL_FLAT);
	gl.glBegin(GL.GL_POLYGON);
	gl.glNormal3fv(normaltb[0], 0);
	for (int i=0; i<numberOfPoints; i++) {
	    float theta = (float)(2.0*Math.PI*i/numberOfPoints);
	    float[] pnt = {(float)Math.sin(theta), 1.0f, (float)Math.cos(theta) };
	    gl.glVertex3fv(pnt, 0);
	}
	gl.glEnd();
	gl.glBegin(GL.GL_POLYGON);
	gl.glNormal3fv(normaltb[1], 0);
	for (int i=numberOfPoints-1; i>=0; i--) {
	    float theta = (float)(2.0*Math.PI*i/numberOfPoints);
	    float[] pnt = {(float)Math.sin(theta), -1.0f, (float)Math.cos(theta) };
	    gl.glVertex3fv(pnt, 0);
	}
	gl.glEnd();
	gl.glShadeModel(GL.GL_SMOOTH);
	gl.glBegin(GL.GL_QUADS);
	for (int i=0; i<numberOfPoints; i++) {
	    float theta0 = (float)(2.0*Math.PI*i/numberOfPoints);
	    float theta1 = (float)(2.0*Math.PI*(i+1)/numberOfPoints);
	    gl.glNormal3fv(new float[] { (float)Math.sin(theta0),  0.0f, (float)Math.cos(theta0) }, 0);
	    gl.glVertex3fv(new float[] { (float)Math.sin(theta0),  1.0f, (float)Math.cos(theta0) }, 0);
	    gl.glVertex3fv(new float[] { (float)Math.sin(theta0), -1.0f, (float)Math.cos(theta0) }, 0);
	    gl.glNormal3fv(new float[] { (float)Math.sin(theta1),  0.0f, (float)Math.cos(theta1) }, 0);
	    gl.glVertex3fv(new float[] { (float)Math.sin(theta1), -1.0f, (float)Math.cos(theta1) }, 0);
	    gl.glVertex3fv(new float[] { (float)Math.sin(theta1),  1.0f, (float)Math.cos(theta1) }, 0);
	}
	gl.glEnd();
    }
}
	