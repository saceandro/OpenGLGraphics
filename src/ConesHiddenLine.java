import javax.media.opengl.*;

public class ConesHiddenLine extends ConesWire {
    public static void main(String[] args) {
	(new ConesHiddenLine("Cones HiddenLine")).showFrame();
    }
    
    protected ConesHiddenLine(String name) {
	super(name);
    }
    
    public void init(GLAutoDrawable drawable) {
	super.init(drawable);
	gl.glEnable(GL.GL_POLYGON_OFFSET_FILL);
	gl.glPolygonOffset(1.0f, 1.0f);
    }
    
    protected void object() {
	final int NOC = 16;
	float[] background = { 0.0f, 0.0f, 0.0f };
	float[] foreground = { 1.0f, 1.0f, 0.0f };
	for (int i=0; i<NOC; i++) {
	    double t = 2.0*Math.PI*i/NOC;
	    gl.glPushMatrix();
	    gl.glTranslated(1.6*Math.sin(t), 0.0, 1.6*Math.cos(t));
	    gl.glScalef(0.3f, 1.4f, 0.3f);
	    gl.glColor3fv(foreground, 0);
	    coneWire();
	    gl.glColor3fv(background, 0);
	    conePolygon();
	    gl.glPopMatrix();
	}
    }
    
    private void conePolygon() {
	final int NOP = 16;
	float[] apex = { 0.0f, 1.0f, 0.0f };
	float[][] circle = new float[NOP+1][];
	for (int i=0; i<=NOP; i++) {
	    double t = 2.0*Math.PI*i/NOP;
	    circle[i] = new float[3];
	    circle[i][0] = (float)Math.sin(t);
	    circle[i][1] = -1.0f;
	    circle[i][2] = (float)Math.cos(t);
	}
	gl.glBegin(GL.GL_POLYGON);
	for (int i=0; i<NOP; i++) 
	    gl.glVertex3fv(circle[NOP-i-1], 0);
	gl.glEnd();
	gl.glBegin(GL.GL_TRIANGLE_FAN);
	gl.glVertex3fv(apex, 0);
	for (int i=0; i<=NOP; i++) {
	    gl.glVertex3fv(circle[i], 0);
	}
	gl.glEnd();
    }
}