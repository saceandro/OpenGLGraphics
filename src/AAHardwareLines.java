import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;

public class AAHardwareLines extends LinesBase {
    public static void main(String[] args) {
	(new AAHardwareLines("AAHardwareLines")).showFrame();
    }
    
    private AAHardwareLines(String name) {
	GLCapabilities caps = new GLCapabilities();
	GLCapabilitiesChooser chooser = new MultisampleChooser();
	caps.setSampleBuffers(true);
	caps.setNumSamples(16);
	GLCanvas canvas = new GLCanvas(caps, chooser, null, null);
	canvas.setSize(width, height);
	canvas.addGLEventListener(this);
	
	f = new Frame(name);
	f.add(canvas);
	f.pack();
	f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });
    }
    
    static class MultisampleChooser extends DefaultGLCapabilitiesChooser {
	public int chooseCapabilities(GLCapabilities desired, GLCapabilities[] available, int windowSystemRecommendedChoice) {
	    boolean anyHaveSampleBuffers = false;
	    for (int i=0; i<available.length; i++) {
		GLCapabilities caps = available[i];
		if (caps!=null && caps.getSampleBuffers()) {
		    anyHaveSampleBuffers = true;
		    break;
		}
	    }
	    int selection = super.chooseCapabilities(desired, available, windowSystemRecommendedChoice);
	    if (!anyHaveSampleBuffers) {
		System.err.println("WARNING: antialiasing will be disabled " + "because none of the available pixel formats had it to buffer");
	    }
	    else if(!available[selection].getSampleBuffers()) {
		System.err.println("WARNING: antialiasing will be disablesd " + "because the DefaultGLCapabilitiesChooser didn't supply it");
	    }
	    return selection;
	}
    }
}
				      