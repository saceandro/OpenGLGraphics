import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;

public abstract class ObjectSpin extends ObjectRotate {
    protected final float rotateRatio=2.0f;
    protected int startX, startY;
    protected double angle=0.0;
    protected double[] axis = {0.0, 0.0, 1.0, 1.0};
    protected double[] origin = new double[4];
    protected double[] modelMatrix = new double[16];
    protected double[] projMatrix = new double[16];
    protected int[] viewport = new int[4];

    protected ObjectSpin(String name) {
	super(name);
    }
    
    protected void mouseInit(GLAutoDrawable drawable) {
	drawable.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
		    prevX = startX = e.getX();
		    prevY = startY = e.getY();
		}
		public void mouseReleased(MouseEvent e) {
		    int x = e.getX();
		    int y = e.getY();
		    if (x==startX && y==startY)
			angle = 0.0;
		}
	    });
    }
    
    protected void motionInit(GLAutoDrawable drawable) {
	drawable.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseDragged(MouseEvent e) {
		    int x = e.getX();
		    int y = e.getY();
		    Dimension size = e.getComponent().getSize();
		    float deltaX = x - prevX;
		    float deltaY = y - prevY;
		    angle = Math.sqrt(deltaX*deltaX + deltaY*deltaY) * rotateRatio
			/ Math.min(size.width, size.height);
		    glu.gluUnProject(origin[0]+deltaY, origin[1]+deltaX, origin[2],
				     modelMatrix, 0, projMatrix, 0, viewport, 0, axis, 0);
		    prevX = x;
		    prevY = y;
		}
	    });
    }
    
    protected void positionInit() {
	gl.glTranslatef(0.0f, 0.0f, depth);
	gl.glRotatef(rotx, 1.0f, 0.0f, 0.0f);
	gl.glRotatef(roty, 0.0f, 1.0f, 0.0f);
	gl.glRotatef(rotz, 0.0f, 0.0f, 1.0f);
	gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, projMatrix, 0);
	gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
    }
    
    protected void setMatrix() {
	gl.glRotated(angle, axis[0], axis[1], axis[2]);
	gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, modelMatrix, 0);
	glu.gluProject(0.0, 0.0, 0.0,
		       modelMatrix, 0, projMatrix, 0, viewport, 0, origin, 0);
	gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
    }
    
    protected void resetMatrix() {}
}
