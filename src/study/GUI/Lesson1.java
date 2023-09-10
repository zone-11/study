package study.GUI;

import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Lesson1 {

	public static void main(String[] args) throws InterruptedException {
		JFrame j = new JFrame("Hello kitty");
		j.setSize(500, 500);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("JavaSwing");
		j.add(label);
		TimeUnit.SECONDS.sleep(2);
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				System.out.println("HELLO");
				label.setText("SUPER");
			}
		});
	}

}
