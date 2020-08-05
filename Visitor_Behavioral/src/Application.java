
public class Application {

	public static void main(String[] args) {
		ComputerPart computerPart = new Computer();
		computerPart.accept(new ComputerPartDisplayVisitor());
	}

}
