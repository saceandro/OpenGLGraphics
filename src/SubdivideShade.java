import javax.media.opengl.*;

public class SubdivideShade extends ObjectShade {
    public static void main(String[] args) {
	if (args.length==1)
	    depth = Integer.parseInt(args[0]);
	(new SubdivideShade("Subdivide Shade")).showFrame();
    }
    
    static int depth = 0;
    static final float R = (float)Math.sqrt(2.0);
    
    protected SubdivideShade(String name) {
	super(name);
    }

    protected void object() {
	float[] diffuse  = { 0.8f, 0.3f, 0.8f, 1.0f };
	float[] specular = { 0.9f, 0.9f, 0.9f, 1.0f };
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, diffuse, 0);
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, specular, 0);
	gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 100.0f);
	
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
	for (int i=0; i<faces.length; i++) {
	    subdivide(vertices[faces[i][0]], vertices[faces[i][1]], vertices[faces[i][2]], depth);
	}
    }

    protected void subdivide(float[] pnt0, float[] pnt1, float[] pnt2, int depth) {
	if (depth==0) {
	    gl.glShadeModel(GL.GL_FLAT);
	    gl.glBegin(GL.GL_TRIANGLES);
	    gl.glNormal3fv(normal(pnt0, pnt1, pnt2),0);
	    gl.glVertex3fv(pnt0, 0);
	    gl.glVertex3fv(pnt1, 0);
	    gl.glVertex3fv(pnt2, 0);
	    gl.glEnd();
	}
	else {
	    float[] pnt01 = split(pnt0, pnt1);
	    float[] pnt12 = split(pnt1, pnt2);
	    float[] pnt20 = split(pnt2, pnt0);
	    subdivide( pnt0, pnt01, pnt20, depth-1);
	    subdivide( pnt1, pnt12, pnt01, depth-1);
	    subdivide( pnt2, pnt20, pnt12, depth-1);
	    subdivide(pnt01, pnt12, pnt20, depth-1);
	}
    }

    private float[] normal(float[] pnt0, float[] pnt1, float[] pnt2) {
	float[] answer = new float[3];
	for (int i=0; i<3; i++)
	    answer[i] = pnt0[i] + pnt1[i] + pnt2[i];
	return answer;
    }
    
    protected float[] split(float[] pnt0, float[] pnt1) {
	float[] ans = new float[3];
	for (int i=0; i<3; i++)
	    ans[i] = pnt0[i] + pnt1[i];
	float ratio = R / ((float)Math.sqrt(ans[0]*ans[0] + ans[1]*ans[1] + ans[2]*ans[2]));
	for (int i=0; i<3; i++)
	    ans[i] *= ratio;
	return ans;
    }
}