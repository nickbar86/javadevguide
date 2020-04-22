package binary0perator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
/*The Java BinaryOperator interface is a functional interface that represents an operation which takes two parameters and returns a single value. Both parameters and the return type must be of the same type.
 *
 */
public class BinaryOp {
	public static void main(String[] args) {
		BinaryOperator<Integer> add = (value1, value2) -> {
			return value1 + value2;
		};
		List<Integer> integers = new ArrayList<>(Arrays.asList(1,2,3,4));
		int result =integers.stream().reduce(0, add);
		int result2=integers.stream().reduce(0, Integer::sum);
		System.out.println(result);
		System.out.println(result2);
		
		
		
		Developer dev1 = new Developer("jordan", BigDecimal.valueOf(9999));
        Developer dev2 = new Developer("jack", BigDecimal.valueOf(8888));
        Developer dev3 = new Developer("jaden", BigDecimal.valueOf(10000));
        Developer dev4 = new Developer("ali", BigDecimal.valueOf(2000));
        Developer dev5 = new Developer("mkyong", BigDecimal.valueOf(1));
        
        List<Developer> list = Arrays.asList(dev1, dev2, dev3, dev4, dev5);
        
        Comparator<Developer> comparing = Comparator.comparing(Developer::getSalary);
        BinaryOperator<Developer> bo = BinaryOperator.maxBy(comparing);
        BinaryOperator<Developer> bo2 = (devv1,devv2)->{
        	return new Developer(devv1.getName()+","+devv2.getName(), devv1.salary.add(devv2.getSalary()));
        };
        System.out.println(bo2.apply(dev1, dev2));
        System.out.println(list.stream().max(comparing));
        System.out.println(list.stream().reduce(bo));
	}
}
