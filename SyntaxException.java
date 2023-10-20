/**
 * The 'SyntaxException' class is an exception that represents a syntax error in the program.
 */
public class SyntaxException extends Exception {

	private int pos;				// The position where the syntax error occurred.
	private Token expected; // The expected token.
	private Token found;		// The token that was found.

	/**
	 * Constructs a 'SyntaxExceptoin' with the specified position, expected token, and found token.
	 * 
	 * @param pos				The position where the syntax error occurred.
	 * @param expected	The expected token.
	 * @param found			The token that was found.
	 */
	public SyntaxException(int pos, Token expected, Token found) {
		this.pos=pos;
		this.expected=expected;
		this.found=found;
	}

	/**
	 * Provides a string representation of the syntax error, including position, expected token, and found token.
	 * 
	 * @return A string describing the syntax error.
	 */
	public String toString() {
		return "syntax error"
			+", pos="+pos
			+", expected="+expected
			+", found="+found;
	}

}
