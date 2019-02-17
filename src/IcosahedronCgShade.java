import javax.media.opengl.*;
import com.sun.opengl.cg.*;

public class IcosahedronCgShade extends CgObjectShade {
    public static void main(String[] args) {
	(new IcosahedronCgShade("IcosahedronCgShade")).showFrame();
    }

    protected IcosahedronCgShade(String name) {
	super(name);
	rotx =  20.0f;
	roty = -30.0f;
	//vpFileName = "GouraudVp.cg";
	vpFileName = "PhongVp.cg";
	fpFileName = "PhongFp.cg";
    }

    protected void object() {
	float[] diffuse  = { 0.8f, 0.3f, 0.8f, 1.0f };
	float[] specular = { 0.9f, 0.9f, 0.9f, 1.0f };
	final float shininess = 100.0f;

	if (vertexProgram == null) {
	    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, diffuse, 0);
	    gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, specular, 0);
	    gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, shininess);
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

	final float R = (float)Math.sqrt(2.0);
	final float X = (float)((Math.sqrt(5.0) + 1.0) / 2.0);
	final float Y = (float)(R / Math.sqrt(1.0 + X*X));
	final float Z = (float)(X * Y);
	final float NY = (float) (1.0 / Math.sqrt(1.0 + X*X));
	final float NZ = X * NY;
	float[][] vertices = { { 0.0f,    Y,    Z }, { 0.0f,   -Y,    Z }, { 0.0f,   -Y,   -Z },
			       { 0.0f,    Y,   -Z }, {    Z, 0.0f,    Y }, {    Z, 0.0f,   -Y },
			       {   -Z, 0.0f,   -Y }, {   -Z, 0.0f,    Y }, {    Y,    Z, 0.0f },
			       {   -Y,    Z, 0.0f }, {   -Y,   -Z, 0.0f }, {    Y,   -Z, 0.0f } };
	float[][] normals = { { 0.0f,    NY,    NZ }, { 0.0f,   -NY,    NZ }, { 0.0f,   -NY,   -NZ },
			       { 0.0f,    NY,   -NZ }, {    NZ, 0.0f,    NY }, {    NZ, 0.0f,   -NY },
			       {   -NZ, 0.0f,   -NY }, {   -NZ, 0.0f,    NY }, {    NY,    NZ, 0.0f },
			       {   -NY,    NZ, 0.0f }, {   -NY,   -NZ, 0.0f }, {    NY,   -NZ, 0.0f } };
	int[][] faces = { { 0, 1, 4 }, { 0,  4,  8 }, { 0,  8,  9 }, { 0,  9,  7 },
			  { 0, 7, 1 }, { 1, 10, 11 }, { 1, 11,  4 }, { 4, 11,  5 },
			  { 4, 5, 8 }, { 8,  5,  3 }, { 8,  3,  9 }, { 9,  3,  6 },
			  { 9, 6, 7 }, { 7,  6, 10 }, { 7, 10,  1 }, { 2, 10,  6 },
			  { 2, 6, 3 }, { 2,  3,  5 }, { 2,  5, 11 }, { 2, 11, 10 } };

	gl.glBegin(GL.GL_TRIANGLES);
	for (int i=0; i<faces.length; i++) {
	    for (int j=0; j<faces[i].length; j++) {
		gl.glNormal3fv(normals[faces[i][j]],0);
		gl.glVertex3fv(vertices[faces[i][j]], 0);
	    }
	}
	gl.glEnd();
    }
}
