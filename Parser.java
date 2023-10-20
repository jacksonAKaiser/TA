// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

/**
 * The 'Parser' class is responsible for parting a given program and constructing a syntax tree.
 */
public class Parser {

	private Scanner scanner;

	/**
	 * Matches the current token to an expected string, throwing a 'SyntaxException' if they don't match.
	 * 
	 * @param s The expected token string.
	 * @throws SyntaxException If the current token doesn't match the expected string.
	 */
	private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
	}

	/**
	 * Retrieves the current token.
	 * 
	 * @return The current token.
	 * @throws SyntaxException If there's an issue getting the current token.
	 */
	private Token curr() throws SyntaxException {
		return scanner.curr();
	}

	/**
	 * Retrieves the current position in the program.
	 * 
	 * @return the current position.
	 */
	private int pos() {
		return scanner.pos();
	}

	/**
	 * Parses a multiplication or division operator and constructs a 'NodeMulop' accordingly.
	 * 
	 * @return The parsed 'NodeMulop' or null if no mulop is found.
	 * @throws SyntaxException If there's a syntax error.
	 */
	private NodeMulop parseMulop() throws SyntaxException {
		if (curr().equals(new Token("*"))) {
			match("*");
			return new NodeMulop(pos(), "*");
		}
		if (curr().equals(new Token("/"))) {
			match("/");
			return new NodeMulop(pos(), "/");
		}
		return null;
	}

	/**
	 * Parses an addition or subtraction operator and constructs a 'NodeAddop' accordingly.
	 * 
	 * @return The parsed 'NodeAddop' or null fi no addop is found.
	 * @throws SyntaxException If there's a syntax error.
	 */
	private NodeAddop parseAddop() throws SyntaxException {
		if (curr().equals(new Token("+"))) {
			match("+");
			return new NodeAddop(pos(), "+");
		}
		if (curr().equals(new Token("-"))) {
			match("-");
			return new NodeAddop(pos(), "-");
		}
		return null;
	}

	/**
	 * Parses a factor in the program and constructs an appropriate 'NodeFact'.
	 * 
	 * @return The parsed 'NodeFact'.
	 * @throws SyntaxException If there's a syntax error.
	 */
	private NodeFact parseFact() throws SyntaxException {
		if (curr().equals(new Token("("))) {
			match("(");
			NodeExpr expr = parseExpr();
			match(")");
			return new NodeFactExpr(expr);
		}
		if (curr().equals(new Token("id"))) {
			Token id = curr();
			match("id");
			return new NodeFactId(pos(), id.lex());
		}
		Token num = curr();
		match("num");
		return new NodeFactNum(num.lex());
	}

	/**
	 * Parses a term in the program and constructs a 'NodeTerm'.
	 * 
	 * @return The parsed 'NodeTerm'.
	 * @throws SyntaxException If there's a syntax error.
	 */
	private NodeTerm parseTerm() throws SyntaxException {
		NodeFact fact = parseFact();
		NodeMulop mulop = parseMulop();
		if (mulop == null)
			return new NodeTerm(fact, null, null);
		NodeTerm term = parseTerm();
		term.append(new NodeTerm(fact, mulop, null));
		return term;
	}

	/**
	 * Parses an expression in the program and constructs a 'NodeExpr'.
	 * 
	 * @return The parsed 'NodeExpr'.
	 * @throws SyntaxException If there's a syntax error.
	 */
	private NodeExpr parseExpr() throws SyntaxException {
		NodeTerm term = parseTerm();
		NodeAddop addop = parseAddop();
		if (addop == null)
			return new NodeExpr(term, null, null);
		NodeExpr expr = parseExpr();
		expr.append(new NodeExpr(term, addop, null));
		return expr;
	}

	/**
	 * Parses an assignment in the program and constructs a 'NodeAssn'.
	 * 
	 * @return The parsed 'NodeAssn'.
	 * @throws SyntaxException If there's a syntax error.
	 */
	private NodeAssn parseAssn() throws SyntaxException {
		Token id = curr();
		match("id");
		match("=");
		NodeExpr expr = parseExpr();
		NodeAssn assn = new NodeAssn(id.lex(), expr);
		return assn;
	}

	/**
	 * Parses a statement in the program and constructs a 'NodeStmt'.
	 * 
	 * @return The parsed 'NodeStmt'.
	 * @throws SyntaxException If there's a syntax error.
	 */
	private NodeStmt parseStmt() throws SyntaxException {
		NodeAssn assn = parseAssn();
		match(";");
		NodeStmt stmt = new NodeStmt(assn);
		return stmt;
	}

	/**
	 * Parses the entire program and constructs the syntax tree.
	 * 
	 * @param program The program to parse.
	 * @return The root of the syntax tree.
	 * @throws SyntaxException If there's a syntax error.
	 */
	public Node parse(String program) throws SyntaxException {
		scanner = new Scanner(program);
		scanner.next();
		NodeStmt stmt = parseStmt();
		match("EOF");
		return stmt;
	}

}
