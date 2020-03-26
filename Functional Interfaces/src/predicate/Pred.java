package predicate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * The Java Predicate interface, java.util.function.Predicate, represents a simple function that takes a single value as parameter, and returns true or false. 
 */
public class Pred {
	public static void main(String[] args) {
		// example
		Predicate<Integer> pr = value -> value > 5;
		System.out.println(pr.test(4));

		// as filter
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> collect = list.stream().filter(x -> x > 5).collect(Collectors.toList());
		List<Integer> collect2 = list.stream().filter(pr).collect(Collectors.toList());

		// Multiple Predicates and, or, negate
		Predicate<Integer> greaterThan5 = x -> x > 5;
		Predicate<Integer> lessThan8 = x -> x < 8;

		List<Integer> collection3 = list.stream().filter(greaterThan5.and(lessThan8)).collect(Collectors.toList());

		List<String> stringCollection = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");
		// Chaining
		Predicate<String> startWithA = x -> x.startsWith("a");
		boolean result = startWithA.or(x -> x.startsWith("m")).test("mkyong");
		System.out.println(result);

		Hosting h1 = new Hosting(1, "amazon", "aws.amazon.com");
		Hosting h2 = new Hosting(2, "linode", "linode.com");
		Hosting h3 = new Hosting(3, "liquidweb", "liquidweb.com");
		Hosting h4 = new Hosting(4, "google", "google.com");
		Hosting h5 = new Hosting(2, "linode", "linode.com");

		List<Hosting> listHosting = Arrays.asList(h1, h2, h3, h4, h5);
		Predicate<Hosting> isPredicateHosting = host -> host.getName().equals("linode");
		System.out.println(listHosting.stream().filter(isPredicateHosting).map(Hosting::toString)
				.collect(Collectors.joining(",")));

		Map<Integer, String> HOSTING = new HashMap<>();
		HOSTING.put(1, "linode.com");
		HOSTING.put(2, "heroku.com");
		HOSTING.put(3, "digitalocean.com");
		HOSTING.put(4, "aws.amazon.com");
		HOSTING.put(5, "aws2.amazon.com");
		  //  {1=linode.com}
        Map<Integer, String> filteredMap = filterByValue(HOSTING, x -> x.contains("linode"));
        System.out.println(filteredMap);
        
        Map<Integer, Integer> numbers = new HashMap<>();
        numbers.put(1, 11);
        numbers.put(2, 12);
        numbers.put(3, 13);
        numbers.put(4, 14);
        IntStream.range(0, 10).forEach(i->{
        	numbers.compute(i, (key,val)->(val==null)?1:val-1);
        });
        System.out.println(numbers);
	}

	public static <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
		return map.entrySet()
				.stream()
				.filter(x -> predicate.test(x.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
}
