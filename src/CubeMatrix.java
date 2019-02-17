import javax.media.opengl.*;

public class CubeMatrix extends CubePosition {
    public static void main(String[] args) {
	if (args.length==3)
	    (new CubeMatrix("CubeMatrix Demo", args)).showFrame();
	else
	    (new CubeMatrix("CubeMatrix Demo")).showFrame();
    }
    
    protected CubeMatrix(String name) {
	super(name);
    }
    
    protected CubeMatrix(String name, String[] args) {
	super(name, args);
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	final double fieldOfView=25.0, near=1.0, far=20.0;
	
	double aspect = (double) w / (double) h;
	gl.glMatrixMode(GL.GL_PROJECTION);
	gl.glLoadIdentity();
	glu.gluPerspective(fieldOfView, aspect, near, far);
	
	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glLoadIdentity();
	double eye_xz = Math.sqrt(eye_x * eye_x + eye_z * eye_z);
	double eye_xyz= Math.sqrt(eye_xz* eye_xz+ eye_y * eye_y);
	double[] translate = {
	    1.0, 0.0,     0.0, 0.0,
	    0.0, 1.0,     0.0, 0.0,
	    0.0, 0.0,     1.0, 0.0,
	    0.0, 0.0,-eye_xyz, 1.0
	};
	gl.glMultMatrixd(translate, 0);
	double sinP = eye_y / eye_xyz, cosP = eye_xz / eye_xyz;
	double[] rotateX = {
	    1.0,   0.0,  0.0, 0.0,
	    0.0,  cosP, sinP, 0.0,
	    0.0, -sinP, cosP, 0.0,
	    0.0,   0.0,  0.0, 1.0
	};
	gl.glMultMatrixd(rotateX, 0);
	double sinT = eye_x / eye_xz, cosT = eye_z / eye_xz;
	double[] rotateY = {
	    cosT, 0.0, sinT, 0.0,
	    0.0,  1.0,  0.0, 0.0,
	    -sinT,0.0, cosT, 0.0,
	    0.0,  0.0,  0.0, 1.0
	};
	gl.glMultMatrixd(rotateY, 0);
    }
}