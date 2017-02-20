package homeworkPP;

import java.util.ArrayList;

public class Egal extends ExpList {

	public Egal(ArrayList<Expresie> listaArgumente) {
		super(listaArgumente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String eval(Context c) {

		String op1s = this.getListaArgumente().get(0).eval(c);
		String op2s = this.getListaArgumente().get(1).eval(c);
		Integer op1, op2;
		if (Character.isDigit(op1s.charAt(0))) {
			op1 = new Integer(this.getListaArgumente().get(0).eval(c));
		} else {
			op1 = 0;
		}
		if (Character.isDigit(op2s.charAt(0))) {
			op2 = new Integer(this.getListaArgumente().get(1).eval(c));
		} else {
			op2 = 0;
		}
		if (op1.equals(op2))
			return "1";
		return "0";
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
