package homeworkPP;

public class Numar implements Expresie {
	public String s;
	@Override
	public String eval(Context c)  {
		if (Character.isDigit(s.charAt(0))){
			return s;
		}
		if (s.charAt(0)=='@'){
				return s.substring(1,s.length());
		}
		try {
			return c.valueOf(s).toString();
		} catch (Exception e) {
			return s;
		}
	}
	public Numar(String s) {
		super();
		this.s = s;
	}
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
