package study.ThreadProgramming;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class Lesson8 {
	public static void main(String[] args) throws InterruptedException  {
		new ReaderWriterMapTest(2000, 1000);
		
	}
}
class ReaderWriterMap<T, M> {
	private Map<T, M> lockedMap = new ConcurrentHashMap<T, M>();
	
	public void put(T key, M value) {
		lockedMap.put(key, value);
	}
	public M get(T key) {
		return lockedMap.get(key);
	}
}
class ReaderWriterMapTest {
	private ReaderWriterMap<Integer, Integer> map =
			new ReaderWriterMap<>();
	private ExecutorService exec = Executors.newCachedThreadPool();
	private Random rand = new Random(47);
	public class Reader implements Runnable {
		public void run() {
			try {
				for(int i = 0; i < 20; i++) {
					System.out.println(map.get(i));
				}
			} catch(Exception err) {
				System.out.println("Reader interrupted");
			}
		}
	}
	public class Writer implements Runnable {
		public void run() {
			try {
				for(int i = 0; i < 20; i++) {
					map.put(i, rand.nextInt());
				}
			} catch(Exception err) {
				System.out.println("Reader interrupted");
			}
		}
	}
	public ReaderWriterMapTest(int readers, int writers) {
		for(int i = 0; i < writers; i++) {
			exec.execute(new Writer());
		}
		for(int i = 0; i < readers; i++) {
			exec.execute(new Reader());
		}
		exec.shutdown();
	}
}




class FastSimulation {
	static final int N_ELEMENTS = 100000;
	static final int N_GENES = 30;
	static final int N_EVOLVERS = 50;
	static final int[][] GRID =
	new int[N_ELEMENTS][N_GENES];
	static Random rand = new Random(47);
	private static ReentrantLock lock = new ReentrantLock();
	static class Evolver implements Runnable {
		public void run() {
			while(!Thread.interrupted()) {
				if(!lock.tryLock()) continue;
				int element = rand.nextInt(N_ELEMENTS);
				for(int i = 0; i < N_GENES; i++) {
					int previous = element - 1;
					if(previous < 0) previous = N_ELEMENTS - 1;
					int next = element + 1;
					if(next >= N_ELEMENTS) next = 0;
					int oldvalue = GRID[element][i];
					int newvalue = oldvalue +
							GRID[previous][i] + GRID[next][i];
					newvalue /= 3;
					GRID[element][i] = newvalue;
					if(oldvalue > newvalue) {
						System.out.println(oldvalue);
					}
				}
				lock.unlock();
			}
		}
	}
	public static void start() throws InterruptedException{
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < N_ELEMENTS; i++) {
			for(int j = 0; j < N_GENES; j++) {
				GRID[i][j] = rand.nextInt(1000);
			}
		}
		for(int i = 0; i < N_EVOLVERS; i++) {
			exec.execute(new Evolver());
		}
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}
