package whz.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JPanel;

import whz.util.Global;

public class Bullets{
	private LinkedList<Bullet> bullet = new LinkedList();
	
	public LinkedList<Bullet> getBullet(){
		return bullet;
	}
	
	public void setBullet(LinkedList<Bullet> bullet) {
		this.bullet = bullet;
	}

	public class Bullet extends Point{
		private int direction;
		private int from;
	
		public int getFrom() {
			return from;
		}
		
		public Bullet(int direction, int from, Point p){
			this.direction = direction;
			this.from = from;
			x = p.x;
			y = p.y;
		}
		
		public void move() {
			switch(direction) {
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
				if(x<0) {
					x=Global.DOUBLE_WIDTH-1;
				}
				if(y<0) {
					y=Global.DOUBLE_HEIGHT-1;
				}
				break;
			case Global.UP_RIGHT:
				x++;
				y--;
				if(x>=Global.DOUBLE_WIDTH) {
					x=0;
				}
				if(y<0) {
					y=Global.DOUBLE_HEIGHT-1;
				}
				break;
			case Global.DOWN_LEFT:
				x--;
				y++;
				if(x<0) {
					x=Global.DOUBLE_WIDTH-1;
				}
				if(y>=Global.DOUBLE_HEIGHT) {
					y=0;
				}
				break;
			case Global.DOWN_RIGHT:
				x++;
				y++;
				if(x>=Global.DOUBLE_WIDTH) {
					x=0;
				}
				if(y>=Global.DOUBLE_HEIGHT) {
					y=0;
				}
				break;
			}
		}
	
		public void drawMyself(Graphics g) {
			move();
			g.setColor(Color.black);
			g.fillRoundRect((int)this.getX() * Global.CELL_SIZE, (int) this.getY() * Global.CELL_SIZE, Global.CELL_SIZE,
					Global.CELL_SIZE, 10,10);
		}
		
		public boolean shootSnake(Snake_1 snake_1, Snake_2 snake_2) {
			if(from == 1) {
				for(Point b:snake_2.body) {
					if(b.x==x&&b.y==y) {
						return true;
					}
				}
				return false;
			}else {
				for(Point b:snake_1.body) {
					if(b.x==x&&b.y==y) {
						return true;
					}
				}
				return false;
			}
		}
	}
}











