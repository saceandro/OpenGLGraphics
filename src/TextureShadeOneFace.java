import java.io.*;
import javax.media.opengl.*;
import com.sun.opengl.util.texture.*;

public abstract class TextureShadeOneFace extends ObjectShade {
    protected String imgFile = null;
    protected Texture texture = null;
    protected float[][][] texCoords = { { {      0.0f, 0.5f }, { 1.0f/3.0f, 0.5f }, { 1.0f/3.0f, 0.0f }, {      0.0f, 0.0f } },
					{ { 1.0f/3.0f, 0.5f }, { 2.0f/3.0f, 0.5f }, { 2.0f/3.0f, 0.0f }, { 1.0f/3.0f, 0.0f } },
					{ { 2.0f/3.0f, 0.5f }, {      1.0f, 0.5f }, {      1.0f, 0.0f }, { 2.0f/3.0f, 0.0f } },
					{ {      0.0f, 1.0f }, { 1.0f/3.0f, 1.0f }, { 1.0f/3.0f, 0.5f }, {      0.0f, 0.5f } },
					{ { 1.0f/3.0f, 1.0f }, { 2.0f/3.0f, 1.0f }, { 2.0f/3.0f, 0.5f }, { 1.0f/3.0f, 0.5f } },
					{ { 2.0f/3.0f, 1.0f }, {      1.0f, 1.0f }, {      1.0f, 0.5f }, { 2.0f/3.0f, 0.5f } } };
    
    protected TextureShadeOneFace(String title, String image) {
	super(title);
	imgFile = image;
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
	    texture = TextureIO.newTexture(new File(imgFile), true);
	    gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_MODULATE);
	}
	catch (IOException e) {
	    System.err.println("ERROR: loading texture " + e);
	    e.printStackTrace();
	}
    }
}