package study.ThreadProgramming;

import java.util.PriorityQueue;
import java.util.concurrent.*;

public class Lesson7 {

	public static void main(String[] args) {
		PriorityQueue<String> queue = new PriorityQueue<>();
	}
}
class RunnerTest implements Runnable {
	private BlockingQueue<String> queue;
	public RunnerTest(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			String text = queue.take();
			System.out.println("COMPLETE");
		} catch(InterruptedException err) {
			System.out.println("Interrupted");
		}
	}
}
