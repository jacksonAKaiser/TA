/**
 * The 'NodeAssn' class represents a node in the syntax tree for variable assignments.
 */
public class NodeAssn extends Node {

	private String id; 			// The identifier for the variable.
	private NodeExpr expr; 	// The expression to be assigned to the variable.

	/**
	 * Constructs a 'NodeAssn' with the specified variable identifier and expression.
	 * 
	 * @param id		The variable identifier.
	 * @param expr	The expression to be assigned to the variable.
	 */
	public NodeAssn(String id, NodeExpr expr) {
		this.id = id;
		this.expr = expr;
	}

	/**
	 * Evaluates the variable assignment node by updating the environments.
	 * 
	 * @param env The environment for variable storage and evaluation.
	 * @return The result of the assignment (the assigned value).
	 * @throws EvalExceptoin If an evaluation error occurs.
	 */
	public int eval(Environment env) throws EvalException {
		// Update the environment by putting the value of the expression into the variable.
		return env.put(id, new NodeWr(expr).eval(env));
	}

	/**
	 * Generates and returns the corresponding C code for the variable assignment.
	 * 
	 * @return The C code representation of the variable assignment.
	 */
	public String code() {
		// Generate C code for the assignment statement.
		return id + "=" + expr.code() + ";" + new NodeWr(expr).code();
	}

}