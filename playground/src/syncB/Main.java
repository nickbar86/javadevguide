package syncB;

import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		SynchronizedBlocks block = new SynchronizedBlocks();
		SynchronizedBlocks block2 = new SynchronizedBlocks();
		Thread threadA = new Thread(new Runnable() {

			@Override
			public void run() {
				IntStream.range(0, 10000000).forEach(i -> {
					block.performSynchronisedTask();
					block2.performSynchronisedTask();
				});
				System.out.println("Thread A1:"+block.getCount()+"  A2:"+block2.getCount());
			}
			
		});
		Thread threadB = new Thread(new Runnable() {

			@Override
			public void run() {
				IntStream.range(0, 10000000).forEach(i -> {
					block.performSynchronisedTask();
					block2.performSynchronisedTask();
				});
				System.out.println("Thread B1:"+block.getCount()+"  B2:"+block2.getCount());
			}
		});
		
		threadA.start();
		threadB.start();

		
		Thread threadC = new Thread(new Runnable() {

			@Override
			public void run() {
				IntStream.range(0, 10000000).forEach(i -> {
					SynchronizedBlocks.performStaticSyncTask();
				});
				System.out.println("Thread C"+SynchronizedBlocks.getStaticCount());
			}
			
		});
		Thread threadD = new Thread(new Runnable() {

			@Override
			public void run() {
				IntStream.range(0, 10000000).forEach(i -> {
					SynchronizedBlocks.performStaticSyncTask();
				});
				System.out.println("Thread D"+SynchronizedBlocks.getStaticCount());
			}
		});
		
		
		threadC.start();
		threadD.start();
		
	}
}
