package study.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Lesson5 {

	public static void main(String[] args) {
		Tools.run(new FileChooserTest());
	}
}
class FileChooserTest extends JFrame {
	private JTextField field = new JTextField(1);

	private JButton
		btn1 = new JButton("Open"),
		btn2 = new JButton("Save");
	public FileChooserTest() {
		field.setEditable(false);
		setLayout(new FlowLayout());
		add(field);
		add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int i = fileChooser.showOpenDialog(FileChooserTest.this);
				if(i == JFileChooser.APPROVE_OPTION) {
					field.setText(fileChooser.getSelectedFile()
							.getName());
				}
			}
		});
	}
}
class Menus extends JFrame {
	private JTextField tf = new JTextField(20);
	private ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tf.setText(((JMenuItem)e.getSource()).getText());
		}
	};
	
	public Menus() {
		JMenu menu = new JMenu("First");
		JMenu menu1 = new JMenu("Second");
		tf.setEditable(false);
		for(int i = 0; i < 5; i++) {
			JMenuItem item = new JMenuItem("SomeProperty" + i);
			item.addActionListener(al);
			menu.add(item);
		}
		for(int i = 0; i < 5; i++) {
			JMenuItem item = new JMenuItem("SomeProperty" + i);
			item.addActionListener(al);
			menu1.add(item);
		}
		JMenuBar bar = new JMenuBar();
		bar.add(menu);
		bar.add(menu1);
		setJMenuBar(bar);
		setLayout(new FlowLayout());
		add(tf);
	}
}
class TabedPane extends JFrame {
	JTabbedPane tp = new JTabbedPane();
	
	public TabedPane() { 
		setLayout(new FlowLayout());
		tp.addTab("someTab", new JCheckBox());
		add(tp);
	}
}
class MessageBoxes extends JFrame {
	JButton alertButton = new JButton("Alert");
	JButton confirmButton = new JButton("Confirm");
	
	public MessageBoxes() {
		setLayout(new FlowLayout());
		alertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						"Hellow this is alert", "some",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, 
						"or no", "choose yes",
						JOptionPane.YES_NO_OPTION);
				System.out.println(i);
			}
		});
		add(confirmButton);
		add(alertButton);
	}
}