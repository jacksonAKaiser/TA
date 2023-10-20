/**
 * The 'NodeStmt' class represents a statement in the syntax tree, typically containing a single assignment.
 */
public class NodeStmt extends Node {

	private NodeAssn assn; // The assignment node within the statement.

	/**
	 * Constructs a 'NodeStmt' with the specified assignment node.
	 * 
	 * @param assn The assignment node within the statement.
	 */
	public NodeStmt(NodeAssn assn) {
		this.assn=assn;
	}

	/**
	 * Evaluates the statement by delegating the evaluation to the contained assignment node.
	 * 
	 * @param env The environment for variable storage and evaluation.
	 * @return		The result of the assignment node's evaluation.
	 * @throws EvalException If an evaluation error occurs during the assignment
	 */
	public int eval(Environment env) throws EvalException {
		return assn.eval(env);
	}

	/**
	 * Generates and returns the corresponding C code for the statement.
	 * 
	 * @return The C code representation of the assignment node within the statement.
	 */
	public String code() { return assn.code(); }

}
