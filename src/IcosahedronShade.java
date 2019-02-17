import javax.media.opengl.*;

public class IcosahedronShade extends ObjectShade {
    public static void main(String[] args) {
	(new IcosahedronShade("Icosahedron Shade")).showFrame();
    }

    protected IcosahedronShade(String name) {
	super(name);
    }

    protected void object() {
	float[] diffuse  = { 0.8f, 0.3f, 0.8f, 1.0f };
	float[] specular = { 0.9f, 0.9f, 0.9f, 1.0f };
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, diffuse, 0);
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, specular, 0);
	gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 100.0f);
	
	final float R = (float)Math.sqrt(2.0);
	final float X = (float)((Math.sqrt(5.0) + 1.0) / 2.0);
	final float Y = (float)(R / Math.sqrt(1.0 + X*X));
	final float Z = (float)(X * Y);
	float[][] vertices = { { 0.0f,    Y,    Z }, { 0.0f,   -Y,    Z }, { 0.0f,   -Y,   -Z },
			       { 0.0f,    Y,   -Z }, {    Z, 0.0f,    Y }, {    Z, 0.0f,   -Y },
			       {   -Z, 0.0f,   -Y }, {   -Z, 0.0f,    Y }, {    Y,    Z, 0.0f },
			       {   -Y,    Z, 0.0f }, {   -Y,   -Z, 0.0f }, {    Y,   -Z, 0.0f } };
	int[][] faces = { { 0, 1, 4 }, { 0,  4,  8 }, { 0,  8,  9 }, { 0,  9,  7 },
			  { 0, 7, 1 }, { 1, 10, 11 }, { 1, 11,  4 }, { 4, 11,  5 },
			  { 4, 5, 8 }, { 8,  5,  3 }, { 8,  3,  9 }, { 9,  3,  6 },
			  { 9, 6, 7 }, { 7,  6, 10 }, { 7, 10,  1 }, { 2, 10,  6 },
			  { 2, 6, 3 }, { 2,  3,  5 }, { 2,  5, 11 }, { 2, 11, 10 } };
	gl.glShadeModel(GL.GL_FLAT);

	gl.glBegin(GL.GL_TRIANGLES);
	for (int i=0; i<faces.length; i++) {
	    gl.glNormal3fv(normal(vertices[faces[i][0]], vertices[faces[i][1]], vertices[faces[i][2]]), 0);
	    for (int j=0; j<faces[i].length; j++)
		gl.glVertex3fv(vertices[faces[i][j]], 0);
	}
	gl.glEnd();
    }
    
    private float[] normal(float[] pnt0, float[] pnt1, float[] pnt2) {
	return crossProduct(subtract(pnt1, pnt0), subtract(pnt2, pnt0));
    }

    private float[] subtract(float[] vec0, float[] vec1) {
	float[] answer = new float[3];
	for (int i=0; i<3; i++)
	    answer[i] = vec0[i] - vec1[i];
	return answer;
    }
    
    private float[] crossProduct(float[] vec0, float[] vec1) {
	float[] answer = new float[3];
	answer[0] = vec0[1] * vec1[2] - vec0[2] * vec1[1];
	answer[1] = vec0[2] * vec1[0] - vec0[0] * vec1[2];
	answer[2] = vec0[0] * vec1[1] - vec0[1] * vec1[0];
	return answer;
    }
}