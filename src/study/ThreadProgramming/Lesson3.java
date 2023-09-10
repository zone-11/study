package study.ThreadProgramming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lesson3 {

	public static void main(String[] args) {
		TestSync testSync = new TestSync();
		ObjectSyncTest objTest = new ObjectSyncTest();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Runnable() {
			public void run() {
				objTest.f1();
			}
		});
		exec.execute(new Runnable() {
			public void run() {
				objTest.f2();
			}
		});
		objTest.f3();
	}

}
class ObjectSyncTest {
	Object obj1 = new Object();
	Object obj2 = new Object();
	
	ReentrantLock rLock = new ReentrantLock();
	public void f1() {
		boolean isLock = false;
		try {
			isLock = rLock.tryLock();
			TimeUnit.SECONDS.sleep(5);
		} catch(Exception err) {
			System.out.println("ERROR: " + err.getMessage());
		} finally {
			if(isLock) {
				rLock.unlock();
				System.out.println("UNLOCKED");
			} else {
				System.out.println("CANT IT IS LOCK");
			}
		}
	}
	public void f2() {
		boolean isLock = false;
		try {
			isLock = rLock.tryLock();
		} catch(Exception err) {
			System.out.println("ERROR: " + err.getMessage());
		} finally {
			if(isLock) {
				rLock.unlock();
				System.out.println("UNLOCKED");
			} else {
				System.out.println("CANT IT IS LOCK");
			}
		}
	}
	public void f3() {
		boolean isLock = false;
		try {
			isLock = rLock.tryLock();
		} catch(Exception err) {
			System.out.println("ERROR: " + err.getMessage());
		} finally {
			if(isLock) {
				rLock.unlock();
				System.out.println("UNLOCKED");
			} else {
				System.out.println("CANT IT IS LOCK");
			}
		}
	}
}
class TestSyncThread implements Runnable {
	private TestSync testSync;
	
	public TestSyncThread(TestSync testSync) {
		this.testSync = testSync;
	}
	
	public void run() {
		while(true) {
			testSync.add();
		}
	}
}
class TestSync {
	private int val1 = 0;
	private int val2 = 0;
	
	private ReentrantLock lock = new ReentrantLock();
	
	public void add() {
		synchronized(this) {
			val1++;
			val2++;
			System.out.println("FIRST: " + val1);
			System.out.println("SECOND: " + val2);
		}
		Thread.yield();
		System.out.println("COMPLETE");
	}
	public synchronized int getVal1() { return val1; }
	public synchronized int getVal2() { return val2; }
}