
abstract class Plan {
	protected double rate;  
    protected abstract void setRate();  

    public void calculateBill(int units){  
         System.out.println(units*rate);  
     }  
}
