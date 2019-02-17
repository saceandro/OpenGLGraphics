import javax.media.opengl.*;
import com.sun.opengl.cg.*;

public class CubeCgShade extends CgObjectShade {
    public static void main(String[] args) {
	(new CubeCgShade("CubeCgShade")).showFrame();
    }

    protected CubeCgShade(String name) {
	super(name);
	rotx = -17.0f;
	roty =  16.0f;
	//vpFileName = "GouraudVp.cg";
	vpFileName = "PhongVp.cg";
	fpFileName = "PhongFp.cg";
    }
    
    protected void object() {
	final float[] diffuse =  { 0.1f, 0.2f, 0.6f, 1.0f };
	final float[] specular = { 0.9f, 0.9f, 0.9f, 1.0f };
	final float shininess = 150.0f;

	if (vertexProgram == null) {
	    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, diffuse, 0);
	    gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, specular, 0);
	    gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 100.0f);
	    gl.glShadeModel(GL.GL_SMOOTH);
	    //gl.glShadeModel(GL.GL_FLAT);
	}
	else {
	    CGprogram program = (fragmentProgram == null) ? vertexProgram : fragmentProgram;
	    CgGL.cgGLSetParameter4fv(CgGL.cgGetNamedParameter(program, "ambient"), diffuse, 0);
	    CheckCgError();
	    CgGL.cgGLSetParameter4fv(CgGL.cgGetNamedParameter(program, "diffuse"), diffuse, 0);
	    CheckCgError();
	    CgGL.cgGLSetParameter4fv(CgGL.cgGetNamedParameter(program, "specular"), specular, 0);
	    CheckCgError();
	    CgGL.cgGLSetParameter1f(CgGL.cgGetNamedParameter(program, "shininess"), shininess);
	    CheckCgError();
	}

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
	float[][] normals = { { 1.0f, 0.0f, 0.0f },
			      { 0.0f, 1.0f, 0.0f },
			      { 0.0f, 0.0f, 1.0f },
			      {-1.0f, 0.0f, 0.0f },
			      { 0.0f,-1.0f, 0.0f },
			      { 0.0f, 0.0f,-1.0f } };
	
	gl.glBegin(GL.GL_QUADS);
	for (int i=0; i<faces.length; i++) {
	    gl.glNormal3fv(normals[i], 0);
	    for (int j=0; j<faces[i].length; j++)
		gl.glVertex3fv(vertices[faces[i][j]], 0);
	}
	gl.glEnd();
    }
}