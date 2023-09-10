package study.ThreadProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Lesson1 {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 5; i++) {
			exec.execute(new Test3());
		}
		exec.shutdown();
	}
}
class Test3 implements Runnable {
	private final Random rand = new Random();
	
	
	public void run() {
		try {
			long start = System.currentTimeMillis();
			TimeUnit.SECONDS.sleep(rand.nextInt(1, 10));
			System.out.println((System.currentTimeMillis() - start) / 1000);
		} catch(InterruptedException err) {
			System.out.println("Interrupted");
		}
	}
}
class Test2 implements Callable<String> {
	private int id;
	
	public Test2(int id) {
		this.id = id;
	}
	public String call() {
		return "HELLO " + id;
	}
}
class Test implements Runnable {
	private static int taskCount = 0;
	private int id = taskCount++;
	
	public Test() {
		System.out.println("TEST #" + id + " STARTING");
	}
	
	public void run() {
		for(int i = 0; i < 3; i++) {
			System.out.println("MSG TEST #" + id + " HELLO");
			Thread.yield();
		}
		System.out.println("TEST #" + id + " ENDING");
	}
}