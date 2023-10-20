/*
 * The 'NodeFactNum' class represents a factor in the syntax tree that corresponds to a numeric constant.
 */
public class NodeFactNum extends NodeFact {

	private String num; // The numeric constant.

	/**
	 * Constructs a 'NodeFactNum' with the specified numeric constant.
	 * 
	 * @param num The numeric constant.
	 */
	public NodeFactNum(String num) {
		this.num=num;
	}

	/**
	 * Evaluates the numeric constant factor by parsing and returning its integer value.
	 * 
	 * @param env the environment for variable storage and evaluation (not used for constants).
	 * @return The integer value of the numeric constant.
	 * @throws EvalExceptoin This exception is not thrown for constant evaluation.
	 */
	public int eval(Environment env) throws EvalException {
		return Integer.parseInt(num);
	}

	/**
	 * Generates and returns the corresponding C code for the numeric constant.
	 * 
	 * @return The C code representation of the numeric constant.
	 */
	public String code() { return num; }

}
