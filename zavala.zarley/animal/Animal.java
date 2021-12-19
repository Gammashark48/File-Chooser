package animal;

public interface Animal {
	public default String speak() {
		return "This animal doesn't speak";
	}
	
	public default String getName() {
		return "This animal is unknown!";
	}
	
	public default String getType() {
		return "This animal has no type!";
	}
}