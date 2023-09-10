package study.generics;

import java.util.ArrayList;
import java.util.Date;

public class Generic2 {

	public static void main(String[] args) {
		Mixin mixin = new Mixin();
		mixin.set("HELLO");
		System.out.println(mixin.get() + " " + 
		mixin.getStamp() + " " + mixin.getSerialNumber() +
		" " + mixin.getColor());
	}

}
interface TimeStamped { long getStamp();}
class TimeStampedImp implements TimeStamped {
	private final long timeStamp;
	public TimeStampedImp() {
		timeStamp = new Date().getTime();
	}
	public long getStamp() {
		return timeStamp;
	}
}
interface SerialNumbered { long getSerialNumber();}
class SerialNumberedImp implements SerialNumbered {
	private static long counter = 1;
	private final long serialNumber = counter++;
	public long getSerialNumber() {return serialNumber;}
}
interface Basic {
	public void set(String val);
	public String get();
}
class BasicImp implements Basic {
	private String value;
	public void set(String val) { value = val; }
	public String get() { return value; }
}
interface Colored { String getColor(); }
class ColoredImp implements Colored {
	String color = "ORANGE";
	public String getColor() {
		return color;
	}
}
class Mixin extends BasicImp
implements TimeStamped, SerialNumbered, Colored {
	private TimeStamped timeStamp = new TimeStampedImp();
	private SerialNumbered serialNumber = new SerialNumberedImp();
	private Colored color = new ColoredImp();
	
	public long getStamp() { return timeStamp.getStamp(); }
	public long getSerialNumber() { return serialNumber.getSerialNumber(); }
	public String getColor() { return color.getColor(); }
}
interface Processor<T extends Exception> {
	void process() throws T;
}
class ProcessorRunner<T extends Exception> extends ArrayList<Processor<T>> {
	public void processAll() throws T {
		for(Processor<T> processor : this) {
			processor.process();
		}
	}
}
abstract class SelfBounded<T extends SelfBounded<T>> {
	abstract T test(T arg);
	public T test2(T arg) {
		return test(arg);
	}
}
class Test extends SelfBounded<Test> {
	public Test test(Test arg) {
		return new Test();
	}
}