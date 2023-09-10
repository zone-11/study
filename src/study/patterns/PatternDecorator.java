package study.patterns;

public class PatternDecorator {

	public static void main(String[] args) {
		Robot<? extends RobotAbility> robot = new RobotCooker<Killer>(new Killer());
		robot = new RobotCooker<Super>(new Super());
		System.out.println(robot.doJob());
		System.out.println(robot.activateRobotAbility());
	}

}
abstract class Robot<T extends RobotAbility> {
	private T ability;
	
	public Robot(T ability) {
		this.ability = ability;
	}
	
	public abstract String doJob();
	public String activateRobotAbility() {
		return ability.activate();
	}
}
class RobotCooker<T extends RobotAbility> extends Robot<T> {
	
	public RobotCooker(T ability) {
		super(ability);
	}
	public String doJob() {
		return "I cook some food";
	}
}
interface RobotAbility {
	String activate();
}
class Killer implements RobotAbility {
	public String activate() {
		return "Kill some";
	}
}
class Super implements RobotAbility {
	
	public String activate() {
		return "You are powerless";
	}
}