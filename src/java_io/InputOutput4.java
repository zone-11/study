package java_io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class InputOutput4 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File file = new File("C:\\projects\\test_package\\test8.txt");
		
//		ObjectOutputStream out = new ObjectOutputStream(
//				new FileOutputStream(file));
//		out.writeObject(new SerTest("text", 100));
//		SerTest.serializeStaticState(out);
//		out.close();
		
		ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(file));
		SerTest serTest = (SerTest)in.readObject();
		SerTest.deserializeStaticState(in);
		in.close();
		System.out.println(SerTest.staticString);
	}
}
class SerTest implements Serializable {
	String text;
	int integer;
	static String staticString;
	
	public SerTest(String text, int integer) {
		this.text = text;
		this.integer = integer;
		staticString = text;
	}
	public String getText() { return text; }
	public int getInt() { return integer; }
	static String getStaticString() { return staticString; }
	
	public static void serializeStaticState(ObjectOutputStream out)
			throws IOException {
		out.writeUTF(staticString);
	}
	public static void deserializeStaticState(ObjectInputStream in)
			throws IOException {
		staticString = in.readUTF();
	}
}
