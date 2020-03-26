package withMethodName;

public class Main {
	public static void main(String args[]) 
    { 
        // instantiating Singleton class with variable x 
        Singleton x = Singleton.Singleton(); 
  
        // instantiating Singleton class with variable y 
        Singleton y = Singleton.Singleton(); 
  
        // instantiating Singleton class with variable z 
        Singleton z = Singleton.Singleton(); 
  
        // changing variable of instance x 
        x.s = (x.s).toUpperCase(); 
  
        System.out.println("String from x is " + x.s); 
        System.out.println("String from y is " + y.s); 
        System.out.println("String from z is " + z.s); 
        System.out.println("\n"); 
  
        // changing variable of instance x 
        z.s = (z.s).toLowerCase(); 
  
        System.out.println("String from x is " + x.s); 
        System.out.println("String from y is " + y.s); 
        System.out.println("String from z is " + z.s); 
    } 
}
