/**
 * The 'EvalException' class represents an exception that is thrown in the case of an 
 * evaluation error during program execution.
 */
public class EvalException extends Exception {

	private int pos; 		// The position where the error occurred.
	private String msg; // A message describing the error

	/**
	 * Constructs an 'EvalException' with the specified position and error message.
	 * 
	 * @param pos The position where the error occurred.
	 * @param msgA message describing the error.
	 */
	public EvalException(int pos, String msg) {
		this.pos=pos;
		this.msg=msg;
	}

	/**
	 * Returns a string representation of the 'EvalExceptoin', including the error type,
	 * position, and error message.
	 * 
	 * @return A string representation of the exception.
	 */
	public String toString() {
		return "eval error"
			+", pos="+pos
			+", "+msg;
	}

}