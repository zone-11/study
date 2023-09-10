package java_io;

import java.io.File;
import java.nio.file.Path;

public class lesson2 {

	public static void main(String[] args) {
		Directory dir = new Directory("C:\\projects\\test_package\\toAdd");
		dir.fill(new TextFileGenerator() {
			private int count;
			public File next() {
				count++;
				return new File(count+"");
			}
		}, 10);
	}

}
class Directory {
	private File dir;
	
	public Directory(String path)throws RuntimeException {
		File file = new File(path);
		if(file.isFile())throw new RuntimeException();
		this.dir = file;
	}
	public void deleteAll() {
		for(File file : dir.listFiles()) {
			file.delete();
		}
	}
	public void fill(TextFileGenerator generator, int times) {
		for(int i = 0; i < times; i++) {
			File newFile = new File(dir.getAbsolutePath()+"\\"+generator.next().getName());
			newFile.mkdirs();
		}
	}
	public void renameAll(String newName) {
	
	}
}
interface TextFileGenerator {
	File next();
}
