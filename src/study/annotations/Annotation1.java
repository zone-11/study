package study.annotations;

import java.lang.annotation.*;
import java.lang.reflect.*;

public class Annotation1 {

	public static void main(String[] args) {
		TestAnnotationHandler.handle(ForTest.class);
	}
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Test {
	public String name() default "HUGIE";
	public Some some() default @Some(id = 10);
}
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Some {
	public String title() default "DEFAULT TITLE";
	public int id();
}
class TestAnnotationHandler {
	public static void handle(Class<?> testClass) {
		for(Method method : testClass.getDeclaredMethods()) {
			Test ann = method.getAnnotation(Test.class);
			if(ann != null) {
				System.out.println(ann.name());
				System.out.println(ann.some().title());
				System.out.println(ann.some().id());
			} else {
				System.out.println("NULL");
			}
		}
	}
}
class ForTest {
	@Test(name = "JAMES", some = @Some(id = 20, title = "GENIUS"))
	public void test1() {}
	@Test(name = "NINGA")
	public void test2() {}
	@Test(name = "ROBERT")
	public void test3() {}
}
