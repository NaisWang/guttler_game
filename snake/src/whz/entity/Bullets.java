package whz.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JPanel;

import whz.util.Global;

public class Bullets {
	private LinkedList<Bullet> bullet = new LinkedList();

	public LinkedList<Bullet> getBullet() {
		return bullet;
	}

	public void setBullet(LinkedList<Bullet> bullet) {
		this.bullet = bullet;
	}

	public class Bullet extends Point {

		// 标记子弹的方向
		private int direction;

		// 标记该子弹指向的是谁
		private int from;

		public int getFrom() {
			return from;
		}

		public Bullet(int direction, int from, Point p) {
			this.direction = direction;
			this.from = from;
			x = p.x;
			y = p.y;
		}

		// 子弹的移动
		public void move() {
			switch (direction) {
				case Global.UP:
					y--;
					if (y < 0) {
						y = Global.DOUBLE_HEIGHT - 1;
					}
					break;
				case Global.DOWN:
					y++;
					if (y >= Global.DOUBLE_HEIGHT) {
						y = 0;
					}
					break;
				case Global.LEFT:
					x--;
					if (x < 0) {
						x = Global.DOUBLE_WIDTH - 1;
					}
					break;
				case Global.RIGHT:
					x++;
					if (x >= Global.DOUBLE_WIDTH) {
						x = 0;
					}
					break;
				case Global.UP_LEFT:
					x--;
					y--;
					if (x < 0) {
						x = Global.DOUBLE_WIDTH - 1;
					}
					if (y < 0) {
						y = Global.DOUBLE_HEIGHT - 1;
					}
					break;
				case Global.UP_RIGHT:
					x++;
					y--;
					if (x >= Global.DOUBLE_WIDTH) {
						x = 0;
					}
					if (y < 0) {
						y = Global.DOUBLE_HEIGHT - 1;
					}
					break;
				case Global.DOWN_LEFT:
					x--;
					y++;
					if (x < 0) {
						x = Global.DOUBLE_WIDTH - 1;
					}
					if (y >= Global.DOUBLE_HEIGHT) {
						y = 0;
					}
					break;
				case Global.DOWN_RIGHT:
					x++;
					y++;
					if (x >= Global.DOUBLE_WIDTH) {
						x = 0;
					}
					if (y >= Global.DOUBLE_HEIGHT) {
						y = 0;
					}
					break;
			}
		}

		// 绘制自己
		public void drawMyself(Graphics g) {
			move();
			if(from == 1){
				g.setColor(Color.GRAY);
			}else{
				g.setColor(Color.green);
			}
			g.fillRoundRect((int) this.getX() * Global.CELL_SIZE, (int) this.getY() * Global.CELL_SIZE, Global.CELL_SIZE,
					Global.CELL_SIZE, 10, 10);
		}
	}
}











