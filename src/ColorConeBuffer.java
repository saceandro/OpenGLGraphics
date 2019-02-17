import javax.media.opengl.*;
import java.nio.*;

public class ColorConeBuffer extends ObjectTranslate {
    public static void main(String[] args) {
	numberOfPoints = Integer.parseInt(args[0]);
	new ColorConeBuffer("ColorConeBuffer").showFrame();
    }
    
    static int numberOfPoints = 0;

    private static final float[] vertices() {
	float[] vertices = new float[3*(numberOfPoints+2)];
	vertices[0] = 0.0f; vertices[1] =  1.0f; vertices[2] = 0.0f;
	vertices[3] = 0.0f; vertices[4] = -1.0f; vertices[5] = 0.0f;
	float theta = 0;
	for (int i=2; i<=numberOfPoints+1; i++) {
	    theta = (float)(2.0*Math.PI*i / numberOfPoints);
	    vertices[3*i]   = (float)Math.sin(theta);
	    vertices[3*i+1] = 1.0f;
	    vertices[3*i+2] = (float)Math.cos(theta);
	}
	return vertices;
    }
    
    private static final float[] colors() {
	float[] colors = new float[3*(numberOfPoints+2)];
	colors[0] = 0.0f; colors[1] = 0.0f; colors[2] = 1.0f;
	colors[3] = 0.0f; colors[4] = 0.0f; colors[5] = 0.0f;
	for (int i=2; i<=numberOfPoints+1; i++) {
	    colors[3*i] = (float)i / (float)numberOfPoints;
	    colors[3*i+1] = 1.0f;
	    colors[3*i+2] = 1.0f;
	}
	return colors;
    }
    
    private static final int[] indices() {
	int[] indices = new int[6*(numberOfPoints-1)];
	for (int i=0; i<=numberOfPoints-1; i++) {
	    indices[3*i]   = i+1;
	    indices[3*i+1] = 0;
	    indices[3*i+2] = i+2;
	}
	for (int i=numberOfPoints; i<2*numberOfPoints-1; i++) {
	    indices[3*i]   = i+1-numberOfPoints;
	    indices[3*i+1] = 1;
	    indices[3*i+2] = i+2-numberOfPoints;
	}
	return indices;
    }

    protected ColorConeBuffer(String name) {
	super(name);
    }

    protected void objectInit() {
	int[] buffers = new int[3];
	gl.glGenBuffers(buffers.length, buffers, 0);

	gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers[0]);
	Buffer buf = ByteBuffer.allocateDirect(vertices().length*3).order(ByteOrder.nativeOrder()).asFloatBuffer().put(vertices()).position(0);
	gl.glBufferData(GL.GL_ARRAY_BUFFER, vertices().length*3, buf, GL.GL_STATIC_DRAW);
	gl.glVertexPointer(3, GL.GL_FLOAT, 0, 0L);

	gl.glEnableClientState(GL.GL_COLOR_ARRAY);
	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers[1]);
	buf = ByteBuffer.allocateDirect(colors().length*3).order(ByteOrder.nativeOrder()).asFloatBuffer().put(colors()).position(0);
	gl.glBufferData(GL.GL_ARRAY_BUFFER, colors().length*3, buf, GL.GL_STATIC_DRAW);
	gl.glColorPointer(3, GL.GL_FLOAT, 0, 0L);

	gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, buffers[2]);
	buf = ByteBuffer.allocateDirect(indices().length*3).order(ByteOrder.nativeOrder()).asIntBuffer().put(indices()).position(0);
	gl.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, indices().length*3, buf, GL.GL_STATIC_DRAW);
	object = gl.glGenLists(1);
	gl.glNewList(object, GL.GL_COMPILE);
	object();
	gl.glEndList();
    }

    protected void object() {
	gl.glDrawElements(GL.GL_TRIANGLES, indices().length, GL.GL_UNSIGNED_INT, 0L);
    }
}
