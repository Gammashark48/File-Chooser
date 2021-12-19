package animal.herbivore;
import animal.Animal;

public class Giraffe implements Animal {
    private String name;
    private String type = "Giraffe";
	public String getName() {
		return this.name;
	}
	public String getType() {
	    return this.type;
	}
	
	public Giraffe(String name) {
	    this.name = name;
	}

}