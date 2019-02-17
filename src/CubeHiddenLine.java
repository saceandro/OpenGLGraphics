import javax.media.opengl.*;

public class CubeHiddenLine extends ObjectRotate {
    private static float[][] vertices = { { -1.0f, -1.0f, -1.0f },
					  {  1.0f, -1.0f, -1.0f },
					  {  1.0f,  1.0f, -1.0f },
					  { -1.0f,  1.0f, -1.0f },
					  { -1.0f, -1.0f,  1.0f },
					  {  1.0f, -1.0f,  1.0f },
					  {  1.0f,  1.0f,  1.0f },
					  { -1.0f,  1.0f,  1.0f } };
    private static int[][] faces = { { 1, 2, 6, 5 }, { 2, 3, 7, 6 }, { 4, 5, 6, 7 },
				     { 0, 4, 7, 3 }, { 0, 1, 5, 4 }, { 0, 3, 2, 1 } };	
    
    public static void main(String[] args) {
	(new CubeHiddenLine("Cube HiddenLine")).showFrame();
    }
    
    protected CubeHiddenLine(String name) {
	super(name);
    }

    public void init(GLAutoDrawable drawable) {
	super.init(drawable);
	gl.glEnable(GL.GL_POLYGON_OFFSET_FILL);
	gl.glPolygonOffset(1.0f, 1.0f);
    }
    
    protected void object() {
	float[] background = { 0.0f, 0.0f, 0.0f };
	float[] foreground = { 1.0f, 1.0f, 0.0f };
	gl.glColor3fv(foreground, 0);
	cubeWire();
	gl.glColor3fv(background, 0); 
	cubePolygon();
    }

    private void cubeWire() {
	for (int i=0; i<faces.length; i++) {
	    gl.glBegin(GL.GL_LINE_LOOP);
	    for (int j=0; j<faces[i].length; j++)
		gl.glVertex3fv(vertices[faces[i][j]], 0);
	    gl.glEnd();
	}
    }
    
    private void cubePolygon() {
	for (int i=0; i<faces.length; i++) {
	    gl.glBegin(GL.GL_QUADS);
	    for (int j=0; j<faces[i].length; j++)
		gl.glVertex3fv(vertices[faces[i][j]], 0);
	    gl.glEnd();
	}
	for (int i=0; i<faces.length; i++) {
	    gl.glBegin(GL.GL_LINE_LOOP);
	    for (int j=0; j<faces[i].length; j++)
		gl.glVertex3fv(vertices[faces[i][j]], 0);
	    gl.glEnd();
	}
    }
}