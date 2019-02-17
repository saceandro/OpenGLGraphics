import java.io.*;
import javax.media.opengl.*;

import com.sun.opengl.cg.CgGL;
import com.sun.opengl.util.texture.*;

public abstract class CgTextureTranslate extends CgObjectTranslate {
    protected String imgFile = null;
    protected Texture texture = null;
    protected float[][] texCoords = { { 0.0f, 1.0f }, { 1.0f, 1.0f }, { 1.0f, 0.0f }, { 0.0f, 0.0f } };
    
    protected CgTextureTranslate(String title, String image) {
	super(title);
	imgFile = image;
    }

    public void init(GLAutoDrawable drawable) {
	baseInit(drawable);
	textureInit();
	cgInit();
	objectInit();
	mouseInit(drawable);
	motionInit(drawable);
    }
    
    private void textureInit() {
	try {
	    texture = TextureIO.newTexture(new File(imgFile), true);
	    if (fragmentProgram == null) {
		gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
	    }
	    else {
		CgGL.cgGLSetTextureParameter(CgGL.cgGetNamedParameter(fragmentProgram, "texture"), texture.getTextureObject());
		CheckCgError();
	    }
	}
	catch (IOException e) {
	    System.err.println("ERROR: loading texture " + e);
	    e.printStackTrace();
	}
    }
}