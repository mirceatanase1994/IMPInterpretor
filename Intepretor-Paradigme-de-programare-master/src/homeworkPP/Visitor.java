package homeworkPP;

public interface Visitor {
	public void visit (Numar num);
	public void visit (Assign assign);
	public void visit (BucataCod bucata);
	public void visit (Egal egal);
	public void visit (Return r);
	public void visit (Inmultire inmultire);
	public void visit (Adunare adunare);
	public void visit (MaiMic maiMic);
	public void startVisit(Daca daca);
	public void endVisit (Daca daca);
	public void startVisit(CatTimp catTimp);
	public void endVisit (CatTimp catTimp);
}
