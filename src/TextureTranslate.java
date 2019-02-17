import java.io.*;
import javax.media.opengl.*;
import com.sun.opengl.util.texture.*;

public abstract class TextureTranslate extends ObjectTranslate {
    protected String imgFile = null;
    protected Texture texture = null;
    protected float[][] texCoords = { { 0.0f, 1.0f }, { 1.0f, 1.0f },
				      { 1.0f, 0.0f }, { 0.0f, 0.0f } };
    
    protected TextureTranslate(String title, String image) {
	super(title);
	imgFile = image;
    }
    
    public void init(GLAutoDrawable drawable) {
	baseInit(drawable);
	textureInit();
	objectInit();
	mouseInit(drawable);
	motionInit(drawable);
    }

    private void textureInit() {
	try {
	    texture = TextureIO.newTexture(new File(imgFile), true);
	    gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
	    
	    /*
	      TextureCoords coords = texture.getImageTexCoords();
	      texCoords[0][0] = texCoords[3][0] = coords.left();
	      texCoords[1][0] = texCoords[2][0] = coords.right();
	      texCoords[0][1] = texCoords[1][1] = coords.bottom();
	      texCoords[2][1] = texCoords[3][1] = coords.top();
	    */
	    
	}
	catch (IOException e) {
	    System.err.println("ERROR: loading texture " + e);
	    e.printStackTrace();
	}
    }
}