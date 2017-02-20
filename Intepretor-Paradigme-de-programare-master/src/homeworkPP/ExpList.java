package homeworkPP;

import java.util.ArrayList;

public abstract class ExpList implements Expresie {
	private ArrayList<Expresie> listaArgumente;
	public void add(Expresie e){
		this.listaArgumente.add(e);
	}
	public ArrayList<Expresie> getListaArgumente() {
		return listaArgumente;
	}
	public void setListaArgumente(ArrayList<Expresie> listaArgumente) {
		this.listaArgumente = listaArgumente;
	}
	public ExpList(ArrayList<Expresie> listaArgumente) {
		super();
		this.listaArgumente = listaArgumente;
	}
}
