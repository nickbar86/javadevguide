import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

/*
 *  The Function interface represents a function (method) that takes a single parameter and returns a single value. 
 */
public class Func {
	public static void main(String[] args) {
		Function<Long, String> adder = (value) -> "" + (value + 3);
		System.out.println(adder.apply(3L));
		BiFunction<Double, String, Long> adderToLong = (d, s) -> (long) (d + Long.parseLong(s));

		Function<Long, BigDecimal> bigDecimalConverter = l -> BigDecimal.valueOf(l);

		BiFunction<Double, String, BigDecimal> biFunction = adderToLong.andThen(bigDecimalConverter);

		BigDecimal bd = biFunction.apply(20.33d, "34");
		System.out.println(bd);
		

		Function<Integer, String> intToString = Object::toString;
		Function<String, String> quote = s -> "'" + s + "'";
		Function<Integer, String> quoteIntToString = quote.compose(intToString);
		Function<Integer, String> quoteIntToString2 = intToString.andThen(quote);
		System.out.println(quoteIntToString.apply(5));
		System.out.println(quoteIntToString2.apply(5));
	}
}
