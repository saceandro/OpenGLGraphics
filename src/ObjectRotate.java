import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*;

public abstract class ObjectRotate implements GLEventListener {
    private Frame f;
    protected GL gl;
    protected GLU glu;
    protected double fieldOfView=25.0, near=1.0, far=20.0;
    protected float depth=-10.0f;
    protected float rotx=0.0f, roty=0.0f, rotz=0.0f;
    protected int prevX, prevY;
    protected int object;

    protected abstract void object();
    
    protected ObjectRotate(String name) {
	GLCapabilities caps = new GLCapabilities();
	GLCanvas canvas = new GLCanvas(caps);
	canvas.setSize(500, 500);
	canvas.addGLEventListener(this);
	
	final Animator animator = new Animator(canvas);
	
	f = new Frame(name);
	f.add(canvas);
	f.pack();
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    animator.stop();
		    System.exit(0);
		}
	    });
	animator.start();
    }
    
    protected void showFrame() {
	f.setVisible(true);
    }
    
    public void init(GLAutoDrawable drawable) {
	baseInit(drawable);
	objectInit();
	mouseInit(drawable);
	motionInit(drawable);
    }

    protected void baseInit(GLAutoDrawable drawable) {
	gl = drawable.getGL();
	glu = new GLU();
	gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	gl.glEnable(GL.GL_DEPTH_TEST);
	gl.glEnable(GL.GL_CULL_FACE);
    }
    
    protected void objectInit() {
	object = gl.glGenLists(1);
	gl.glNewList(object, GL.GL_COMPILE);
	object();
	gl.glEndList();
    }
    
    protected void mouseInit(GLAutoDrawable drawable) {
	drawable.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
		    prevX = e.getX();
		    prevY = e.getY();
		}
	    });
    }

    protected void motionInit(GLAutoDrawable drawable) {
	drawable.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseDragged(MouseEvent e) {
		    int x = e.getX();
		    int y = e.getY();
		    Dimension size = e.getComponent().getSize();
		    float thetaY = 360.0f*((float)(x-prevX)/size.width);
		    float thetaX = 360.0f*((float)(y-prevY)/size.height);
		    rotx += thetaX;
		    roty += thetaY;
		    prevX = x;
		    prevY = y;
		}
	    });
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	cameraInit(w,h);
	positionInit();
    }
    
    protected void cameraInit(int w, int h) {
	double aspect = (double) w / (double) h;
	gl.glMatrixMode(GL.GL_PROJECTION);
	gl.glLoadIdentity();
	glu.gluPerspective(fieldOfView, aspect, near, far);
	
	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glLoadIdentity();
    }
    
    protected void positionInit() {
	gl.glTranslatef(0.0f, 0.0f, depth);
    }
    
    public void display(GLAutoDrawable drawable) {
	setMatrix();
	gl.glCallList(object);
	resetMatrix();
    }
    
    protected void setMatrix() {
	gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	gl.glPushMatrix();
	gl.glRotatef(rotx, 1.0f, 0.0f, 0.0f);
	gl.glRotatef(roty, 0.0f, 1.0f, 0.0f);
	gl.glRotatef(rotz, 0.0f, 0.0f, 1.0f);
    }
    
    protected void resetMatrix() {
	gl.glPopMatrix();
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
}
	