package java_io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;

public class NewInputOutput1 {
	public static final int BSIZE = 1024;
	public static void  main(String[] args) throws IOException{
		File file = new File("C:\\projects\\test_package\\test_8x8.png");
		File file2 = new File("C:\\projects\\test_package\\NIOCOPY.png");
		FileChannel
			in = new FileInputStream(file).getChannel(),
			out = new FileOutputStream(file2).getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(3);
		in.read(buffer);
		buffer.flip();
		int i = 0;
		while(buffer.hasRemaining()) {
			i++;
			System.out.println(buffer.get());
		}
		System.out.println("SIZE: " + i);
	}
}
