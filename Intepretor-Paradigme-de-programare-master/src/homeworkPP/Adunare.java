package homeworkPP;

import java.util.ArrayList;

public class Adunare extends ExpList {

	public Adunare(ArrayList<Expresie> listaArgumente) {
		super(listaArgumente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String eval(Context c) {
		// System.out.print("Adun "+this.getListaArgumente().get(0).eval(c)+
		// " cu "+this.getListaArgumente().get(1).eval(c)+" si obtin ");
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
		return new Integer(op1 + op2).toString();

	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
