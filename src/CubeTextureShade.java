import javax.media.opengl.*;

public class CubeTextureShade extends TextureShade {
    public static void main(String[] args) {
	if (args.length==0)
	    System.out.println("Useage: java CubeTextureShade <imgFile> ...");
	else
	    new CubeTextureShade("CubeTextureShade", args).showFrame();
    }

    protected CubeTextureShade(String title, String[] image) {
	super(title, image);
	rotx =  20.0f;
	roty = -30.0f;
    }
    
    protected void object() {
	float[] diffuse =  { 0.7f, 0.7f, 0.7f, 1.0f };
	float[] specular = { 0.9f, 0.9f, 0.9f, 1.0f };
	
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, diffuse, 0);
	gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, specular, 0);
	gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 100.0f);
	gl.glShadeModel(GL.GL_FLAT);
	
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
	
	for (int i=0; i<faces.length; i++) {
	    int t = i % textures.length;
	    textures[t].enable();
	    textures[t].bind();
	    gl.glBegin(GL.GL_QUADS);
	    gl.glNormal3fv(normals[i], 0);
	    for (int j=0; j<faces[i].length; j++) {
		gl.glTexCoord2fv(texCoords[j], 0);
		gl.glVertex3fv(vertices[faces[i][j]], 0);
	    }
	    gl.glEnd();
	    textures[t].disable();
	}
    }
}