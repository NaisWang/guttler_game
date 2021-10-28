package whz.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import whz.util.Global;

/*
 * Barrier
 * Ways of Barrier:
 * 1.isEatenBySelf()
 * 2.drawSelf()
 * 
 */

public class Barrier extends Entity {
	private int[][] rocks = new int[Global.DOUBLE_WIDTH][Global.DOUBLE_HEIGHT];

	public Barrier() {
		for (int y = 0; y < Global.DOUBLE_HEIGHT; y++) {
			for (int x = 0; x < Global.DOUBLE_WIDTH; x++) {
				if (y == 0 || y == Global.DOUBLE_HEIGHT - 1) {
					rocks[x][y] = 1;
				}
				if (x == 0 || x == Global.DOUBLE_WIDTH - 1) {
					rocks[x][y] = 1;
				}
			}
		}
		setColor(Color.gray);
	}

	//判断是否被蛇吃掉
    @Override
	public boolean isHit(Point head) {
//		System.out.println("The barrier isn't touched by the snake");
		for (int x = 0; x < Global.DOUBLE_WIDTH; x++) {
			for (int y = 0; y < Global.DOUBLE_HEIGHT; y++) {
				if (rocks[x][y] == 1 && head.x == x && head.y == y) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void drawMyself(Graphics g) {
//		System.out.println("Barrier is drawing itself");
		g.setColor(getColor());
		for (int y = 0; y < Global.DOUBLE_HEIGHT; y++) {
			for (int x = 0; x < Global.DOUBLE_WIDTH; x++) {
				if (rocks[x][y] == 1) {
					g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}

	public int[][] getRocks() {
		return this.rocks;
	}
}
