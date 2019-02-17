import javax.media.opengl.*;

public class AAAlphaLines extends Lines {
    public static void main(String[] args) {
	(new AAAlphaLines("AAAlphaLines")).showFrame();
    }

    protected AAAlphaLines(String name) {
	super(name);
    }

    public void init(GLAutoDrawable drawable) {
	super.init(drawable);
	gl.glEnable(GL.GL_LINE_SMOOTH);
	gl.glEnable(GL.GL_BLEND);
	gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
    }
}