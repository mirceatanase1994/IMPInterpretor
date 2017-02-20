package homeworkPP;

public class Variabila {
	private String name;
	private Integer value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Variabila(String name, Integer value) {
		super();
		this.name = name;
		this.value = value;
	}
	@Override
	public String toString() {
		return "Variabila [name=" + name + ", value=" + value + "]";
	}
}
