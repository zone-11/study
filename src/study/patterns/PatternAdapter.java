package study.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class PatternAdapter {

	public static void main(String[] args) {
		StringTest stringTest = new StringTest();
		IntegerTest integerTest = new IntegerTest();
		List<Float> floatList = Arrays.<Float>asList(1f, 43f);;
		System.out.println(Functional.some(stringTest));
		System.out.println(Functional.some(integerTest));
		System.out.println(Functional.some(new CollectorAdapter<Float>(floatList)));
	}

}
class CollectorAdapter<T> implements Collector<String>{
	Collection<T> col;
	public CollectorAdapter(Collection<T> col) {
		this.col = col;
	}
	public String changeSome() {
		return col.getClass().getName();
	}
	
}
interface Collector<T> {
	T changeSome();
}
class StringTest implements Collector<String> {
	Collection<String> col = Arrays.asList("HELO");
	public String changeSome() {
		return "HELLOS";
	}
}
class IntegerTest implements Collector<Integer> {
	Collection<Integer> col = Arrays.asList(12, 123, 3);
	public Integer changeSome() {
		return 123;
	}
}
class Functional {
	public static <T> T some(Collector<T> col) {
		return col.changeSome();
	}
}