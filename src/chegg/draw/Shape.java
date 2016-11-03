package chegg.draw;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Shape extends Frame {

	public Shape() {
		super("Shape tutorial");
		setSize(400, 400);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.BLUE);
	}

	public static void main(String[] args) {
		Shape shape = new Triangle();
		shape.setVisible(true);
	}
}
