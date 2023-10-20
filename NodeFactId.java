/**
 * The 'NodeFactId' class represents a factor in the syntax tree that corresponds to a variable identifier.
 */
public class NodeFactId extends NodeFact {

	private String id; // The variable identifier.

	/**
	 * Constructs a 'NodeFactId' with the sepcified position and variable identifier.
	 * 
	 * @param pos	The position of the node in the source code.
	 * @param id	The variable identifier.
	 */
	public NodeFactId(int pos, String id) {
		this.pos=pos;
		this.id=id;
	}

	/**
	 * Evaluates the variable factor by retrieving its value from the environment.
	 * 
	 * @param env The environment for variable storage and evaluation.
	 * @return The value associated with the variable.
	 * @throws EvalException If the variable is not found in the environment.
	 */
	public int eval(Environment env) throws EvalException {
		return env.get(pos,id);
	}

	/**
	 * Generates and returns the corresponding C code for the variabel identifier.
	 * 
	 * @return The C code representation of the variable identifier.
	 */
	public String code() { return id; }

}