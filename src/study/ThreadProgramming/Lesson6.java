package study.ThreadProgramming;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Lesson6 {

	public static void main(String[] args) throws InterruptedException {
		CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
			public void run() {
				System.out.println("ENDING...");
			}
		});
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Runnable() {
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
					barrier.await();
					System.out.println("Complete");
				} catch(InterruptedException err) {
					System.out.println("Interrupted");
				} catch(BrokenBarrierException err) {
					
				}
			}
		});
		exec.execute(new Runnable() {
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(4);
					barrier.await();
					System.out.println("Complete");
				} catch(InterruptedException err) {
					System.out.println("Interrupted");
				} catch(BrokenBarrierException err) {
					
				}
			}
		});
		exec.shutdown();
	}
}
class TaskPortion implements Runnable {
	private static int counter = 0;
	private int id = counter++;
	private CountDownLatch cdl;
	private static Random rand = new Random(47);
	
	public TaskPortion(CountDownLatch cdl) {
		this.cdl = cdl;
	}
	public void run() {
		work();
		cdl.countDown();
	}
	private void work() {
		try {
			System.out.println("TASK #" + id + " working...");
			synchronized(this) {
				TimeUnit.SECONDS.sleep(rand.nextInt(10));
			}
		} catch(InterruptedException err) {
			System.out.println("Work Interrupted");
		}
	}
}
class WaitingTask implements Runnable {
	private CountDownLatch cdl;
	
	public WaitingTask(CountDownLatch cdl) {
		this.cdl = cdl;
	}
	public void run() {
		try {
			cdl.await();
			System.out.println("TASK COMPLETE");
		} catch(InterruptedException err) {
			System.out.println("Interrupted");
		}
	}
}

//--------------------
class Sandwich {
	private static int counter = 0;
	private int id = counter++;
	
	enum Status { DEFAULT, BUTTER, JELLY }
	private Status status = Status.DEFAULT;
	public void butter() { status = Status.BUTTER; }
	public void jelly() { status = Status.JELLY; }
	public Status getStatus() { return status; }
	public int id() { return id; }
	public String toString() {
		return "Sandwich #" + id + " Status: " + status;
	}
}
class Kitchen implements Runnable {
	private BlockingQueue<Sandwich> defaultQueue;
	
	Kitchen(BlockingQueue<Sandwich> queue) {
		this.defaultQueue = queue;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				TimeUnit.SECONDS.sleep(1);
				Sandwich sandwich = new Sandwich();
				System.out.println(sandwich);
				defaultQueue.put(sandwich);
			}
		} catch(InterruptedException err) {
			System.out.println("Interrupted");
		}
	}
}
class Butterer implements Runnable {
	private BlockingQueue<Sandwich> defaultQueue;
	private BlockingQueue<Sandwich> buttererQueue;
	
	public Butterer(BlockingQueue<Sandwich> defaultQueue, BlockingQueue<Sandwich> buttererQueue) {
		this.defaultQueue = defaultQueue;
		this.buttererQueue = buttererQueue;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Sandwich sandwich = defaultQueue.take();
				sandwich.butter();
				System.out.println(sandwich);
				buttererQueue.put(sandwich);
			}
		} catch(InterruptedException err) {
			System.out.println("Interrupted");
		}
		System.out.println("Exiting Butterer");
	}
}
class Jellyer implements Runnable {
	private BlockingQueue<Sandwich> jellyerQueue;
	private BlockingQueue<Sandwich> buttererQueue;
	
	public Jellyer(BlockingQueue<Sandwich> buttererQueue, BlockingQueue<Sandwich> jellyerQueue) {
		this.jellyerQueue = jellyerQueue;
		this.buttererQueue = buttererQueue;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Sandwich sandwich = buttererQueue.take();
				sandwich.jelly();
				System.out.println(sandwich);
				jellyerQueue.put(sandwich);
			}
		} catch(InterruptedException err) {
			System.out.println("Interrupted");
		}
		System.out.println("Exiting Jellyer");
	}
}


//----------
class TestBlockingQueue implements Runnable {
	BlockingQueue<String> queue;
	
	TestBlockingQueue(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				System.out.println(queue.take());
			}
		} catch(InterruptedException err) {
			System.out.println("Interrupted");
		}
		System.out.println("Exiting...");
	}
}