package testing;

import java.io.IOException;

public class test1 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int someInt = 10;
		System.out.println(new Info("Granny", 78));
	}
}
interface ISome {
	void someDo();
}
class Info {
	private String name;
	private int age;
	public Info(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Info [name=" + name + ", age=" + age + "]";
	}
	
}





