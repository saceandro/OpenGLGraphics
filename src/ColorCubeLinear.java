import javax.media.opengl.*;

public class ColorCubeLinear extends ObjectTranslate {
    public static void main(String[] args) {
	(new ColorCubeLinear("ColorCubeLinear")).showFrame();
    }
    
    protected ColorCubeLinear(String name) {
	super(name);
    }
    
    float[] vertices = {  -1.0f, -1.0f, -1.0f ,
			     1.0f, -1.0f, -1.0f ,
			     1.0f,  1.0f, -1.0f ,
			    -1.0f,  1.0f, -1.0f ,
			    -1.0f, -1.0f,  1.0f ,
			     1.0f, -1.0f,  1.0f ,
			     1.0f,  1.0f,  1.0f ,
			    -1.0f,  1.0f,  1.0f  };
    float[] colors = {  0.0f, 0.0f, 0.0f ,
			  1.0f, 0.0f, 0.0f ,
			  1.0f, 1.0f, 0.0f ,
			  0.0f, 1.0f, 0.0f ,
			  0.0f, 0.0f, 1.0f ,
			  1.0f, 0.0f, 1.0f ,
			  1.0f, 1.0f, 1.0f ,
			  0.0f, 1.0f, 1.0f  };
    int[] indices = {  1, 2, 6, 5 ,  2, 3, 7, 6 ,  4, 5, 6, 7 ,
		       0, 4, 7, 3 ,  0, 1, 5, 4 ,  0, 3, 2, 1  };
    
    protected void object() {
	final int QUADS = 4;
	final int FACES = 6;
	gl.glBegin(GL.GL_QUADS);
	for (int i=0; i<FACES; i++) {
	    for (int j=0; j<QUADS; j++) {
		gl.glColor3fv(colors, indices[i*QUADS+j]*3);
		gl.glVertex3fv(vertices, indices[i*QUADS+j]*3);
	    }
	}
	gl.glEnd();
    }
}