package java_io;

import java.io.*;

public class InputOutput2 {
	public static void main(String[] args) throws IOException{
		File file = new File("C:\\projects\\test_package\\Mag.png");
		DataOutputStream stream = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)));
		stream.writeUTF("SUPER NIGER TURTLES YSE");
		stream.writeInt(123);
		stream.writeDouble(1.3);
		stream.close();
		DataInputStream stream2 = new DataInputStream(
				new BufferedInputStream(new FileInputStream(file)));
		System.out.println(stream2.readUTF());
		System.out.println(stream2.readInt());
		stream2.close();
	}
}
