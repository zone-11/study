package java_io;

import java.io.*;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class NewInputOutput2 {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\projects\\test_package\\NIO.txt");
		FileOutputStream stream = new FileOutputStream(file);
		FileChannel channel = stream.getChannel();
		FileLock lock = channel.lock();
		FileChannel channel2 = stream.getChannel();
		
		channel.write(ByteBuffer.wrap("SHUU".getBytes()));
	}
}

class SecretFile {
	public static void writeSecret(String txt, File file) throws IOException{
		FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(txt.toCharArray().length * 2);
		buffer.asCharBuffer().put(txt);
		secret(buffer.asCharBuffer());
		channel.position(channel.size());
		channel.write(buffer);
		channel.close();
	}
	public static String secret(CharBuffer charBuffer) {
		while(charBuffer.position() + 2 <= charBuffer.limit()) {
			charBuffer.mark();
			char c1 = charBuffer.get();
			char c2 = charBuffer.get();
			charBuffer.reset();
			charBuffer.put(c2).put(c1);
		}
		charBuffer.rewind();
		return charBuffer.toString();
	}
	public static String readSecret(File file) throws IOException {
		FileChannel channel = new FileInputStream(file).getChannel();
		ByteBuffer buffer = ByteBuffer.allocate((int)file.length() * 2);
		channel.read(buffer);
		channel.close();
		buffer.flip();
		return secret(buffer.asCharBuffer());
	}
}