package study.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Lesson4 {

	public static void main(String[] args) {
		Tools.run(new ComboBox());

	}
}
class ComboBox extends JFrame {
	private JComboBox<Item> box = new JComboBox<>();
	private JTextField field = new JTextField(10);
	private Random rand = new Random(47);
	private JLabel label = new JLabel();
	
	private class Item {
		
		private int price;
		private String productName;
		public Item(int price, String productName) {
			super();
			this.price = price;
			this.productName = productName;
		}
		/**
		 * @return the price
		 */
		public int getPrice() {
			return price;
		}
		/**
		 * @return the productName
		 */
		public String getProductName() {
			return productName;
		}
		public String toString() {
			return productName;
		}
	}
	
	public ComboBox() {
		field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				box.addItem(new Item(rand.nextInt(0, 50),
						field.getText()));
			}
		});
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item)box.getSelectedItem();
				label.setText("Product: " + item.getProductName() +
						", Price: " + item.getPrice());
			}
		});
		setLayout(new FlowLayout());
		add(field);
		add(box);
		add(label);
	}
}
class RadioButton extends JFrame {
	private JRadioButton rb1 = new JRadioButton("first");
	private JRadioButton rb2 = new JRadioButton("second");
	private JRadioButton rb3 = new JRadioButton("third");
	private JTextField field = new JTextField(10);
	private ButtonGroup group = new ButtonGroup();
	
	private ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			field.setText("RadioButton: " + 
		((JRadioButton)e.getSource()).getText());
		}
	};
	
	public RadioButton() {
		rb1.addActionListener(al);
		rb2.addActionListener(al);
		rb3.addActionListener(al);
		field.setEditable(false);
		group.add(rb1);
		group.add(rb2);
		group.add(rb3);
		setLayout(new FlowLayout());
		add(field);
		add(rb1);
		add(rb2);
		add(rb3);
	}
}
class CheckBox extends JFrame {
	JCheckBox cb1 = new JCheckBox("CheckBox1");
	JCheckBox cb2 = new JCheckBox("CheckBox2");
	JCheckBox cb3 = new JCheckBox("CheckBox3");
	
	JTextArea textArea = new JTextArea();
	
	private class CheckBoxListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JCheckBox cb) {
				textArea.append("checkbox: " + cb.getText() +
						", selected: " + cb.isSelected() + "\n");
			}
		}
	}
	public CheckBox() {
		setLayout(new FlowLayout());
		cb1.addActionListener(new CheckBoxListener());
		cb2.addActionListener(new CheckBoxListener());
		cb3.addActionListener(new CheckBoxListener());
		add(cb1);
		add(cb2);
		add(cb3);
		add(BorderLayout.SOUTH, new JScrollPane(textArea));
	}
}
class TextPane extends JFrame {
	JTextPane tp = new JTextPane();
	JButton b = new JButton("Button");
	
	public TextPane() {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tp.setText(tp.getText() + "HELLO\n");
			}
		});
		add(new JScrollPane(tp));
		add(BorderLayout.SOUTH, b);
	}
}
