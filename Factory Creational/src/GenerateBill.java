import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*Advantage of Factory Design Pattern
Factory Method Pattern allows the sub-classes to choose the type of objects to create.
It promotes the loose-coupling by eliminating the need to bind application-specific classes into the code. That means the code interacts solely with the resultant interface or abstract class, so that it will work with any classes that implement that interface or that extends that abstract class.
Usage of Factory Design Pattern
When a class doesn't know what sub-classes will be required to create
When a class wants that its sub-classes specify the objects to be created.
When the parent classes choose the creation of objects to its sub-classes.*/
public class GenerateBill {
	public static void main(String args[]) throws IOException {
		GetPlanFactory planFactory = new GetPlanFactory();

		System.out.print("Enter the name of plan for which the bill will be generated: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String planName = br.readLine();
		System.out.print("Enter the number of units for bill will be calculated: ");
		int units = Integer.parseInt(br.readLine());

		Plan p = planFactory.getPlan(planName);
		// call getRate() method and calculateBill()method of DomesticPaln.

		System.out.print("Bill amount for " + planName + " of  " + units + " units is: ");
		p.calculateBill(units);
	}
}
