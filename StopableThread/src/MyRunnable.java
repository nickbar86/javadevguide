
public class MyRunnable implements Runnable{
	private boolean doStop = false;

	public synchronized void doStop(){
		doStop=true;
	}
	
	public synchronized boolean keepRunning(){
		return doStop == false;
	}
	
	@Override
	public void run() {
		while(keepRunning()) {
            // keep doing what this thread should do.
            System.out.println("Running");

            try {
                Thread.sleep(3L * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
	}

}
