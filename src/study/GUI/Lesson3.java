package study.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;

public class Lesson3 {

	public static void main(String[] args) {
		Borders.main(args);
		
	}
}
class Borders extends JFrame {
	static JPanel showBorder(Border b) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel(b.getClass().getSimpleName(), JLabel.CENTER),
				BorderLayout.CENTER);
		panel.setBorder(b);
		return panel;
	}
	public Borders() {
		setLayout(new GridLayout(2, 4));
		add(showBorder(new TitledBorder("So")));
		add(showBorder(new EtchedBorder()));
		add(showBorder(new LineBorder(Color.cyan)));
		add(showBorder(
				new MatteBorder(5, 5, 30, 30, Color.black)));
		add(showBorder(
				new BevelBorder(BevelBorder.RAISED)));
		add(showBorder(
				new SoftBevelBorder(BevelBorder.LOWERED)));
		add(showBorder(new CompoundBorder(
				new EtchedBorder(),
				new LineBorder(Color.BLUE) )));
	}
	public static void main(String[] args) {
		Tools.run(new Borders(), 555, 555);
	}
}
class ButtonsGroups {
	public static JPanel makeBtnPanel(Class<? extends AbstractButton> kind,
			String[] ids) {
		ButtonGroup group = new ButtonGroup();
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(kind.getSimpleName()));
		for(String id : ids) {
			AbstractButton ab = new JButton("failed");
			try {
				Constructor constr = kind.getConstructor(String.class);
				ab = (AbstractButton)constr.newInstance(id);
				ab.setToolTipText("Button");
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			group.add(ab);
			panel.add(ab);
		}
		
		return panel;
	}
}
class Buttons extends JFrame {
	public Buttons() {
		setLayout(new FlowLayout());
		add(new JButton("Default button"));
		add(new JToggleButton("JToggleButton"));
		add(new JCheckBox("JCheckBox"));
		add(new JRadioButton("JRadioButton"));
		add(ButtonsGroups.makeBtnPanel(JToggleButton.class, new String[] {"FIRST",
				"SECONDS", "THIRD"}));
		JPanel jp = new JPanel();
		jp.setBorder(new TitledBorder("Directions"));
		jp.add(new BasicArrowButton(BasicArrowButton.NORTH));
		jp.add(new BasicArrowButton(BasicArrowButton.SOUTH));
		jp.add(new BasicArrowButton(BasicArrowButton.WEST));
		jp.add(new BasicArrowButton(BasicArrowButton.EAST));
		add(jp);
	}
}