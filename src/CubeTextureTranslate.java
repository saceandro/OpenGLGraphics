import javax.media.opengl.*;

public class CubeTextureTranslate extends TextureTranslate {
    public static void main(String[] args) {
	if (args.length==0)
	    System.out.println("Usage: java CubeTextureTranslate <imageFile>");
	else
	    new CubeTextureTranslate("CubeTextureTranslate", args[0]).showFrame();
    }
    
    protected CubeTextureTranslate(String title, String image) {
	super(title, image);
	rotx =  20.0f;
	roty = -30.0f;
	
	texCoords[0][0] = 0.25f; texCoords[0][1] = 0.75f;
	texCoords[1][0] = 0.75f; texCoords[1][1] = 0.75f;
	texCoords[2][0] = 0.75f; texCoords[2][1] = 0.25f;
	texCoords[3][0] = 0.25f; texCoords[3][1] = 0.25f;
	
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
	
	texture.enable();
	texture.bind();
	gl.glBegin(GL.GL_QUADS);
	for (int i=0; i<faces.length; i++) {
	    for (int j=0; j<faces[i].length; j++) {
		gl.glTexCoord2fv(texCoords[j], 0);
		gl.glVertex3fv(vertices[faces[i][j]], 0);
	    }
	}
	gl.glEnd();
	texture.disable();
    }
}