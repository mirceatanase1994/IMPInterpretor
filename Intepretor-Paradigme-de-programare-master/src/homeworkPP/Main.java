package homeworkPP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

	/**
	 * IMPORTANT! Your solution will have to implement this method.
	 * 
	 * @param exp
	 *            - a string, which represents an expression (that follows the
	 *            specification in the homework);
	 * @param c
	 *            - the context (a one-to-one association between variables and
	 *            values);
	 * @return - the result of the evaluation of the expression;
	 */
	public static Expresie createExpression(String[] parsedArguments) {
		ArrayList<Expresie> listaArgumente = new ArrayList<Expresie>();
		if (parsedArguments[0].equals("+")) {
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[1]))));
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[2]))));
			return new Adunare(listaArgumente);
		}
		if (parsedArguments[0].equals("*")) {
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[1]))));
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[2]))));
			return new Inmultire(listaArgumente);
		}
		if (parsedArguments[0].equals("<")) {
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[1]))));
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[2]))));
			return new MaiMic(listaArgumente);
		}

		if (parsedArguments[0].equals("if")) {
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[1]))));
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[2]))));
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[3]))));
			return new Daca(listaArgumente);
		}
		if (parsedArguments[0].equals("while")) {
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[1]))));
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[2]))));
			return new CatTimp(listaArgumente);
		}
		if (parsedArguments[0].equals("=")) {
			listaArgumente.add(createExpression(splitList(eliminaParanteze("@" + parsedArguments[1]))));
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[2]))));
			return new Assign(listaArgumente);
		}

		if (parsedArguments[0].equals(";")) {
			for (int i = 1; i < parsedArguments.length; i++) {
				listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[i]))));
			}
			return new BucataCod(listaArgumente);
		}

		if (parsedArguments[0].equals("==")) {
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[1]))));
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[2]))));
			return new Egal(listaArgumente);
		}

		if (parsedArguments[0].equals("return")) {
			listaArgumente.add(createExpression(splitList(eliminaParanteze(parsedArguments[1]))));
			return new Return(listaArgumente);
		}

		return new Numar(parsedArguments[0]);

	}

	public static Integer evalExpression(String exp, Context c) {
		/* TO-DO */
		// String[] vector = splitList(exp);
		// for (int i = 0; i<vector.length; i++){
		// System.out.println("|"+vector[i]+"|");
		// }

		Expresie expresie = createExpression(splitList(eliminaParanteze(exp)));
		return new Integer(expresie.eval(c));
	}

	/**
	 * IMPORTANT! Your solution will have to implement this method.
	 * 
	 * @param program
	 *            - a string, which represents a program (that follows the
	 *            specification in the homework);
	 * @return - the result of the evaluation of the expression;
	 */
	public static Integer evalProgram(String program) {
		Context c = new Context();
		Expresie cod = createExpression(splitList(eliminaParanteze(program)));
		return new Integer(cod.eval(c));
	}

	/**
	 * IMPORTANT! Your solution will have to implement this method.
	 * 
	 * @param program
	 *            - a string, which represents a program (that follows the
	 *            specification in the homework);
	 * @return - whether the given program follow the syntax rules specified in
	 *         the homework (always return a value and always use variables that
	 *         are "in scope");
	 */

	public static Boolean checkCorrectness(String program) {
		Expresie cod = createExpression(splitList(eliminaParanteze(program)));
		CorrectnessVisitor v = new CorrectnessVisitor();
		cod.accept(v);
		return (!(v.hasError() || v.hasReturnError())) && v.hasReturned();
	}

	/**
	 *
	 * @param s
	 *            - a string, that contains a list of programs, each program
	 *            starting with a '[' and ending with a matching ']'. Programs
	 *            are separated by the whitespace caracter;
	 * @return - array of strings, each element in the array representing a
	 *         program; Example: "[* [+ 1 2] 3] [* 4 5]" -> "[* [+ 1 2] 3]" &
	 *         "[* 4 5]";
	 */
	public static String eliminaParanteze(String s) {
		if (s.charAt(0) == '[') {
			return s.substring(1, s.length() - 1);
		}
		return s;
	}

	public static String[] splitList(String s) {
		String[] result = new String[0];
		List<String> l = new LinkedList<String>();
		int inside = 0;
		int start = 0, stop = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '[') {
				inside++;
				stop++;
				continue;
			}
			if (s.charAt(i) == ']') {
				inside--;
				stop++;
				continue;
			}
			if (s.charAt(i) == ' ' && inside == 0) {
				l.add(s.substring(start, stop));
				start = i + 1; // starting after whitespace
				stop = start;

				continue;
			}
			stop++; // no special case encountered
		}
		if (stop > start) {
			l.add(s.substring(start, stop));
		}

		return l.toArray(new String[l.size()]);

	}

	public static void main(String[] args) {
		/* Suggestion: use it for testing */
		Context c = new Context();
		c.add("x", 5);
		c.add("y", 4);
		c.add("z", 10);
		System.out.println("Evaluare expresie:" + evalExpression(
				"[; [= x 10] [; [if [== x 10] [= x 3] [= x 10]] [return y]]]",
				c));
		System.out.println("Evaluare prog:" + evalProgram(
				"[; [= x 10] [; [if [== x 10] [= x 3] [= x 10]] [return y]]]"));

		System.out.println(checkCorrectness(
				"[; [= x 10] [; [if [== x 10] [= x 3] [= x 10]] [return y]]]"));

	}
}
