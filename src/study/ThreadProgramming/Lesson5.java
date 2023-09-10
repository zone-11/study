package study.ThreadProgramming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lesson5 {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		TestHandShaking ths = new TestHandShaking();
		
		exec.execute(new Run1(ths));
		exec.execute(new Run2(ths));
		TimeUnit.SECONDS.sleep(2);
		exec.shutdownNow();
	}

}
class Run1 implements Runnable {
	private TestHandShaking ths;
	
	Run1(TestHandShaking ths) {
		this.ths = ths;
	}
	public void run() {
		try {
			
			while(!Thread.interrupted()) {
				ths.f1();
				ths.waitForF2();
			}
		} catch(InterruptedException err) {
			System.out.println("Interrupted");
		}
		System.out.println("EXITING FROM RUN1");
	}
}
class Run2 implements Runnable {
	private TestHandShaking ths;
	
	Run2(TestHandShaking ths) {
		this.ths = ths;
	}
	public void run() {
		try {
			
			while(!Thread.interrupted()) {
				ths.waitForF1();
				ths.f2();
			}
		} catch(InterruptedException err) {
			System.out.println("Interrupted");
		}
		System.out.println("EXITING FROM RUN2");
	}
}
class TestHandShaking {
	private boolean flag = false;
	
	synchronized void f1() throws InterruptedException {
		flag = true;
		System.out.println("FLAG: " + flag);
		notify();
	}
	synchronized void waitForF2() throws InterruptedException {
		while(flag == true) {
			wait();
		}
	}
	synchronized void waitForF1() throws InterruptedException {
		while(flag == false) {
			wait();
		}
	}
	synchronized void f2() throws InterruptedException {
		flag = false;
		notify();
		System.out.println("FLAG: " + flag);
	}
}
class RunTest1 implements Runnable {
	
	public void run() {
		try {
			synchronized(this) {
				wait();
			}
			System.out.println("EXIT FROM WAIT");
		} catch(InterruptedException errr) {
			System.out.println("Interrupted");
		}
	}
}
class RunTest2 implements Runnable {
	private RunTest1 rt1;;
	
	public RunTest2(RunTest1 rt1) {
		this.rt1 = rt1;
	}
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(5);
			synchronized(rt1) {
				rt1.notifyAll();
			}
		} catch(InterruptedException err) {
			System.out.println("Interrupted");
		}
	}
}