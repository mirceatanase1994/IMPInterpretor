package homeworkPP;

import java.util.ArrayList;

public class Return extends ExpList {

	public Return(ArrayList<Expresie> listaArgumente) {
		super(listaArgumente);
	}

	@Override
	public String eval(Context c) {
		String deReturnat = this.getListaArgumente().get(0).eval(c);
		if (!Character.isDigit(deReturnat.charAt(0)))
			return "0";
		return deReturnat;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
