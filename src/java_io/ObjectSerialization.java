package java_io;

import java.io.*;

public class ObjectSerialization {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File file = new File("C:\\projects\\test_package\\test7.out");
//		ObjectOutputStream out = new ObjectOutputStream(
//				new FileOutputStream(file));
//		Libriary<String> lib = new Libriary<>("HELLO");
		ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(file));
		Libriary<String> lib = (Libriary<String>)in.readObject();
		System.out.println(lib.get());
	}

}
class Libriary<T> implements Serializable {
	private T obj;
	
	Libriary() {
		
	}
	Libriary(T obj) {
		this.obj = obj;
	}
	public T get() { return obj; }
}