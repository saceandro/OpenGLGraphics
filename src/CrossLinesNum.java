     1	public class CrossLines extends CrossLine {
     2	    public static void main (String[] args) {
     3		if (args.length == 0) {
     4		    System.err.println("Usage: java CrossLines <message>");
     5		}
     6		else {
     7		    new CrossLines(args);
     8		    new CrossLines(args);
     9		    new CrossLines(args);
    10		}
    11	    }
    12	
    13	    private static final long serialVersionUID = 0;
    14	
    15	    protected CrossLines(String[] words) {
    16		super(words);
    17	    }
    18	}