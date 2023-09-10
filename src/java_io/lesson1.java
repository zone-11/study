package java_io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.*;

public class lesson1 {

	public static void main(final String[] args) {
		File file = new File("C:\\projects\\test_package");
		Handler handler = new Handler(Pattern.compile(".*\\.txt"));
		DirSortedList.TreeInfo tree = 
		DirSortedList.TreeInfo.walk(file.getAbsolutePath(), handler);
		System.out.println(handler.getFiles().toString());
		System.out.println(file.lastModified());
		Date date = new Date(file.lastModified());
	}

}
class Handler implements DirSortedList.TreeInfo.FileHandler{
	private Pattern pattern;
	private List<File> needFiles = new ArrayList<>();
	
	public Handler(Pattern pattern) {
		this.pattern = pattern;
	}
	public String handleFile(File file) {
		if(pattern.matcher(file.getName()).matches()) {
			needFiles.add(file);
		}
		
		return "";
	}
	public List<File> getFiles() { return needFiles; }
}
class DirSortedList {
	private File file;
	
	public DirSortedList(String filePath) {
		file = new File(filePath);
	}
	public String[] list() {
		return file.list();
	}
	public String[] list(final String regex) {
		return file.list(new FilenameFilter() {
			private Pattern pattern = Pattern.compile(regex);
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		});
	}
	public String info() {
		return "Can Read: " + file.canRead() + "\n" +
				"Can Write: " + file.canWrite() + "\n" +
				"Name: " + file.getName() + "\n" +
				"Path: " + file.getAbsolutePath() + "\n" +
				"Parent: " + file.getParent() + "\n" + 
				"Length: " + file.length() + "\n" +
				"Last Modified: " + file.lastModified() + "\n";
	}
	public static class TreeInfo {
		List<File> files = new ArrayList<>();
		List<File> dirs = new ArrayList<>();
		public List<String> toTerminal = new ArrayList<>();
		public void addAll(TreeInfo other) {
			files.addAll(other.files);
			dirs.addAll(other.dirs);
			toTerminal.addAll(other.toTerminal);
		}
		public void addToTerminal(FileHandler handler, File file) {
			toTerminal.add(handler.handleFile(file)+"\n");
		}
		public interface FileHandler {
			String handleFile(File file);
		}
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Dirs:\n");
			for(File file : dirs) {
				builder.append("-" + file.getAbsoluteFile() + "\n");
			}
			builder.append("Files:\n");
			for(File file : files) {
				builder.append("-" + file.getName() + "\n");
			}
			return builder.toString();
		}
		public String terminal() {
			StringBuilder builder = new StringBuilder();
			for(String str : toTerminal) {
				builder.append(str);
			}
			return builder.toString();
		}
		public static TreeInfo walk(String path, String regex) {
			return recurseWalk(new File(path), regex);
		}
		public static TreeInfo walk(String path, FileHandler handler) {
			return recurseWalkWithHandle(new File(path), handler);
		}
		private static TreeInfo recurseWalk(File file, String regex) {
			TreeInfo result = new TreeInfo();
			if(file.isDirectory()) {
				for(int i = 0; i < file.listFiles().length; i++) {
					result.dirs.add(file);
					result.addAll(recurseWalk(file.listFiles()[i], regex));
				}
			} else if(file.isFile() && file.getName().matches(regex)) {
				result.files.add(file);
			}
			return result;
		}
		private static TreeInfo recurseWalkWithHandle(File file, FileHandler handler) {
			TreeInfo result = new TreeInfo();
			if(file.isDirectory()) {
				for(int i = 0; i < file.listFiles().length; i++) {
					result.dirs.add(file);
					result.addAll(recurseWalkWithHandle(file.listFiles()[i], handler));
				}
			} else if(file.isFile()) {
				result.files.add(file);
				result.addToTerminal(handler, file);
			}
			return result;
		}
	}
}
