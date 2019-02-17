import javax.media.opengl.*;
import java.nio.*;

public class ColorCubeBuffer extends ColorCubeLinear {
    public static void main(String[] args) {
	(new ColorCubeBuffer("ColorCubeBuffer")).showFrame();
    }
    
    protected ColorCubeBuffer(String name) {
	super(name);
    }
    
    protected void objectInit() {
	int[] buffers = new int[3];
	gl.glGenBuffers(buffers.length, buffers, 0);
	gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers[0]);
	Buffer buf = ByteBuffer.allocateDirect(vertices.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(vertices).position(0);
	gl.glBufferData(GL.GL_ARRAY_BUFFER, vertices.length*4, buf, GL.GL_STATIC_DRAW);
	gl.glVertexPointer(3, GL.GL_FLOAT, 0, 0L);

	gl.glEnableClientState(GL.GL_COLOR_ARRAY);
	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers[1]);

	buf = ByteBuffer.allocateDirect(colors.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(colors).position(0);
	gl.glBufferData(GL.GL_ARRAY_BUFFER, colors.length*4, buf, GL.GL_STATIC_DRAW);
	gl.glColorPointer(3, GL.GL_FLOAT, 0, 0L);
	
	gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, buffers[2]);
	buf = ByteBuffer.allocateDirect(indices.length*4).order(ByteOrder.nativeOrder()).asIntBuffer().put(indices).position(0);
	gl.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, indices.length*4, buf, GL.GL_STATIC_DRAW);
	object = gl.glGenLists(1);
	gl.glNewList(object, GL.GL_COMPILE);
	object();
	gl.glEndList();
    }
    
    protected void object() {
	gl.glDrawElements(GL.GL_QUADS, indices.length, GL.GL_UNSIGNED_INT, 0L);
    }
}