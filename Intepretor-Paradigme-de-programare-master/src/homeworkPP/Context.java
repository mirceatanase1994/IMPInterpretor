package homeworkPP;

import java.util.ArrayList;

public class Context {
	@Override
	public String toString() {
		return "Context [variables=" + variables + "]";
	}
	private ArrayList<Variabila> variables = new ArrayList<Variabila>();
	public void add (String v, Integer i){
        /* TO-DO */
		for (int j = 0; j<this.variables.size();j++){
			if (this.variables.get(j).getName().equals(v)){
				this.variables.remove(j);
			}
		}
    	this.variables.add(new Variabila(v,i));
    }
    // Treat undefined variable problem using exceptions
    public Integer valueOf (String v) throws Exception {
    	for (Variabila var:this.variables){
    		if (var.getName().equals(v))
    			return var.getValue();
    	}
    	throw new Exception();
    }
    
    public static Context copy(Context c){
    	Context c1 = new Context();
    	for (Variabila v:c.variables){
    		c1.add(v.getName(), v.getValue());
    	}
    	return c1;
    }
}