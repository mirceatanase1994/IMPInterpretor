package homeworkPP;

import java.util.ArrayList;

public class Assign extends ExpList {

	public Assign(ArrayList<Expresie> listaArgumente) {
		super(listaArgumente);
	}

	@Override
	public String eval(Context c) {
		String opLeft = this.getListaArgumente().get(1).eval(c);
		Integer op = 0;
		;
		if (Character.isDigit(opLeft.charAt(0))) {
			op = new Integer(opLeft);
		}
		String numeVar = this.getListaArgumente().get(0).eval(c);
		c.add(numeVar, op);
		return "0";
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
