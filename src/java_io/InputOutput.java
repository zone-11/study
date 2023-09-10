package java_io;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class InputOutput {

	public static void main(String[] args) {
		
	}

}
class BufferedFileReader {
	public static String read(String path) throws IOException {
		BufferedReader bufReader = new BufferedReader(new FileReader(path));
		LinkedList<String> lines = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = bufReader.readLine())!= null) {
			lines.add(line.toUpperCase());
		}
		bufReader.close();
		ListIterator<String> iterator = lines.listIterator(lines.size());
		while(iterator.hasPrevious()) {
			sb.append(iterator.previous()+"\n");
		}
		return sb.toString();
	}
}