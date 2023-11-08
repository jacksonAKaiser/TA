/**
 * The 'NodeAddop' class represents a node in the syntax tree for addition or subtraction operations.
 */
public class NodeAddop extends Node {

	private String addop; // The addition or subtraction operator.

	/**
	 * Constructs a 'NodeAddop' with the specified position and addop (operator).
	 * 
	 * @param pos   The position of the node in teh source code.
	 * @param addop The addition or subtraction operator.
	 */
	public NodeAddop(int pos, String addop) {
		this.pos=pos;
		this.addop=addop;
	}

	/**
	 * Performs the addition or subtraction operation on two operands.
	 * 
	 * @param o1 The first operand.
	 * @param o2 The second operand.
	 * @return The result of the addition or subtraction operation.
	 * @throws EvalException If the operator is not valid.
	 */
	public double op(double o1, double o2) throws EvalException {
		if (addop.equals("+"))
			return o1+o2;
		if (addop.equals("-"))
			return o1-o2;
		throw new EvalException(pos,"bogus addop: "+addop);
	}

	/**
	 * Generates and returns the corresponding C code for the addop.
	 * 
	 * @return The C code representation of the addop.
	 */
	public String code() { return addop; }

}