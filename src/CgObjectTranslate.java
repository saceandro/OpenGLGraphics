import javax.media.opengl.*;
import com.sun.opengl.cg.*;

public abstract class CgObjectTranslate extends ObjectTranslate {
    private CGcontext context = null;
    private /* CGprofile */ int vertexProfile;
    protected String vpFileName = null;
    protected CGprogram vertexProgram = null;
    private /* CGprofile */ int fragmentProfile;
    protected String fpFileName = null;
    protected CGprogram fragmentProgram = null;
    
    protected CgObjectTranslate(String name) {
	super(name);
    }

    protected static void CheckCgError() {
	int err = CgGL.cgGetError();
	if (err != CgGL.CG_NO_ERROR) {
	    throw new RuntimeException("CG Error: " + CgGL.cgGetErrorString(err));
	}
    }

    public void init(GLAutoDrawable drawable) {
	baseInit(drawable);
	cgInit();
	objectInit();
	mouseInit(drawable);
	motionInit(drawable);
    }

    protected void cgInit() {
	context = CgGL.cgCreateContext();
	CheckCgError();
	if (!CgGL.cgIsContext(context)) {
	    throw new RuntimeException("CG Bad Context: " + context);
	}
	
	vertexProfile = CgGL.cgGLGetLatestProfile(CgGL.CG_GL_VERTEX);
	CheckCgError();
	//System.out.println("Video card supports vp: " + vertexProfile);

	if (vpFileName != null) {
	    vertexProgram = CgGL.cgCreateProgramFromFile(context, CgGL.CG_SOURCE, vpFileName, vertexProfile, null, null);
	    CheckCgError();
	    if (vertexProgram != null) {
		if (!CgGL.cgIsProgramCompiled(vertexProgram)) {
		    CgGL.cgCompileProgram(vertexProgram);
		}
		CgGL.cgGLLoadProgram(vertexProgram);
		CheckCgError();
	    }
	}

	fragmentProfile = CgGL.cgGLGetLatestProfile(CgGL.CG_GL_FRAGMENT);
	CheckCgError();
	//System.out.println("Video card supports fp: " + fragmentProfile);
	
	if (fpFileName != null) {
	    fragmentProgram = CgGL.cgCreateProgramFromFile(context, CgGL.CG_SOURCE, fpFileName, fragmentProfile, null, null);
	    CheckCgError();
	    if (fragmentProgram != null) {
		if (!CgGL.cgIsProgramCompiled(fragmentProgram)) {
		    CgGL.cgCompileProgram(fragmentProgram);
		}
		CgGL.cgGLLoadProgram(fragmentProgram);
		CheckCgError();
	    }
	}
    }

    public void display(GLAutoDrawable drawable) {
	setMatrix();
	cgStart();
	gl.glCallList(object);
	cgStop();
	resetMatrix();
    }

    protected void cgStart() {
	if (vertexProgram != null) {
	    CgGL.cgGLEnableProfile(vertexProfile);
	    CheckCgError();
	    CgGL.cgGLBindProgram(vertexProgram);
	    CheckCgError();
	    CgGL.cgGLSetStateMatrixParameter(CgGL.cgGetNamedParameter(vertexProgram, "modelViewProj"), CgGL.CG_GL_MODELVIEW_PROJECTION_MATRIX, CgGL.CG_GL_MATRIX_IDENTITY);
	    CheckCgError();
	    CgGL.cgGLSetStateMatrixParameter(CgGL.cgGetNamedParameter(vertexProgram, "modelView"), CgGL.CG_GL_MODELVIEW_MATRIX, CgGL.CG_GL_MATRIX_IDENTITY);
	    CheckCgError();
	    CgGL.cgGLSetStateMatrixParameter(CgGL.cgGetNamedParameter(vertexProgram, "modelViewNormal"), CgGL.CG_GL_MODELVIEW_MATRIX, CgGL.CG_GL_MATRIX_INVERSE_TRANSPOSE);
	    CheckCgError();
	}

	if (fragmentProgram != null) {
	    CgGL.cgGLEnableProfile(fragmentProfile);
	    CheckCgError();
	    CgGL.cgGLBindProgram(fragmentProgram);
	    CheckCgError();
	}
    }

    protected void cgStop() {
	if (vertexProgram != null) {
	    CgGL.cgGLDisableProfile(vertexProfile);
	    CheckCgError();
	}
	if (fragmentProgram != null) {
	    CgGL.cgGLDisableProfile(fragmentProfile);
	    CheckCgError();
	}
    }
}