/**
 * The 'NodeFactExpr' class represents a factor in the syntax tree that consists 
 * of an expression enclosed in parentheses.
 */
public class NodeFactExpr extends NodeFact {

	private NodeExpr expr; // The enclosed expression.

	/**
	 * Constructs a 'NodeFactExpr' with the specified enclosed expression.
	 * 
	 * @param expr The enclosed expression within parentheses.
	 */
	public NodeFactExpr(NodeExpr expr) {
		this.expr=expr;
	}

	/**
	 * Evaluates the expression enclosed within parentheses.
	 * 
	 * @param env The environment for variables storage and evaluation.
	 * @return The result of the enclosed expression evaluation.
	 * @throws EvalException If an evaluation error occurs.
	 */
	public double eval(Environment env) throws EvalException {
		return expr.eval(env);
	}

	/**
	 * Generates and returns the corresponding C code for the enclosed expression within parentheses.
	 * 
	 * @return The C code representation of the enclosed expression in parentheses.
	 */
	public String code() { return "("+expr.code()+")"; }

}