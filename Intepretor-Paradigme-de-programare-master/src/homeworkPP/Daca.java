package homeworkPP;

import java.util.ArrayList;

public class Daca extends ExpList{
    
	public Daca(ArrayList<Expresie> listaArgumente) {
		super(listaArgumente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String eval(Context c) {

		System.out.println("Evaluez argumentele(Daca):");
		for(int i = 0; i<this.getListaArgumente().size(); i++){
			System.out.println(this.getListaArgumente().get(i)+"--");
		}
		Expresie conditie = this.getListaArgumente().get(0);
		//System.out.println("Conditia pe if este "+ conditie.eval(c));
		if (new Integer(conditie.eval(c)) > 0)
			return this.getListaArgumente().get(1).eval(c);
		return this.getListaArgumente().get(2).eval(c);
	}

	@Override
	public void accept(Visitor v) {
		v.startVisit(this);
		v.endVisit(this);
	}

}
