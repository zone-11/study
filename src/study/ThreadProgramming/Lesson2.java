package study.ThreadProgramming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Lesson2 {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler());
		Generator gen = new Generator();
		exec.execute(new TestThread(gen, false));
		exec.execute(new TestThread(gen, true));
		exec.shutdown();
	}
}
class Generator {
	private boolean testFlag;
	
	public boolean get() { return testFlag; }
	public void set(boolean newFlag) { testFlag = newFlag; } 
}
class TestThread implements Runnable {
	private Generator gen;
	private boolean flag;
	
	public TestThread(Generator gen, boolean flag) {
		this.gen = gen;
		this.flag = flag;
	}
	
	public void run() {
		while(true) {
			if(flag) {
				gen.set(true);
			}
			System.out.println(Thread.currentThread() + " FLAG: " + gen.get());
		}
	}
}
class ThreadExceptionHandler implements
Thread.UncaughtExceptionHandler {
	
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(t + " | ERROR: " + e);
	}
}
class ExceptionThreadFactory implements
ThreadFactory {
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new ThreadExceptionHandler());
		return t;
	}
}
class ExceptionRun implements Runnable {
	public void run() {
		throw new RuntimeException();
	}
}
class DaemonFactory implements ThreadFactory {
	
	public Thread newThread(Runnable runnable) {
		Thread t = new Thread(runnable);
		t.setDaemon(true);
		return t;
	}
}
class CountDown implements Runnable {
	private int priority;
	private int count;
	
	public CountDown(int count, int priority) {
		this.count = count;
		this.priority = priority;
	}
	
	public void run() {
		Thread.currentThread().setPriority(priority);
		for(int i = 0; i < count; i++) {
			System.out.println("THREAD: " + Thread.currentThread().toString() +
					"\n COUNT: " + i + ", IS_DAEMON: " + Thread.currentThread().isDaemon());
		}
	}
}