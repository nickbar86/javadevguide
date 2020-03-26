/*The main advantages of Builder Pattern are as follows:

It provides clear separation between the construction and representation of an object.
It provides better control over construction process.
It supports to change the internal representation of objects.*/
public class BuilderDemo {
	public static void main(String args[]) {
		CDBuilder cdBuilder = new CDBuilder();
		CDType cdType1 = cdBuilder.buildSonyCD();
		cdType1.showItems();

		CDType cdType2 = cdBuilder.buildSamsungCD();
		cdType2.showItems();
	}
}
