/**
 * The 'NodeWr' class represents a node in the syntax tree for writing (printing) expressions to the console.
 */
public class NodeWr extends Node {

	private NodeExpr expr; // The expression to be written (printed).

	/**
	 * Constructs a 'NodeWr' with the specified expression to be written.
	 * 
	 * @param expr The expression to be written (printed)
	 */
	public NodeWr(NodeExpr expr) {
		this.expr=expr;
	}

	/**
	 * Evaluates the write (print) operation by evaluating the expression and printing its result to the console.
	 * 
	 * @param env The environment for variable storage and evaluation.
	 * @return The result of the evaluated expression.
	 * @throws EvalException If an evaluation error occurs.
	 */
	public double eval(Environment env) throws EvalException {
		double d=expr.eval(env);
		int i=(int) d;
		if (i==d)
			System.out.println(i);
		else
			System.out.println(d);
		return d;
	}

	/**
	 * Generates and returns the corresponding C cod3e for the write (print) operation.
	 * 
	 * @return The C code representation of the write (print) operation.
	 */
	public String code() {
		return "printf(\"%g\\n\","
			+"(double)("
			+expr.code()
			+"));";
	}

}
