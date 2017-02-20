package homeworkPP;

import java.util.ArrayList;

public class CorrectnessVisitor implements Visitor {
	ArrayList<Context> listaContexte = new ArrayList<Context>();
	private boolean error;
	private boolean returned;
	private boolean returnError;

	public boolean hasReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public boolean hasReturnError() {
		return returnError;
	}

	public void setReturnError(boolean returnError) {
		this.returnError = returnError;
	}

	public boolean hasError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public CorrectnessVisitor() {
		this.listaContexte.add(new Context());
		this.error = false;
		this.returned = false;
		this.returnError = false;
	}

	private Context peekContext() {
		return this.listaContexte.get(this.listaContexte.size() - 1);
	}

	private Context popContext() {
		return this.listaContexte.remove(this.listaContexte.size() - 1);
	}

	private void pushContext(Context c) {
		this.listaContexte.add(c);
	}

	@Override
	public void visit(Numar num) {
		System.out.println(
				"Se viziteaza numarul:" + num.eval(this.peekContext()) + " in contextul :" + this.peekContext());
		if (this.hasReturned())
			this.setReturnError(true);
		String numar = num.eval(this.peekContext());
		if (!Character.isDigit(numar.charAt(0))) {
			this.setError(true);
			System.out.println("Eroare: out of scope!");
		}
	}

	@Override
	public void visit(Assign assign) {
		if (this.hasReturned())
			this.setReturnError(true);
		assign.getListaArgumente().get(1).accept(this);
		Context c = this.popContext();
		c.add(assign.getListaArgumente().get(0).eval(c), 0);
		this.pushContext(c);
	}

	@Override
	public void visit(BucataCod bucata) {
		System.out.println("Se viziteaza o bucata de cod in contextul " + this.peekContext());
		if (this.hasReturned())
			this.setReturnError(true);

		bucata.getListaArgumente().get(0).accept(this);
		bucata.getListaArgumente().get(1).accept(this);
	}

	@Override
	public void visit(Egal egal) {
		if (this.hasReturned())
			this.setReturnError(true);
		egal.getListaArgumente().get(0).accept(this);
		egal.getListaArgumente().get(1).accept(this);
	}

	@Override
	public void visit(Return r) {

		if (this.hasReturned())
			this.setReturnError(true);
		r.getListaArgumente().get(0).accept(this);
		this.setReturned(true);
	}

	@Override
	public void visit(Inmultire inmultire) {
		if (this.hasReturned())
			this.setReturnError(true);
		inmultire.getListaArgumente().get(0).accept(this);
		inmultire.getListaArgumente().get(1).accept(this);
	}

	@Override
	public void visit(MaiMic maiMic) {
		if (this.hasReturned())
			this.setReturnError(true);
		maiMic.getListaArgumente().get(0).accept(this);
		maiMic.getListaArgumente().get(1).accept(this);
	}

	@Override
	public void startVisit(Daca daca) {
		if (this.hasReturned())
			this.setReturnError(true);
		daca.getListaArgumente().get(0).accept(this);
		this.pushContext(Context.copy(this.peekContext()));
	}

	@Override
	public void endVisit(Daca daca) {
		if (this.hasReturned())
			this.setReturnError(true);
		daca.getListaArgumente().get(1).accept(this);
		daca.getListaArgumente().get(2).accept(this);
		this.popContext();
	}

	@Override
	public void startVisit(CatTimp catTimp) {
		if (this.hasReturned())
			this.setReturnError(true);
		this.pushContext(Context.copy(this.peekContext()));

		catTimp.getListaArgumente().get(0).accept(this);
	}

	@Override
	public void endVisit(CatTimp catTimp) {
		if (this.hasReturned())
			this.setReturnError(true);
		catTimp.getListaArgumente().get(1).accept(this);
		this.popContext();
		System.out.println("Am revenit la contextul : " + this.peekContext());
	}

	@Override
	public void visit(Adunare adunare) {
		if (this.hasReturned())
			this.setReturnError(true);
		adunare.getListaArgumente().get(0).accept(this);
		adunare.getListaArgumente().get(1).accept(this);
	}

}
