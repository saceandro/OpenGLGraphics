import javax.media.opengl.*;

public class CubeAngle extends CubePosition {
    public static void main(String[] args) {
	if (args.length>0 && args.length<4)
	    (new CubeAngle("CubeAngle Demo", args)).showFrame();
	else
	    (new CubeAngle("CubeAngle Demo")).showFrame();
    }
    
    private double fieldOfView=25.0, near=1.0, far=20.0;
    private float depth=-10.0f;
    private float rotx=20.0f, roty=-30.0f, rotz=0.0f;
    
    protected CubeAngle(String name) {
	super(name);
    }
    
    protected CubeAngle(String name, String[] args) {
	super(name);
	switch (args.length) {
	case 1:
	    fieldOfView = Double.parseDouble(args[0]); break;
	case 2:
	    near = Double.parseDouble(args[0]);
	    far  = Double.parseDouble(args[1]); break;
	case 3:
	    rotx = Float.parseFloat(args[0]);
	    roty = Float.parseFloat(args[1]);
	    rotz = Float.parseFloat(args[2]);
	}
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	double aspect = (double) w / (double) h;
	gl.glMatrixMode(GL.GL_PROJECTION);
	gl.glLoadIdentity();
	glu.gluPerspective(fieldOfView, aspect, near, far);
	
	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glLoadIdentity();
	gl.glTranslatef(0.0f, 0.0f, depth);
    }
    
    public void display(GLAutoDrawable drawable) {
	gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	
	gl.glPushMatrix();
	gl.glRotatef(rotx, 1.0f, 0.0f, 0.0f);
	gl.glRotatef(roty, 0.0f, 1.0f, 0.0f);
	gl.glRotatef(rotz, 0.0f, 0.0f, 1.0f);
	cube();
	gl.glPopMatrix();
    }
}