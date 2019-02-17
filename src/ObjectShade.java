import javax.media.opengl.*;

public abstract class ObjectShade extends ObjectTranslate {
    protected float[] lightPosition = { 5.0f, 5.0f, 0.0f, 1.0f };
    //protected float[] lightPosition = { 5.0f, 5.0f, 0.0f, 0.0f };
    protected float[] lightAmbient = { 0.2f, 0.2f, 0.2f, 1.0f };
    protected float[] lightDiffuse = { 1.0f, 1.0f, 1.0f, 1.0f };
    protected float[] lightSpecular = { 0.9f, 0.9f, 0.9f, 1.0f };

    protected ObjectShade(String name) {
	super(name);
    }

    public void init(GLAutoDrawable drawable) {
	baseInit(drawable);
	lightInit();
	objectInit();
	mouseInit(drawable);
	motionInit(drawable);
    }

    protected void lightInit() {
	gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, lightAmbient, 0);
	gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, lightDiffuse, 0);
	gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, lightSpecular, 0);
	gl.glEnable(GL.GL_LIGHTING);
	gl.glEnable(GL.GL_LIGHT0);
	gl.glEnable(GL.GL_NORMALIZE);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	cameraInit(w,h);
	gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, lightPosition, 0);
	positionInit();
    }
}