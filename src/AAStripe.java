import javax.media.opengl.*;

public class AAStripe extends Stripe {
    public static void main(String[] args) {
	(new AAStripe("AAStripe")).showFrame();
    }
    
    protected AAStripe(String name) {
	super(name);
    }
    
    public void init(GLAutoDrawable drawable) {
	super.init(drawable);
	gl.glEnable(GL.GL_BLEND);
	gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
    }
    
    public void display(GLAutoDrawable drawable) {
	double alpha;
	final int width = 4;
	final double ratio = (double)width / (top-bottom);
	
	gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	gl.glBegin(GL.GL_POINTS);
	for (int y=top; y>bottom; y--) {
	    double dl = (double)left / (ratio*y);
	    int il = (int)Math.floor(dl-0.5);
	    for (int x=left; x<=right; x++) {
		double dr = (double)(x+1) / (y*ratio);
		int ir = (int)Math.floor(dr-0.5);
		if (il==ir) {
		    alpha = Math.abs(il%2);
		}
		else {
		    alpha = Math.abs(il%2)*(1 - (dl-il)) + Math.abs(ir%2)*(dr-ir);
		    for (int i=il+1; i<ir; i++) {
			alpha += Math.abs(i%2);
		    }
		    alpha /= (ir-il+1);
		}
		gl.glColor4d(1.0, 1.0, 1.0, alpha);
		gl.glVertex2i(x, y);
		dl = dr;
		il = ir;
	    }
	}
	gl.glEnd();
    }
}