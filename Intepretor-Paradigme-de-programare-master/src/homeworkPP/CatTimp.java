package homeworkPP;

import java.util.ArrayList;

public class CatTimp extends ExpList {

	public CatTimp(ArrayList<Expresie> listaArgumente) {
		super(listaArgumente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String eval(Context c) {

		System.out.println("Evaluez argumentele(Cat timp):");
		for(int i = 0; i<this.getListaArgumente().size(); i++){
			System.out.println(this.getListaArgumente().get(i)+"--");
		}
		while (new Integer(this.getListaArgumente().get(0).eval(c))>0)
			this.getListaArgumente().get(1).eval(c);
		return "0";
	}

	@Override
	public void accept(Visitor v) {
		v.startVisit(this);
		v.endVisit(this);
	}

}
