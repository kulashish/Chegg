package chegg.draw;

import java.awt.Graphics;
import java.awt.Point;

public class Triangle extends Shape {

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point p[] = new Point[3];
		p[0] = new Point((int) 100, 100);
		p[1] = new Point((int) 300, 100);
		p[2] = new Point((int) 200, 300);
		int[] xCoord = { p[0].x, p[1].x, p[2].x };
		int[] yCoord = { p[0].y, p[1].y, p[2].y };
		g.drawPolygon(xCoord, yCoord, 3);

	}

}
