import javax.media.opengl.*;

public class SphereShade extends SubdivideShade {
    public static void main(String[] args) {
	if (args.length==1)
	    depth = Integer.parseInt(args[0]);
	(new SphereShade("Sphere Shade")).showFrame();
    }

    protected SphereShade(String name) {
	super(name);
    }
    
    protected void subdivide(float[] pnt0, float[] pnt1, float[] pnt2, int depth) {
	if (depth==0) {
	    gl.glShadeModel(GL.GL_SMOOTH);
	    gl.glBegin(GL.GL_TRIANGLES);
	    gl.glNormal3fv(pnt0, 0);
	    gl.glVertex3fv(pnt0, 0);
	    gl.glNormal3fv(pnt1, 0);
	    gl.glVertex3fv(pnt1, 0);
	    gl.glNormal3fv(pnt2, 0);
	    gl.glVertex3fv(pnt2, 0);
	    gl.glEnd();
	}
	else {
	    float[] pnt01 = split(pnt0, pnt1);
	    float[] pnt12 = split(pnt1, pnt2);
	    float[] pnt20 = split(pnt2, pnt0);
	    subdivide( pnt0, pnt01, pnt20, depth-1);
	    subdivide( pnt1, pnt12, pnt01, depth-1);
	    subdivide( pnt2, pnt20, pnt12, depth-1);
	    subdivide(pnt01, pnt12, pnt20, depth-1);
	}
    }
}
