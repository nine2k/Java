import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LayoutQuestion extends JFrame {
	public LayoutQuestion() {
		Container contents = getContentPane();
		JButton aButton = new JButton("A");
		JButton bButton = new JButton("B");
		JButton cButton = new JButton("C");
		JButton dButton = new JButton("D");
		JPanel abPanel = new JPanel(new GridLayout(1,0));
		
		
		abPanel.add(aButton);
		//abPanel.add(bButton);
		contents.add(abPanel, BorderLayout.SOUTH);
		//contents.add(cButton, BorderLayout.CENTER);
		//contents.add(dButton, BorderLayout.SOUTH);
		//setSize(300,300);
		pack();
		setVisible(true);
	}
	public static void main(String args[]) {
		JFrame window = new LayoutQuestion();
	} // end main
}