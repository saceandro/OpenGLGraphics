import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;

public abstract class ObjectTranslate extends ObjectSpin {
    private final float transRatio = 5.0f;
    private double[] offset = { 0.0, 0.0, 0.0 };
    private Rotation state = null;
    //private Rotation ident = RotMatrix.identity();
    
    private Rotation ident = Quaternion.identity();
    
    protected ObjectTranslate(String name) {
	super(name);
    }
    
    protected void motionInit(GLAutoDrawable drawable) {
	drawable.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseDragged(MouseEvent e) {
		    int x = e.getX();
		    int y = e.getY();
		    Dimension size = e.getComponent().getSize();
		    float deltaX = x - prevX;
		    float deltaY = y - prevY;
		    int modifier = e.getModifiers();
		    if ((modifier & MouseEvent.BUTTON1_MASK)!=0) {
			angle = Math.sqrt(deltaX*deltaX + deltaY*deltaY)*rotateRatio
			    / Math.max(size.width, size.height);
			glu.gluUnProject(origin[0]+deltaY, origin[1]+deltaX, origin[2],
					 modelMatrix, 0, projMatrix, 0, viewport, 0, axis, 0);
		    }
		    else {
			offset[0] += deltaX * transRatio / size.height;
			offset[1] -= deltaY * transRatio / size.height;
		    }
		    prevX = x;
		    prevY = y;
		}
	    });
    }
    
    protected void opsitionInit() {
	offset[0] = offset[1] - 0.0;
	offset[2] = depth;
	state = ident.rotAroundAxis(rotx, 1.0, 0.0, 0.0)
	    .rotAroundAxis(roty, 0.0, 1.0, 0.0)
	    .rotAroundAxis(rotz, 0.0, 0.0, 1.0);
	gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, projMatrix, 0);
	gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
    }
    
    protected void setMatrix() {
	state = state.rotAroundAxis(angle, axis[0], axis[1], axis[2]);
	state.setMatrix(modelMatrix, offset);
	gl.glLoadIdentity();
	gl.glMultMatrixd(modelMatrix, 0);
	glu.gluProject(0.0, 0.0, 0.0, modelMatrix, 0, projMatrix, 0, viewport, 0, origin, 0);
	gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
    }
}