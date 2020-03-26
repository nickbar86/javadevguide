/*Benefits:
It describes the coupling between the objects and the observer.
It provides the support for broadcast-type communication.
Usage:
When the change of a state in one object must be reflected in another object without keeping the objects tight coupled.
When the framework we writes and needs to be enhanced in future with new observers with minimal chamges.
*/
public class ObserverPatternDemo {
    public static void main(String[] args) {
        System.out.println("Enter Text >");
 
        // create an event source - reads from stdin
        final EventSource eventSource = new EventSource();
 
        // create first observer
        final ResponseHandler1 responseHandler1 = new ResponseHandler1();
 
        // subscribe the observer to the event source
        eventSource.addObserver(responseHandler1);
        
        
        // create second observer
        final ResponseHandler2 responseHandler2 = new ResponseHandler2();
 
        // subscribe the observer to the event source
        eventSource.addObserver(responseHandler2);
 
        // starts the event thread
        Thread thread = new Thread(eventSource);
        thread.start();
    }
}
