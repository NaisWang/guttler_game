package whz.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import whz.entity.Bullets.Bullet;
import whz.entity.DoubleBarrier.Barriers;
import whz.listener.SnakeListener;
import whz.util.Global;

public class Snake {

	//蛇的当前方向与要变向的方向
	public int oldDirection, newDirection;

	// 蛇尾的坐标
	public Point tail;
	// 吃掉食物的个数
	public int food = 0;

	//蛇是否存活
	public boolean life = true;

	public boolean key = true;

	// 蛇监听器，监听蛇的移动
	public SnakeListener snakeListener;


	// 蛇身体的所有节点对应的坐标
	public LinkedList<Point> body = new LinkedList();

	// 蛇每次移动的间隔时间
	public int sleep = 150;

	// 设置蛇已吃到的食物的个数
	public void setFood(int food) {
		this.food = food;
	}

	// 得到蛇已吃到的食物的个数
	public int getFood() {
		return food;
	}

	// 设置蛇每次移动的间隔时间
	public void setSleep(int sleep) {
		this.sleep = sleep;
	}

	// 得到蛇每次移动的间隔时间
	public int getSleep() {
		return sleep;
	}

	// 设置是否
	public void setLife(boolean life) {
		this.life = life;
	}

	public void setKey(boolean key) {
		this.key = key;
	}

	public boolean getKey() {
		return key;
	}

	// 蛇在移动
	public void move() {
		System.out.println("Snake is moving");
		// 移除掉蛇尾
		tail = body.removeLast();

		// 获取蛇头的(x, y)坐标
		int x = body.getFirst().x;
		int y = body.getFirst().y;

		// 如果要变向的方向与当前方向不是相反方向，则可以变向, 反之，不可以变向
		if (oldDirection + newDirection != 0) {
			oldDirection = newDirection;
		}

		// 更改蛇头坐标
		switch (oldDirection) {
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

		// 将新获取的蛇头坐标添加到蛇节点中
		body.addFirst(new Point(x, y));
	}

	// 吃到食物
	public void eatFood() {
		System.out.println("Snake is eating food");
		// 添加蛇尾节点
		body.addLast(tail);
	}

	// 获取蛇头坐标
	public Point getHead() {
		return body.getFirst();
	}

	// 改变蛇的方向
	public void changeDirection(int nowDirection) {
		System.out.println("Snake is changing direction");
		newDirection = nowDirection;
	}

	// 绘制蛇
	public void drawSelf(Graphics g, int type) {
		System.out.println("Snake is drawing itself");
		g.setColor(Color.blue);
		// 遍历蛇的所有节点坐标
		for (int i = 0; i < body.size(); i++) {
			// 绘制蛇的节点
			g.fillRoundRect((int) body.get(i).x * Global.CELL_SIZE, (int) body.get(i).y * Global.CELL_SIZE, Global.CELL_SIZE,
					Global.CELL_SIZE, 10, 10);
		}
		// 绘制蛇头节点
		if (!body.isEmpty()) {
			if(type == 1){
				g.setColor(Color.black);
			}else{
				g.setColor(Color.yellow);
			}
			g.fillRoundRect((int) body.getFirst().getX() * Global.CELL_SIZE,
					(int) body.getFirst().getY() * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, 10, 10);
		}
	}

	// 判断是否吃到自己
	public boolean isEatSelf() {
		System.out.println("Snake isn't eaten by itself");
		for (int i = 1; i < body.size(); i++) {
			if (body.get(i).equals(getHead())) {
				return true;
			}
		}
		return false;
	}

	// 判断蛇的身体是否碰到蛇的节点
	public boolean isFoodInSnake(Point p) {
		for (Point b : body) {
			if (b.x == p.x && b.y == p.y) {
				return true;
			}
		}
		return false;
	}

	// 设置蛇监听器
	public void addSnakeListener(SnakeListener snakerListener) {
		this.snakeListener = snakerListener;
	}

	// 射击
	public void shoot() {
		if (body.size() >= 5) {
			body.removeLast();
		}
		System.out.println("shooting");
	}

	// 添加障碍物
	public boolean produceBarrier() {
		if (body.size() >= 6) {
			body.removeFirst();
			return true;
		}
		return false;
	}

	// 判断是否碰到了障碍物，如果碰到了则去掉2格蛇节点
	public void isEatenBarrier(LinkedList<Barriers> barriers, int type) {
		Point head = body.getFirst();
		for (int i = 0; i < barriers.size(); i++) {
			Barriers barrier = barriers.get(i);
			if (type != barrier.getFrom() && head.x == barrier.x && head.y == barrier.y) {
				body.removeFirst();
				body.removeFirst();
			}
		}
	}

	// 判断是否被射中
	public void isShoot(Bullets bullets, int type) {
		for (Bullet b : bullets.getBullet()) {
			if (b.getFrom() != type) {
				for (Point p : body) {
					if (b.x == p.x && b.y == p.y) {
						body.remove(p);
						bullets.getBullet().remove(b);
						return;
					}
				}
			}
		}
	}

}
