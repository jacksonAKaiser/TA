// This is the main class/method for the interpreter/compiler.
// Each command-line argument is a complete program,
// which is scanned, parsed, and evaluated.
// All evaluations share the same environment,
// so they can share variables.

/**
 * The `Main` class represents the entry point of the program for executing C code generation.
 */
public class Main {

	/**
     * The main method responsible for parsing and evaluating the input code.
     *
     * @param args An array of command-line arguments containing the input code.
     */
	public static void main(String[] args) {
		Parser parser=new Parser(); //Create a parser object.
		Environment env=new Environment(); // Create an environment for variable storage.
		String code=""; // Initialize a string to store generated C code.

		/* Iterate through the command-line arguments containing the input code. */
		for (String prog: args)
			try {

				/* Parse the input code into a syntax tree. */
				Node node=parser.parse(prog);

				/* Evaluate the syntax tree using the environment. */
				node.eval(env);

				/* Append the generated C code to the code string. */
				code+=node.code();
			} catch (Exception e) {

				/* Handle an exceptions that may occur during parsing or evaluation. */
				System.err.println(e);
			}

		/* Generate and save the final C code using the code class. */
		new Code(code,env);
	}

}