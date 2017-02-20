package homeworkPP;

import java.util.ArrayList;

public class BucataCod extends ExpList {

	public BucataCod(ArrayList<Expresie> listaArgumente) {
		super(listaArgumente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String eval(Context c) {
		Integer rez=new Integer(0);
//		System.out.println("Evaluez argumentele(Bucata Cod):");
//		for(int i = 0; i<this.getListaArgumente().size(); i++){
//			System.out.println(this.getListaArgumente().get(i)+"--");
//		}
		for (int i = 0; i<this.getListaArgumente().size();i++) {
			rez+=new Integer(this.getListaArgumente().get(i).eval(c));
		}
		return rez.toString();
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
