package study.ThreadProgramming;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;

public class Exp43 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Active {
	
}
class ActiveHandler {
	public static void handle(Class<?> clazz) {
		Active ann = clazz.getAnnotation(Active.class);
		if(ann == null) return;
		Method[] methods = clazz.getMethods();
		for(Method method : methods) {
		}
	}
}