package study.lambda;

import java.util.Optional;

public class Lesson1 {

	public static void main(String[] args) {
		Something info = Testing.test("say hello").orElse(new Something("UNKNOWN"));
		System.out.println(info.getTextInfo());
	}

}

class Testing {
	public static Optional<Something> test(String name) {
		Something something = new Something(name);
		return Optional.ofNullable(null);
	}
}

class Something {
	private String textInfo = "DEFAULT";
	
	public Something(String textInfo) {
		this.textInfo = textInfo;
	}
	public Something() {}

	/**
	 * @return the textInfo
	 */
	public String getTextInfo() {
		return textInfo;
	}
	
}

class Factory {
	public static <T, R> T create(Generator<T, R> generator, R arg) {
		return generator.next(arg);
	}
}

interface Generator<T, R> {
	T next(R arg);
}

class Info<T> { 
	private T a;
	
	public Info(T a) {
		this.a = a;
	}
	public Info() {}

	public T getA() {
		return a;
	}
	
}

class Maker {
	public static <T> void handle(Reaction<T> reaction, T element) {
		reaction.justDoIt(element, "Hello");
	}
}

class Action {
	
	public void doAction() {
		System.out.println("Do action");
	}
	
	public static <T> void otherAction(T element, String text) {
		System.out.println(element.getClass().getSimpleName() + " :Other action");
	}
	
	public void againAction(String text) {
		System.out.println(this.getClass().getSimpleName() + ": " + text);
	}
}

interface Reaction<T> {
	void justDoIt(T element, String text);
}
