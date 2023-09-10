package study.ThreadProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Exp35 {

	public static void main(String[] args) {
		BlockingQueue<User> users = new LinkedBlockingQueue<>();
		for(int i = 0; i < 100; i++) {
			users.add(new User());
		}
		Thread thread2 = new Thread(new ServerManager(3, users));
		thread2.start();
	}
}
class User {
	private static int counter = 0;
	private final int id = counter++;
	private final String name;
	private final Random rand = new Random();
	
	public User() {
		name = "User #" + id;
	}
	public void executeRequest() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch(InterruptedException err) {
			System.out.println(this + " request was interrupted");
		} catch(Exception ex) {
			System.out.println("Exception here");
		}
	}
	public String toString() { return name; }
}
class UserGenerator implements Runnable {
	private BlockingQueue<User> bQueue;
	private final Random rand = new Random();
	
	public UserGenerator(BlockingQueue<User> bQueue) {
		this.bQueue = bQueue;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				TimeUnit.SECONDS.sleep(1);
				bQueue.add(new User());
			}
		} catch(InterruptedException err) {
			System.out.println("User generator was interrupted");
		}
	}
}
class UserRequestHandler implements Callable<Integer> {
	private static int counter = 0;
	private int id = counter++;
	private User user;
	
	public UserRequestHandler(User user) {
		this.user = user;
	}
	
	public Integer call() {
		System.out.println("Executing request " + user + " in Handler #" + id);
		user.executeRequest();
		System.out.println(user + " requst was executed");
		return 1;
	}
}
class Server implements Runnable, Comparable<Server> {
	private static int counter = 0;
	private int id = counter++;
	private ExecutorService exec = Executors.newFixedThreadPool(10);
	private List<Future<Integer>> activeUsers = new ArrayList<>();
	
	public void run() {
			while(!Thread.interrupted()) {
				for(int i = 0; i < activeUsers.size(); i++) {
					if(activeUsers.get(i).isDone()) {
						activeUsers.remove(i);
						System.out.println("User disconnected from " + this);
						System.out.println("Active users: " + activeUsers.size() + "\n");
					}
				}
			}
			System.out.println("Exiting from server...");
	}
	public synchronized int compareTo(Server server) {
		return getActiveUsers() < server.getActiveUsers() ? -1 :
			(getActiveUsers() == server.getActiveUsers() ? 0 : 1);
	}
	public synchronized void connect(User user) {
		System.out.println(user + " connects to " + this);
		Future<Integer> future = exec.submit(new UserRequestHandler(user));
		activeUsers.add(future);
		System.out.println("Active users: " + activeUsers.size());
	}
	public synchronized int getActiveUsers() {
		return activeUsers.size();
	}
	public synchronized boolean canConnect() {
		return activeUsers.size() >= 10 ? false : true;
	}
	public String toString() {
		return "Server #" + id;
	}
}
class ServerManager implements Runnable {
	private PriorityBlockingQueue<Server> servers = new PriorityBlockingQueue<>();
	private BlockingQueue<User> users;
	private ExecutorService exec;
	
	public ServerManager(int quaServers, BlockingQueue<User> users) {
		for(int i = 0; i < quaServers; i++) {
			servers.add(new Server());
		}
		this.users = users;
		exec = Executors.newFixedThreadPool(quaServers);
	}
	
	public void run() {
		try {
			synchronized(this) {
				for(Server server : servers) {
					exec.execute(server);
				}
			}
			while(!Thread.interrupted()) {
				Server server = servers.take();
				servers.add(server);
				if(server.canConnect()) {
					server.connect(users.take());
				} else {
//					System.out.println("Server Overloaded!!!");
				}
			}
		} catch(InterruptedException err) {
			System.out.println("Servers interrupted");
		}
	}
}