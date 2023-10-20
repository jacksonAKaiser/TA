/**
 * The 'NodeTerm' class represents a node in the syntax tree for multiplication or division operations
 * between factors.
 */
public class NodeTerm extends Node {

	private NodeFact fact;		// The primary factor of the term.
	private NodeMulop mulop;	// The multiplication or division operator.
	private NodeTerm term;		// The secondary term for binary operations.

	/**
	 * Constructs a 'NodeTerm' with the specified primary factor, mulop (operator), and secondary term.
	 * 
	 * @param fact	The primary factor of the term.
	 * @param mulop	The multiplication or division operator.
	 * @param term	The secondary term.
	 */
	public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) {
		this.fact=fact;
		this.mulop=mulop;
		this.term=term;
	}

	/**
	 * Appends another 'NodeTerm' to this term node.
	 * 
	 * @param term The term to append.
	 */
	public void append(NodeTerm term) {
		if (this.term==null) {
			this.mulop=term.mulop;
			this.term=term;
			term.mulop=null;
		} else
			this.term.append(term);
	}

	/**
	 * Evaluates th eterm node by recursively evaluation its components.
	 * 
	 * @param env The environment for variable storage and evaluation.
	 * @return The result of the term evaluation.
	 * @throws EvalException If an evaluation error occurs.
	 */
	public int eval(Environment env) throws EvalException {
		return term==null
			? fact.eval(env)
			: mulop.op(term.eval(env),fact.eval(env));
	}

	/**
	 * Generates and returns the corresponding C code for the term.
	 * 
	 * @return The C code representation of the term.
	 */
	public String code() {
		return (term==null ? "" : term.code()+mulop.code())+fact.code();
	}

}
