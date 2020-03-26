import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/*The Java Consumer interface is a functional interface that represents a function that consumes a value without returning any value.
 * 
 */
public class Cons {
	public static void main(String[] args) {
		Consumer<Integer> cons = val -> System.out.println(val);
		cons.accept(2);
		
		 List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		 list.stream().forEach(cons);
	}
}
