//Visitable
public interface ComputerPart {
	//accepts a visitor
	public void accept(ComputerPartVisitor visitor);
}
