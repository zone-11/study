package java_io;

import java.io.*;
import java.util.zip.*;

public class NewInputOutput3 {
	public static void main(String[] args) throws IOException {
		File file1 = new File("C:\\projects\\test_package\\test5.txt");
		File file2 = new File("C:\\projects\\test_package\\test6.zip");
		
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(file2));
		BufferedOutputStream out = new BufferedOutputStream(zos);
		DataInputStream in = new DataInputStream(
				new BufferedInputStream(new FileInputStream(file1)));
		ZipEntry toZip = new ZipEntry(file1.getName());
		toZip.setComment("DOG SHEET");
		zos.putNextEntry(toZip);
		while(in.available() != 0) {
			out.write(in.read());
		}
		in.close();
		out.close();
		ZipInputStream zin = new ZipInputStream(
				new FileInputStream(file2));
		BufferedReader inZ = new BufferedReader(
				new InputStreamReader(zin));
		ZipEntry entry;
		while((entry = zin.getNextEntry()) != null) {
			System.out.println("ENTRY: " + entry.getName());
			System.out.println("COMMENT: " + entry.getComment());
			String s;
			while((s = inZ.readLine()) != null) {
				System.out.println(s);
			}
		}
	}
	public static void writeToGZIP(String text, File file) throws IOException {
		DataOutputStream out = new DataOutputStream (
				new GZIPOutputStream(new FileOutputStream(file)));
		out.write(text.getBytes());
		out.close();
	}
	public static String readFromGZIP(File file) throws IOException {
		BufferedReader in = new BufferedReader(
				new InputStreamReader(new GZIPInputStream(
						new FileInputStream(file))));
		StringBuilder builder = new StringBuilder();
		String s;
		while((s = in.readLine()) != null) {
			builder.append(s);
		}
		return builder.toString();
	}
}