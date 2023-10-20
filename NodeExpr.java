/**
 * The 'NodeExpr' class represents a node in the syntax tree for expressions.
 */
public class NodeExpr extends Node {

	private NodeTerm term;		// The primary term of the expression.
	private NodeAddop addop;	// The addition or subtraction operator.
	private NodeExpr expr;		// The secondary expression for binary operations.

	/**
	 * Constructs a 'NodeExpr' with the specified primary term, addop (operator), and secondary expression.
	 * 
	 * @param term	The primary term of the expression.
	 * @param addop	The addition or subtraction operator.
	 * @param expr	The secondary expression.
	 */
	public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
		this.term=term;
		this.addop=addop;
		this.expr=expr;
	}

	/**
	 * Appends another 'NodeExpr' to this expression node.
	 * 
	 * @param expr The expression to append.
	 */
	public void append(NodeExpr expr) {
		if (this.expr==null) {
			this.addop=expr.addop;
			this.expr=expr;
			expr.addop=null;
		} else
			this.expr.append(expr);
	}

	/**
	 * Evaluates the Expression node by recursively evaluation its components.
	 * 
	 * @param env The environment for variable storage and evaluation.
	 * @return The result of the expression evaluation.
	 * @throws EvalException If an evaluation error occurs.
	 */
	public int eval(Environment env) throws EvalException {
		return expr==null
			? term.eval(env)
			: addop.op(expr.eval(env),term.eval(env));
	}

	/**
	 * Generates and returns the corresponding C code for the expression.
	 * 
	 * @return The c code representation of the expression.
	 */
	public String code() {
		return (expr==null ? "" : expr.code()+addop.code())+term.code();
	}

}