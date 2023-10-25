// This class is a scanner for the program
// and programming language being interpreted.

import java.util.*;

/**
 * The 'Scanner' class is responsible for scanning the source program and tokenizing it.
 */
public class Scanner {

	private String program;	// source program being interpreted
	private int pos;				// index of next char in program
	private Token token;		// last/current scanned token

	// sets of various characters and lexemes
	private Set<String> whitespace=new HashSet<String>();
	private Set<String> digits=new HashSet<String>();
	private Set<String> letters=new HashSet<String>();
	private Set<String> legits=new HashSet<String>();
	private Set<String> keywords=new HashSet<String>();
	private Set<String> operators=new HashSet<String>();
	private Set<String> comments=new HashSet<String>();

	// initializers for previous sets

	/**
	 * Initializes a set with characters in the range [lo,hi].
	 * 
	 * @param s 	The set to initialize
	 * @param lo 	The lower bound of characters.
	 * @param hi 	The upper bound of characters.
	 */
	private void fill(Set<String> s, char lo, char hi) {
		for (char c=lo; c<=hi; c++)
			s.add(c+"");
	}

	/**
	 * Initializes the whitespace set.
	 * 
	 * @param s The set to initialize.
	 */
	private void initWhitespace(Set<String> s) {
		s.add(" ");
		s.add("\n");
		s.add("\t");
	}

	/**
	 * Initializes the digits set
	 * 
	 * @param s The set to initialize.
	 */
	private void initDigits(Set<String> s) {
		fill(s,'0','9');
	}

	/**
	 * Initializes the letters set.
	 * 
	 * @param s The set to initialize.
	 */
	private void initLetters(Set<String> s) {
		fill(s,'A','Z');
		fill(s,'a','z');
	}

	/**
	 * Initializes the legits set as a combination of letters and digits.
	 * 
	 * @param s The set to initialize.
	 */
	private void initLegits(Set<String> s) {
		s.addAll(letters);
		s.addAll(digits);
	}

	/**
	 * Initializes the operators set with common operators.
	 * 
	 * @param s The set to initialize.
	 */
	private void initOperators(Set<String> s) {
		s.add("=");
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("(");
		s.add(")");
		s.add(";");
	}

	private void initKeywords(Set<String> s) {
	}

	private void initComments(Set<String> s) {
		s.add("#");
	}

	// constructor:
	//     - squirrel-away source program
	//     - initialize sets
	/**
	 * Constructs a 'Scanner' with the given source program.
	 * 
	 * @param program The source program to be scanned.
	 */
	public Scanner(String program) {
		this.program=program;
		pos=0;
		token=null;
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLegits(legits);
		initKeywords(keywords);
		initOperators(operators);
	}

	// handy string-processing methods

	/**
	 * Checks if the scanning process is complete.
	 * 
	 * @return 'true' if scanning si done, 'false' otherwise.
	 */
	public boolean done() {
		return pos>=program.length();
	}

	/**
	 * Advances the scanner until the current input character is not contained in the given set of characters.
	 * 
	 * @param s The set of characters to search for.
	 */
	private void many(Set<String> s) {
		while (!done()&&s.contains(program.charAt(pos)+""))
			pos++;
	}

	// This method advances the scanner,
	// until the current input character
	// is just after a sequence of one or more
	// of a particular character.
	// Arguments:
	//     c = the character to search for
	// Members:
	//     program = the scanner's input
	//     pos = index of current input character
	private void past(char c) {
		while (!done()&&c!=program.charAt(pos))
			pos++;
		if (!done()&&c==program.charAt(pos))
			pos++;
	}

	// scan various kinds of lexeme

	/**
	 * Scans and tokenizes the next number in the program.
	 */
	private void nextNumber() {
		int old=pos;
		many(digits);
		token=new Token("num",program.substring(old,pos));
	}

	/**
	 * Scans and tokenizes the next keyword or identifier in the program.
	 */
	private void nextKwId() {
		int old=pos;
		many(letters);
		many(legits);
		String lexeme=program.substring(old,pos);
		token=new Token((keywords.contains(lexeme) ? lexeme : "id"),lexeme);
	}

	/**
	 * Scans and tokenizes the next operator in the program.
	 */
	private void nextOp() {
		int old=pos;
		pos=old+2;
		if (!done()) {
			String lexeme=program.substring(old,pos);
			if (operators.contains(lexeme)) {
				token=new Token(lexeme); // two-char operator
				return;
			}
		}
		pos=old+1;
		String lexeme=program.substring(old,pos);
		token=new Token(lexeme); // one-char operator
	}

	// This method determines the kind of the next token (e.g., "id"),
	// and calls a method to scan that token's lexeme (e.g., "foo").
	/**
	 * Determines the kind of next token (e.g., "id") and calls a method to scan that token's lexeme (e.g., "foo").
	 * 
	 * @return 'true' if a valid toke is found, 'false' if there are no more tokens.
	 */	
	public boolean next() {
		many(whitespace);
		if (done()) {
			token=new Token("EOF");
			return false;
		}
		String c=program.charAt(pos)+"";
		if (comments.contains(c)) {
			pos++;
			past('#');
			return next();
		}
		else if (digits.contains(c))
			nextNumber();
		else if (letters.contains(c))
			nextKwId();
		else if (operators.contains(c))
			nextOp();
		else {
			System.err.println("illegal character at position "+pos);
			pos++;
			return next();
		}
		return true;
	}

	// This method scans the next lexeme,
	// if the current token is the expected token.
	/**
	 * Scans and tokenizes the next lexeme, if it matches the expcted token.
	 * 
	 * @param t The expected token to match.
	 * @throws SyntaxException If the next token doesn't match the expected token.
	 */
	public void match(Token t) throws SyntaxException {
		if (!t.equals(curr()))
			throw new SyntaxException(pos,t,curr());
		next();
	}

	/**
	 * Retrieves the current token.
	 * 
	 * @return The current token.
	 * @throws SyntaxException If there's no current token (token is null).
	 */
	public Token curr() throws SyntaxException {
		if (token==null)
			throw new SyntaxException(pos,new Token("ANY"),new Token("EMPTY"));
		return token;
	}

	/**
	 * Retrieves the current position in the source program.
	 * 
	 * @return The current position.
	 */
	public int pos() {
		return pos;
	}

	// for unit testing
	/**
	 * A static method for unit testing. It creates a 'Scanner' with the provided source program and 
	 * prints each token found in the program to the standard output.
	 * 
	 * @param args An array of command-line arguments, where args[0] should be the source program.
	 */
	public static void main(String[] args) {
		try {
			Scanner scanner=new Scanner(args[0]);
			while (scanner.next())
				System.out.println(scanner.curr());
		} catch (SyntaxException e) {
			System.err.println(e);
		}
	}

}
