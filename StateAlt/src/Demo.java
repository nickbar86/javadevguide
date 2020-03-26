
public class Demo {
	public static void main(String[] args) {
		Case inv = new Case();
		inv.setWaitingForQuotation();
		inv.showMeAMessage();
		inv.setWaitingForInvoice();
		inv.showMeAMessage();
		inv.setWaitingForDeliver();
		inv.showMeAMessage();
	}
}
