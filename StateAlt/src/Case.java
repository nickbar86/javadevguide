
public class Case {
	public static WaitingForDelivery wfd;
	public static WaitingForInvoice wfi;
	public static WaitingForQuotation  wfq;
	
	private static State state;
	Case(){
		wfd = new WaitingForDelivery();
		wfi = new WaitingForInvoice();
		wfq = new WaitingForQuotation();
	}
	
	public void setWaitingForDeliver(){
		state=wfd;
	}
	
	public void setWaitingForInvoice(){
		state=wfi;
	}
	
	public void setWaitingForQuotation(){
		state=wfq;
	}
	
	public void showMeAMessage(){
		state.showMeAMessage();
	}
}
