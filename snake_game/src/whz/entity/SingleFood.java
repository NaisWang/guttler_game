package whz.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import whz.util.Global;

/*
 * Food
 * Ways of food£º
 * 1.isEatenBySnake()
 * 2.drawSelf()
 */

public class SingleFood extends Point {
	public boolean isEatenBySnake(Snake snake) {
		System.out.println("Food is not eaten by snake");
		Point head = snake.getHead();
		if (this.equals(head)) {
			return true;
		}
		return false;
	}

	public void drawSelf(Graphics g) {
		g.setColor(Color.red);
		System.out.println("Food is drawing itself");
		g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
	}

	public void addFood(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
}
