import java.util.Arrays;
import java.util.stream.IntStream;

public class Computer implements ComputerPart {
	ComputerPart[] parts;
	
	
	public Computer() {
		this.parts = new ComputerPart[] { new Mouse(), new Keyboard(), new Monitor() };
	}


	@Override
	public void accept(ComputerPartVisitor visitor) {
		Arrays.stream(parts).forEach(part->part.accept(visitor));
		visitor.visit(this);
	}

}
