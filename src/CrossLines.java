public class CrossLines extends CrossLine {
    public static void main (String[] args) {
	if (args.length == 0) {
	    System.err.println("Usage: java CrossLines <message>");
	}
	else {
	    new CrossLines(args);
	    new CrossLines(args);
	    new CrossLines(args);
	}
    }

    private static final long serialVersionUID = 0;

    protected CrossLines(String[] words) {
	super(words);
    }
}