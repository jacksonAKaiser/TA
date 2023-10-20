// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")

/**
 * The 'Token' class represents a token in the program, consisting of a token type and associated lexeme.
 */
public class Token {

	private String token;		// The token type.
	private String lexeme;	// The associated lexeme.
	/**
	 * Constructs a 'Token' with the specified token type and lexeme
	 * 
	 * @param token 	The token type.
	 * @param lexeme	The associated lexeme.
	 */
	public Token(String token, String lexeme) {
		this.token=token;
		this.lexeme=lexeme;
	}

	/**
	 * Constructs a 'Token' with the same token type and lexeme.
	 * 
	 * @param token The token type and lexeme.
	 */
	public Token(String token) {
		this(token,token);
	}

	/**
	 * Retrieves the token type.
	 * 
	 * @return The token type.
	 */
	public String tok() { return token; }

	/**
	 * Retrieves the associated lexeme.
	 * 
	 * @return The lexeme.
	 */
	public String lex() { return lexeme; }

	/**
	 * Compares the current 'Token' to another 'Token' for equality based on their token types.
	 * 
	 * @param t The 'Token' to compare to.
	 * @return	'true' if the token types are equal, 'false' otherwise.
	 */
	public boolean equals(Token t) {
		return token.equals(t.token);
	}

	/**
	 * Provides a string representation of the 'Token' in the format "<token,lexeme>".
	 * 
	 * @return A string representation of the token.
	 */
	public String toString() {
		return "<"+tok()+","+lex()+">";
	}

}
