import javax.media.opengl.*;

public class ColorCube extends ObjectTranslate {
    public static void main(String[] args) {
	(new ColorCube("ColorCube")).showFrame();
    }
    
    protected ColorCube(String name) {
	super(name);
    }
    
    protected void object() {
	float[][] vertices = { { -1.0f, -1.0f, -1.0f },
			       {  1.0f, -1.0f, -1.0f },
			       {  1.0f,  1.0f, -1.0f },
			       { -1.0f,  1.0f, -1.0f },
			       { -1.0f, -1.0f,  1.0f },
			       {  1.0f, -1.0f,  1.0f },
			       {  1.0f,  1.0f,  1.0f },
			       { -1.0f,  1.0f,  1.0f } };
	float[][] colors = { { 0.0f, 0.0f, 0.0f },
			     { 1.0f, 0.0f, 0.0f },
			     { 1.0f, 1.0f, 0.0f },
			     { 0.0f, 1.0f, 0.0f },
			     { 0.0f, 0.0f, 1.0f },
			     { 1.0f, 0.0f, 1.0f },
			     { 1.0f, 1.0f, 1.0f },
			     { 0.0f, 1.0f, 1.0f } };
	int[][] faces = { { 1, 2, 6, 5 }, { 2, 3, 7, 6 }, { 4, 5, 6, 7 },
			  { 0, 4, 7, 3 }, { 0, 1, 5, 4 }, { 0, 3, 2, 1 } };
	gl.glBegin(GL.GL_QUADS);
	for (int i=0; i<faces.length; i++) {
	    for (int j=0; j<faces[i].length; j++) {
		gl.glColor3fv(colors[faces[i][j]], 0);
		gl.glVertex3fv(vertices[faces[i][j]], 0);
	    }
	}
	gl.glEnd();
    }
}