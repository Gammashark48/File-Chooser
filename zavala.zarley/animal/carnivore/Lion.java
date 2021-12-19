package animal.carnivore;
import animal.Animal;

public class Lion implements Animal {
    private String name;
    private String type = "Lion";
	public String getName() {
		return this.name;
	}
	public String getType() {
	    return this.type;
	}
	
	public Lion(String name) {
	    this.name = name;
	}

}