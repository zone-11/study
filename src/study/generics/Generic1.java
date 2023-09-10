package study.generics;

import java.util.ArrayList;
import java.util.List;

public class Generic1 {

	public static void main(String[] args) {
		Apple apple = new Apple();
	}
	public static <T> void f1(Holder<T> holder, T element) {
		holder.set(element);
	}
	public static <T> void f2(Holder<T> holder) {
		T a = holder.get();
		System.out.println(a.getClass().getName());
	}
	public static void f3(Holder<?> holder) {
		f2(holder);
	}
	public static void f4(Holder<List<?>> holder) {
		holder.set(new ArrayList<Integer>(12));
		List<?> list = holder.get();
		Object obj = list.get(0);
		list.remove(0);
	}
	public static void f5(List<Holder<?>> list) {
		list.add(new Holder<Integer>(5));
		Holder<?> holder = list.get(0);
		Object obj = holder.get();
	}
}
class ProductContainer<T> {
	T product;
}
class Apple extends ProductContainer<Apple> {
	
}
class UseList<W, T> {
	void f1(List<W> v) {}
	void f2(List<T> v) {}
}
class Holder<T> {
	private T element;
	public Holder(T element) {
		this.element = element;
	}
	public T get() {return element;}
	public void set(T element) {this.element = element;}
}
