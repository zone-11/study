package study.enums;

import java.util.EnumMap;
import java.util.Random;

public class Enumeration2 {

	public static void main(String[] args) {

	}

}
enum Input {
	NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
	TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
	ABORT_TRANSITION {
		public int amount() {
			throw new RuntimeException("ABORT.amount()");
		}
	},
	STOP {
		public int amount() {
			throw new RuntimeException("STOP.amount()");
		}
	};
	
	private int value;
	private Input(int amount) {
		this.value = amount;
	}
	private Input() {}
	
	public int amount() { return value; }
	
	static Random rand = new Random(47);
	public static Input randomSelection() {
		return values()[rand.nextInt(values().length - 1)];
	}
}
enum Category {
	MONEY(Input.NICKEL, Input.DIME, Input.QUARTER, Input.DOLLAR),
	ITEM_SELECTION(Input.TOOTHPASTE, Input.CHIPS, Input.SOAP, Input.SODA),
	QUIT_TRANSITION(Input.ABORT_TRANSITION),
	SHUT_DOWN(Input.STOP);
	
	private Input[] values;
	private Category(Input... types) {
		this.values = types;
	}
	private static EnumMap<Input, Category> categories = 
			new EnumMap<>(Input.class);
	static {
		for(Category c : Category.class.getEnumConstants()) {
			for(Input i : c.values) {
				categories.put(i, c);
			}
		}
	}
	public static Category categorize(Input input) {
		return categories.get(input);
	}
}

class VendingMachine {
	private static int amount = 0;
	private static State state = State.RESTING;
	private static Input selection = null;
	
	enum StateDuration { TRANSIENT }
	enum State {
		RESTING {
			void next(Input input) {
				switch(Category.categorize(input)) {
				case MONEY:
					amount += input.amount();
					state = ADDING_MONEY;
					break;
				case SHUT_DOWN:
					state = TERMINAL;
				default:
				}
			}
		},
		ADDING_MONEY {
			void next(Input input) {
				switch(Category.categorize(input)) {
				case MONEY:
					amount += input.amount();
				case ITEM_SELECTION:
					selection = input;
					if(amount < selection.amount()) {
						System.out.println("ERORR");
					} else {
						state = DISPENSING;
					}
					break;
				case SHUT_DOWN:
					state = TERMINAL;
				default:
				}
			}
		},
		DISPENSING {
			void next() {
				System.out.println("here is your " + selection);
				amount -= selection.amount();
				state = GIVING_CHANGE;
			}
		},
		GIVING_CHANGE(StateDuration.TRANSIENT) {
			void next() {
				if(amount > 0) {
					System.out.println("Your change: " + amount);
					amount = 0;
				}
				state = RESTING;
			}
		},
		TERMINAL { void output() { System.out.println("HALTED"); } };
		private boolean isTransient = false;
		State() {}
		State(StateDuration trans) { isTransient = true; }
		void next(Input input) {
			throw new RuntimeException("ERROR");
		}
		void next() {
			throw new RuntimeException("ERROR");
		}
		void output() { System.out.println("Amount"); }
	}
}