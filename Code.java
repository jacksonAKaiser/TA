import java.io.*;

/*
 * The 'Code' class is responsible for generating a C program file by combining
 * a prologue, an environment, and user-provided code.
 */
public class Code {

	/* Prologue of the C program */
	private final String[] prologue={
		"#include <stdio.h>",
		"int main() {",
	};

	/* Epilogue of the C program */
	private final String[] epilogue={
		"return 0;",
		"}",
	};

	/* 
	 * Constructs a 'Code' object.
	 * 
	 * @param code The user-provided C code.
	 * @param env The environment for the C code.
	 */
	public Code(String code, Environment env) {
		
		/* Get the file name from the environment */
		String fn=System.getenv("Code");
		if (fn==null)
			return;

		try {
			/* Create a BufferedWriter for writing to the C file */
			BufferedWriter f=new BufferedWriter(new FileWriter(fn+".c"));

			/* Write the prologue for the C file */
			for (String s: prologue)
				f.write(s+"\n");

			/* Write the environment-specific code to the C file */
			f.write(env.toC());

			/* Write the user-provided code to the C file */
			f.write(code);

			/* Write the epilogue for the C file */
			for (String s: epilogue)
				f.write(s+"\n");

			/* closes the file writer */
			f.close();
		} catch (Exception e) {

			/* Handle any exceptions that may occur during file writing */
			System.err.println(e);
		}
	}

}