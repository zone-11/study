package java_io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class InputOutput3 {

	public static void main(String[] args) throws IOException{
		PrintStream ps = new PrintStream(
				new BufferedOutputStream(new FileOutputStream("C:\\projects\\test_package\\test2.txt")));
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		System.setOut(ps);
		
		String s;
		while((s = br.readLine()) != null && s.length() != 0) {
			System.out.println(s);
		}
		ps.close();
	}

}
class BinaryFile {
	public static byte[] read(File bFile) throws IOException {
		BufferedInputStream stream = new BufferedInputStream(
				new FileInputStream(bFile));
		try {
			byte[] data = new byte[stream.available()];
			stream.read(data);
			return data;
		}finally {
			stream.close();
		}
	}
	public static byte[] read(String path) throws IOException {
		return read(new File(path).getAbsoluteFile());
	}
}
class TextFile {
	private final File file;
	private HashMap<Character, Integer> symbols = new HashMap<>();
	
	public TextFile(String path) {
		file = new File(path);
	}
	public String read() throws IOException{
		StringBuilder builder = new StringBuilder();
		
		DataInputStream stream = new DataInputStream(
				new BufferedInputStream(new FileInputStream(file)));
		String s;
		while(stream.available() != 0) {
			char symbol = (char)stream.readByte();
			symbols.put(symbol, symbols.get(symbol) == null ? 1 : symbols.get(symbol) + 1 );
			builder.append(symbol);
		}
		stream.close();
		return builder.toString();
	}
	public String symobolsInfo() {
		return symbols.toString();
	}
	public void write(String toWrite) throws IOException{
		String txt = read();
		PrintWriter stream = new PrintWriter(file);
		stream.print(txt + toWrite);
		stream.close();
	}
	public void clear() throws IOException{
		PrintWriter stream = new PrintWriter(file);
		stream.print("");
		stream.close();
	}
}