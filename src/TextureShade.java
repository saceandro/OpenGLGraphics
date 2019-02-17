import java.io.*;
import javax.media.opengl.*;
import com.sun.opengl.util.texture.*;

public abstract class TextureShade extends ObjectShade {
    protected String[] imgFiles = null;
    protected Texture[] textures = null;
    protected float[][] texCoords = { { 0.0f, 1.0f }, { 1.0f, 1.0f },
				      { 1.0f, 0.0f }, { 0.0f, 0.0f } };
    
    protected TextureShade(String title, String[] images) {
	super(title);
	imgFiles = images;
    }
    
    public void init(GLAutoDrawable drawable) {
	baseInit(drawable);
	lightInit();
	textureInit();
	objectInit();
	mouseInit(drawable);
	motionInit(drawable);
    }

    private void textureInit() {
	try {
	    textures = new Texture[imgFiles.length];
	    for (int i=0; i<imgFiles.length; i++) {
		textures[i] = TextureIO.newTexture(new File(imgFiles[i]), true);
	    }
	    gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_MODULATE);
	}
	catch (IOException e) {
	    System.err.println("ERROR: loading texture " + e);
	    e.printStackTrace();
	}
    }
}