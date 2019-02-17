import javax.media.opengl.GL;

public class CubeCgTranslate extends CgObjectTranslate {
    public static void main(String[] args) {
	(new CubeCgTranslate("CubeCgTranslate")).showFrame();
    }

    protected CubeCgTranslate(String name) {
	super(name);
	rotx =  20.0f;
	roty = -30.0f;
	//vpFileName = "cubePassthroughVp.cg";
	//vpFileName = "cubeGreenVp.cg";
	vpFileName = "cubeInverseVp.cg";
	//fpFileName = "cubePassthroughFp.cg";
	//fpFileName = "cubePinkFp.cg";
	//fpFileName = "cubeInverseFp.cg";
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
	int[][] faces = { { 1, 2, 6, 5 }, { 2, 3, 7, 6 }, { 4, 5, 6, 7 },
			  { 0, 4, 7, 3 }, { 0, 1, 5, 4 }, { 0, 3, 2, 1 } };
	float[][] colors ={ { 0.0f, 1.0f, 1.0f }, { 1.0f, 0.0f, 1.0f },
			    { 1.0f, 1.0f, 0.0f }, { 0.0f, 0.5f, 0.5f },
			    { 0.5f, 0.0f, 0.5f }, { 0.5f, 0.5f, 0.0f } };
	gl.glBegin(GL.GL_QUADS);
	for (int i=0; i<faces.length; i++) {
	    gl.glColor3fv(colors[i], 0);
	    for (int j=0; j<faces[i].length; j++)
		gl.glVertex3fv(vertices[faces[i][j]], 0);
	}
	gl.glEnd();
    }
}