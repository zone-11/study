package study.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

public class Lesson2 {

	public static void main(String[] args) {
		System.out.println();
	}
}
class JFrameTest extends JFrame {
	JButton btn1 = new JButton("Button1");
	JButton btn2 = new JButton("Button2");
	JTextField field = new JTextField(10);
	
	public JFrameTest() {
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText("Button1");
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				field.setText("Button2");
			}
		});
		add(BorderLayout.WEST, btn1);
		add(BorderLayout.EAST, btn2);
	}
}
class TrackEvent extends JFrame {
	private HashMap<String, JTextField> map =
			new HashMap<>();
	private String[] event = {
			"focusLost", "focusGained", "keyreleased",
			"keyTyped", "keyPressed", "mouseEntered",
			"mousePressed", "mouseClicked", "mouseReleased",
			"mouseExited", "mouseMoved", "mouseDragged"
	};
	private SuperButton
		b1 = new SuperButton(Color.red, "BUTTON1"),
		b2 = new SuperButton(Color.blue, "BUTTON2");
	public class SuperButton extends JButton{
		void report(String field, String msg) {
			JTextField t = map.get(field);
			if(t != null) {
				t.setText(msg);
			} else {
				System.out.println("NULL");
			}
		}
		FocusListener fl = new FocusListener() {
			public void focusLost(FocusEvent e) {
				report("focusLost", e.paramString());
			}
			public void focusGained(FocusEvent e) {
				report("focusGained", e.paramString());
			}
		};
		KeyListener kl = new KeyListener() {
			public void keyReleased(KeyEvent e) {
				report("keyReleased", e.paramString());
			}
			public void keyTyped(KeyEvent e) {
				report("keyTyped", e.paramString());
			}
			public void keyPressed(KeyEvent e) {
				report("keyPressed", e.paramString());
			}
		};
		MouseListener ml = new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				report("mouseEntered", e.paramString());
			}
			public void mousePressed(MouseEvent e) {
				report("mousePressed", e.paramString());
			}
			public void mouseClicked(MouseEvent e) {
				report("mouseClicked", e.paramString());
			}
			public void mouseReleased(MouseEvent e) {
				report("mouseReleased", e.paramString());
			}
			public void mouseExited(MouseEvent e) {
				report("mouseExited", e.paramString());
			}
		};
		MouseMotionListener mml = new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				report("mouseMoved", e.paramString());
			}
			public void mouseDragged(MouseEvent e) {
				report("mouseDragged", e.paramString());
			}
		};
		public SuperButton(Color color, String label) {
			super(label);
			setBackground(color);
			addFocusListener(fl);
			addKeyListener(kl);
			addMouseListener(ml);
			addMouseMotionListener(mml);
		}
	}
	public TrackEvent() {
		setLayout(new GridLayout(event.length + 1, 2));
		for(String e : event) {
			JTextField field = new JTextField();
			field.setEditable(false);
			add(new JLabel(e, JLabel.RIGHT));
			add(field);
			map.put(e, field);
			System.out.println(map.toString());
		}
		add(b1);
		add(b2);
	}
}
