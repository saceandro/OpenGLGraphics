import javax.media.opengl.*;

public class AAColorLines extends Lines {
    public static void main(String[] args) {
	(new AAColorLines("AAColorLines")).showFrame();
    }

    protected AAColorLines(String name) {
	super(name);
    }

    public void display(GLAutoDrawable drawable) {
	gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	gl.glBegin(GL.GL_POINTS);
	for (int i=0; i<points.length; i++) {
	    if (points[i][0] >= points[i][1]) {
		int n = points[i][0];
		double d = ((double)points[i][1])/n;
		for (int x=0; x<=n; x++) {
		    int y = (int)(d*x);
		    float r = (float)(d*x - y);
		    gl.glColor3f(r,r,r);
		    gl.glVertex2i(x,y);
		    gl.glColor3f(1.0f-r,1.0f-r,1.0f-r);
		    gl.glVertex2i(x,y+1);
		}
	    }
	    else {
		int n = points[i][1];
		double d = ((double)points[i][0])/n;
		for (int y=0; y<=n; y++) {
		    int x = (int)(d*y);
		    float r = (float)(d*y - x);
		    gl.glColor3f(r,r,r);
		    gl.glVertex2i(x,y);
		    gl.glColor3f(1.0f-r,1.0f-r,1.0f-r);
		    gl.glVertex2i(x+1,y);
		}
	    }
	}
	gl.glEnd();
    }
}