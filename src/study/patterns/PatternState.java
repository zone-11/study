package study.patterns;

public class PatternState {
	public static void main(String[] args) {
		Context con = new Context();
		con.setState(Activities.SLEEPING);
		con.setState(Activities.NIGHTLY);
		con.doAction();
		con.doAction();
	}
}
interface State<T extends IContext<T>> {
	void doAction(T context);
}
class Context extends IContext<Context> {
	public void doAction() {
		state.doAction(this);
	}
}
class EnemyInterestingContext extends IContext<EnemyInterestingContext> {
	public void doAction() {
		System.out.println("ENEMY_INTERESTING_CONTEXT: doAction()");
		state.doAction(this);
	}
}
enum EnemyStates implements State<EnemyInterestingContext> {
	INTERESTING {
		public void doAction(EnemyInterestingContext context) {
			System.out.println("+10 to INTERESTING");
			context.setState(DEFAULT);
		}
	},
	DEFAULT {
		public void doAction(EnemyInterestingContext context) {
			System.out.println("DEFAULT");
			context.setState(INTERESTING);
		}
	};
}
abstract class IContext<T extends IContext<T>> {
	State<T> state;
	
	
	public void setState(State<T> state) {
		this.state = state;
	}
	abstract void doAction();
}
enum Activities implements State<Context> {
	ACTIVE {
		public void doAction(Context context) {
			System.out.println("ACTIVE");
			context.setState(NIGHTLY);
		}
	},
	NIGHTLY {
		public void doAction(Context context) {
			System.out.println("NIGHTLY");
			context.setState(SLEEPING);
		}
	},
	SLEEPING {
		public void doAction(Context context) {
			System.out.println("SLEEPING");
			context.setState(ACTIVE);
		}
	};
}