import java.awt.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;

public abstract class LinesBase implements GLEventListener{
    protected Frame f;
    protected GL gl;
    protected static final int width=600;
    protected static final int height=600;
    protected static final int[][] points=
    {{10,599},{30,599},{75,599},{150,599},{300,599},{599,599},
     {599,300},{599,150},{599,75},{599,30},{599,10}};
    
    protected void showFrame(){
	f.setVisible(true);
    }

    public void init(GLAutoDrawable drawable){
	GLU glu = new GLU();
	gl = drawable.getGL();
	gl.glClearColor(1.0f,1.0f,1.0f,1.0f);
	gl.glColor4f(0.0f,0.0f,0.0f,1.0f);

	gl.glMatrixMode(GL.GL_PROJECTION);
	gl.glLoadIdentity();
	glu.gluOrtho2D(-0.5,width-0.5,-0.5,height-0.5);
	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glLoadIdentity();
	gl.glViewport(0,0,width,height);
    }

    public void display(GLAutoDrawable drawable){
	gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	gl.glBegin(GL.GL_LINES);
	for (int i=0; i<points.length; i++){
	    gl.glVertex2i(0,0);
	    gl.glVertex2i(points[i][0],points[i][1]);
	}
	gl.glEnd();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged){
    }
}