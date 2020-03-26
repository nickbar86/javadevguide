import java.util.function.Function;

/*
 *  The Function interface represents a function (method) that takes a single parameter and returns a single value. 
 */
public class Func {
	public static void main(String[] args) {
		Function<Long, String> adder = (value) -> ""+(value + 3);
		System.out.println(adder.apply(3L));
	}
}
