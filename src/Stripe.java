import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;

public class Stripe implements GLEventListener {
    public static void main(String[] args) {
	(new Stripe("Stripe")).showFrame();
    }

    protected Frame f;
    protected GL gl;
    protected static final int left = -300, right= 300, bottom = 0, top = 600;
    protected Stripe(String name) {
	GLCapabilities caps = new GLCapabilities();
	GLCanvas canvas = new GLCanvas(caps);
	canvas.setSize(right - left, top - bottom);
	canvas.addGLEventListener(this);
	
	f = new Frame(name);
	f.add(canvas);
	f.pack();
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
    }
    
    protected void showFrame() {
	f.setVisible(true);
    }

    public void init(GLAutoDrawable drawable) {
	gl = drawable.getGL();
	GLU glu = new GLU();
	gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	gl.glMatrixMode(GL.GL_PROJECTION);
	gl.glLoadIdentity();
	glu.gluOrtho2D(left, right, bottom,top);
	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glLoadIdentity();
	gl.glViewport(0, 0, right-left, top-bottom);
	/*gl.glEnable(GL.GL_BLEND);
	  gl.glBlendFunc(GL.GL_SRC_ALPHA,GL.GL_ONE_MINUS_SRC_ALPHA);*/
    }

    public void display(GLAutoDrawable drawable) {
	final int width=4;
	final double ratio = (double)width / (top-bottom);
	gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	gl.glBegin(GL.GL_POINTS);
	for (int y=top; y>bottom; y--) {
	    for(int x=left; x<=right; x++) {
		double rgb = Math.abs(Math.floor(x/(ratio*y)) % 2);
		gl.glColor3d(rgb, rgb, rgb);
		gl.glVertex2i(x, y);
	    }
	}
	gl.glEnd();
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
}