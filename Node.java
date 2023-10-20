// This class, and its subclasses,
// collectively model parse-tree nodes.
// Each kind of node can be eval()-uated,
// and/or code()-generated.

/**
 * The 'Node' class represents an abstract node in the syntax tree.
 */
public abstract class Node {

	protected int pos=0; // The position of the node in the source code.

	/**
	 * Evaluates the node using the provided environment.
	 * 
	 * @param env The environment for variable storage and evaluation.
	 * @return The result of the evaluation.
	 * @throws EvalException Tif the node cannot be evaluated.
	 */
	public int eval(Environment env) throws EvalException {
		throw new EvalException(pos,"cannot eval() node!");
	}

	/**
	 * Generates and returns the corresponding C code for the node.
	 * 
	 * @return The C code representation of the node.
	 */
	public String code() { return ""; }

}