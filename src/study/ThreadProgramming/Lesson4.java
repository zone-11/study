package study.ThreadProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Lesson4 {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<?> toCancel = exec.submit(new TestProblem());
		TimeUnit.SECONDS.sleep(1);
		toCancel.cancel(true);
		exec.shutdown();
	}
}
class TestProblem implements Runnable {
	private Problem problem = new Problem();
	
	public void run() {
		problem.test();
	}
}
class Problem {
	
	public void test() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch(InterruptedException err) {
			System.out.println("Problem has interrupted");
		}
		System.out.println("Exiting problem");
	}
}


//---------------------------------------------
class RadiationThread implements Runnable {
	private RadiationCounter counter;
	private RadiationTrigger test;
	
	public RadiationThread(RadiationCounter counter) {
		this.counter = counter;
	}
	public RadiationThread(RadiationCounter counter, RadiationTrigger trigger) {
		this(counter);
		test = trigger;
	}
	
	public void run() {
		System.out.println("TRIGGER_RADIATION: " + test.info());
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch(InterruptedException  err) {
			System.out.println("Interrupted");
		}
	}
}
class RadiationCounter {
	private ArrayList<Triggered> triggers = new ArrayList<>();
	
	public RadiationCounter(Triggered...triggers) {
		this.triggers.addAll(Arrays.asList(triggers));
	}
	public synchronized void connectTrigger(Triggered trigger) {
		triggers.add(trigger);
	}
	public synchronized int totalRadiation() {
		int i = 0;
		for(Triggered trigger : triggers) {
			i += trigger.info();
		}
		return i;
	}
}
class RadiationTrigger implements Triggered {
	private final int radiation = 10;
	
	public synchronized int info() {
		System.out.println("TRIGGER: " + this + ", RADIATION: " + radiation);
		return radiation;
	}
}
interface Triggered {
	int info();
}