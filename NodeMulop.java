/**
 * The 'NodeMulop' class represents a node in the syntax tree for multiplication or division operations.
 */
public class NodeMulop extends Node {

	private String mulop; // The multiplication or division operator.

	/**
	 * Constructs a 'NodeMulop' with the specified position and mulop (operator).
	 * 
	 * @param pos		The position of the node in the source code.
	 * @param mulop	The multiplication or division operator.
	 */
	public NodeMulop(int pos, String mulop) {
		this.pos=pos;
		this.mulop=mulop;
	}

	/**
	 * Performs the multiplication or division operation on two operands.
	 * 
	 * @param o1	The first operand.
	 * @param o2	The second operand.
	 * @return		The result of the multiplication or division operation.
	 * @throws EvalException	If the operator is not valid.
	 */
	public double op(double o1, double o2) throws EvalException {
		if (mulop.equals("*"))
			return o1*o2;
		if (mulop.equals("/"))
			return o1/o2;
		throw new EvalException(pos,"bogus mulop: "+mulop);
	}

	/**
	 * Generates and returns the corresponding C code for the mulop.
	 * 
	 * @return The C code representation of the multiplication or division operator
	 */
	public String code() { return mulop; }

}
