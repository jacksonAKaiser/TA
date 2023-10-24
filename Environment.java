// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

// Hint!
// Use the Java API to implement your Environment.
// Browse:
//   https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
// Read about Collections.
// Focus on the Map interface and HashMap implementation.
// Also:
//   https://www.tutorialspoint.com/java/java_map_interface.htm
//   http://www.javatpoint.com/java-map
// and elsewhere.

/* 
 * The 'Environment' class represents a simple environment for variable mapping
 * and C code generation.
 */
public class Environment {

	// Array of variable name(s)
	//private String[] map = { "x" };
	//Change to <String, Double> after changing all other file inputs to double(ALL FILES)
	private HashMap<String, Integer> HashMap;

	/**
	 * Adds or updates a variable in the environment.
	 * 
	 * @param var The variable name to add or update.
	 * @param val The value to associate with the variable.
	 * @return The provided value.
	 */
	public int put(String var, int val) {

		HashMap.put(var,val);

		return val;
	}

	/**
	 * Retrives the value of a variable from the environment.
	 * 
	 * @param pos The position of the variable in the environment.
	 * @param var The variable name to retrieve.
	 * @return The value associated with the variable.
	 * @throws EvalException If the variable is not found in the environment.
	 */
	public int get(int pos, String var) throws EvalException {
		return HashMap.get(var);
	}

	/**
	 * Converts the environment to a C code representation.
	 * 
	 * @return The C code representation fo the environment variables.
	 */
	public String toC() {
		String s = "";
		String sep = " ";
		for (String v : HashMap.keySet()) {
			s += sep + v;
			sep = ",";
		}
		// Return the C code representation or an empty string if there are no variables.
		return s == "" ? "" : "int" + s + ";\nx=0;x=x;\n";
	}

}