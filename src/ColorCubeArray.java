import javax.media.opengl.*;
import java.nio.*;

public class ColorCubeArray extends ColorCubeLinear {
    public static void main(String[] args) {
	(new ColorCubeArray("ColorCubeArray")).showFrame();
    }
    
    protected ColorCubeArray(String name) {
	super(name);
    }

    protected void objectInit() {
	gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
	Buffer buf = ByteBuffer.allocateDirect(vertices.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(vertices).position(0);
	gl.glVertexPointer(3, GL.GL_FLOAT, 0, buf);
	gl.glEnableClientState(GL.GL_COLOR_ARRAY);
	buf = ByteBuffer.allocateDirect(colors.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(colors).position(0);
	gl.glColorPointer(3, GL.GL_FLOAT, 0, buf);
	object = gl.glGenLists(1);
	gl.glNewList(object, GL.GL_COMPILE);
	object();
	gl.glEndList();
    }
    
    protected void object() {
	gl.glDrawElements(GL.GL_QUADS, indices.length, GL.GL_UNSIGNED_INT, IntBuffer.wrap(indices));
    }
}
	    