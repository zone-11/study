package study.GUI;

import javax.swing.JFrame;

public class Tools {
	public static void run(final JFrame f, final int width, final int height) {
		f.setTitle(f.getClass().getSimpleName());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(width, height);
		f.setVisible(true);
	}
	public static void run(final JFrame f) {
		run(f, 500, 500);
	}
}
