import javax.media.opengl.*;
import com.sun.opengl.cg.*;

public abstract class CgObjectShade extends CgObjectTranslate {
    protected float[] lightPosition = { 5.0f, 5.0f, 0.0f, 1.0f };
    //protected float[] lightPosition = { 5.0f, 5.0f, 0.0f, 0.0f };
    protected float[] lightAmbient = { 0.2f, 0.2f, 0.2f, 1.0f };
    protected float[] lightDiffuse = { 1.0f, 1.0f, 1.0f, 1.0f };
    protected float[] lightSpecular = { 0.9f, 0.9f, 0.9f, 1.0f };

    protected CgObjectShade(String name) {
	super(name);
    }

    public void init(GLAutoDrawable drawable) {
	baseInit(drawable);
	cgInit();
	lightInit();
	objectInit();
	mouseInit(drawable);
	motionInit(drawable);
    }

    protected void lightInit() {
	if (vertexProgram == null) {
	    gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, lightAmbient, 0);
	    gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, lightDiffuse, 0);
	    gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, lightSpecular, 0);
	    gl.glEnable(GL.GL_LIGHTING);
	    gl.glEnable(GL.GL_LIGHT0);
	    gl.glEnable(GL.GL_NORMALIZE);
	}
	else {
	    CGprogram program = (fragmentProgram == null) ? vertexProgram : fragmentProgram;
	    CgGL.cgGLSetParameter4fv(CgGL.cgGetNamedParameter(program, "lightAmbient"), lightAmbient, 0);
	    CheckCgError();
	    CgGL.cgGLSetParameter4fv(CgGL.cgGetNamedParameter(program, "lightDiffuse"), lightDiffuse, 0);
	    CheckCgError();
	    CgGL.cgGLSetParameter4fv(CgGL.cgGetNamedParameter(program, "lightSpecular"), lightSpecular, 0);
	    CheckCgError();
	}
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	cameraInit(w,h);
	if (vertexProgram == null) {
	    gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, lightPosition, 0);
	}
	else {
	    CGprogram program = (fragmentProgram == null) ? vertexProgram : fragmentProgram;
	    CgGL.cgGLSetParameter4fv(CgGL.cgGetNamedParameter(program, "lightPosition"), lightPosition, 0);
	    CheckCgError();
	}	    
	positionInit();
    }
}