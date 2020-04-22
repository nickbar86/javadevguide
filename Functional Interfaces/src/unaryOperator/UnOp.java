package unaryOperator;
import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import binary0perator.Developer;

/*
 * The Java UnaryOperator interface is a functional interface that represents an operation which takes a single parameter and returns a parameter of the same type. 
 */
public class UnOp {
	public static void main(String[] args){
		UnaryOperator<String> unOp = str->str+" add";
		System.out.println(unOp.apply("hi"));
		
		UnaryOperator<Integer> add2 = x->x+2;
		System.out.println(add2.apply(10));
		
		Developer dev1 = new Developer("jordan", BigDecimal.valueOf(9999));
        Developer dev2 = new Developer("jack", BigDecimal.valueOf(8888));
        Developer dev3 = new Developer("jaden", BigDecimal.valueOf(10000));
        Developer dev4 = new Developer("ali", BigDecimal.valueOf(2000));
        Developer dev5 = new Developer("mkyong", BigDecimal.valueOf(1));
        
        Map<Integer,Developer> map = new HashMap<>();
        map.put(1,dev1);
        map.put(2,dev2);
        map.put(3,dev3);
        map.put(4,dev4);
        map.put(5,dev5);
        
        List<Developer> list = Arrays.asList(dev1, dev2, dev3, dev4, dev5);
        List<String> list222 = Arrays.asList(args);
        
        UnaryOperator<Developer> salaryIncBy10Perc = dev -> new Developer(dev.getName(),dev.getSalary().add(dev.getSalary().multiply(new BigDecimal(0.1))));
        UnaryOperator<Entry<Integer,Developer>> salaryIncBy10PercEntry = entr -> new AbstractMap.SimpleEntry(entr.getKey(), new Developer(entr.getValue().getName(),entr.getValue().getSalary().add(entr.getValue().getSalary().multiply(new BigDecimal(0.1)))));
        //System.out.println(list.stream().map(salaryIncBy10Perc).map(Developer::toString).collect(Collectors.joining(",\n")));
        String result = map.entrySet().stream().map(salaryIncBy10PercEntry).map(entr->entr.getValue().toString()).collect(Collectors.joining(",\n"));
        System.out.println(result);
	}
	
}
