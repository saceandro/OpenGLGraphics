import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;

public class CubePosition implements GLEventListener {
    public static void main(String[] args) {
	if (args.length==3)
	    (new CubePosition("CubePosition Demo", args)).showFrame();
	else
	    (new CubePosition("CubePosition Demo")).showFrame();
    }

    private Frame f;
    protected GL gl;
    protected GLU glu;
    protected double eye_x=4.0, eye_y=3.0, eye_z=7.0;
    
    protected CubePosition(String name) {
	GLCapabilities caps = new GLCapabilities();
	GLCanvas canvas = new GLCanvas(caps);
	canvas.setSize(500,500);
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
    
    protected CubePosition(String name, String[] args) {
	this(name);
	eye_x = Double.parseDouble(args[0]);
	eye_y = Double.parseDouble(args[1]);
	eye_z = Double.parseDouble(args[2]);
    }
    
    protected void showFrame() {
	f.setVisible(true);
    }

    public void init(GLAutoDrawable drawable) {
	gl = drawable.getGL();
	glu = new GLU();
	gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	gl.glEnable(GL.GL_DEPTH_TEST);
	gl.glEnable(GL.GL_CULL_FACE);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	final double fieldOfView=25.0, near=1.0, far=20.0;
	double aspect = (double) w / (double) h;
	
	gl.glMatrixMode(GL.GL_PROJECTION);
	gl.glLoadIdentity();
	glu.gluPerspective(fieldOfView, aspect, near, far);

	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glLoadIdentity();
	glu.gluLookAt(eye_x, eye_y, eye_z, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
    }

    public void display(GLAutoDrawable drawable) {
	gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	cube();
    }
    
    void cube() {
	float[][] vertices = { { -1.0f, -1.0f, -1.0f },
			       {  1.0f, -1.0f, -1.0f }, {  1.0f,  1.0f, -1.0f },
			       { -1.0f,  1.0f, -1.0f }, { -1.0f, -1.0f,  1.0f },
			       {  1.0f, -1.0f,  1.0f }, {  1.0f,  1.0f,  1.0f },
			       { -1.0f,  1.0f,  1.0f } };
	int[][] faces = { { 1, 2, 6, 5 }, { 2, 3, 7, 6 }, { 4, 5, 6, 7 }, 
			  { 0, 4, 7, 3 }, { 0, 1, 5, 4 }, { 0, 3, 2, 1 } };
	float[][] colors = { { 0.0f, 1.0f, 1.0f}, { 1.0f, 0.0f, 1.0f },
			     { 1.0f, 1.0f, 0.0f}, { 0.0f, 0.5f, 0.5f },
			     { 0.5f, 0.0f, 0.5f}, { 0.5f, 0.5f, 0.0f } };
	
	gl.glBegin(GL.GL_QUADS);
	for (int i=0; i<faces.length; i++) {
	    gl.glColor3fv(colors[i], 0);
	    for (int j=0; j<faces[i].length; j++)
		gl.glVertex3fv(vertices[faces[i][j]], 0);
	}
	gl.glEnd();
    }
    
    public void displayChanged(GLAutoDrawable drawable,
			       boolean modeChaned, boolean deviceChanged) {
    }
}
