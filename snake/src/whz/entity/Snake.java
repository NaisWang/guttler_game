package whz.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import whz.entity.Bullets.Bullet;
import whz.entity.DoubleBarrier.Barriers;
import whz.listener.SnakeListener;
import whz.util.Global;

/**
 * Snake Ways of snake 1.move() 2.eatFood(Food food) 3.changeDirection()
 * 4.drawSelf() 5.isEatenBySelf()
 */

public class Snake {
	public int oldDirection, newDirection;
	public Point tail;
	public int food = 0;
	public boolean life = true;
	public boolean key = true;
	public SnakeListener snakeListener;
	public LinkedList<Point> body = new LinkedList();
	public int sleep = 300;

	public Snake() {
		init();
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getFood() {
		return food;
	}

	public void setSleep(int sleep) {
		this.sleep = sleep;
	}

	public int getSleep() {
		return sleep;
	}

	public void setLife(boolean life) {
		this.life = life;
	}

	public void init() {
		food = 0;
		body.clear();
		life = true;
		int x = Global.WIDTH / 2;
		int y = Global.HEIGHT / 2;
		for (int i = 0; i < 4; i++) {
			body.add(new Point(x - i, y));
		}
		oldDirection = newDirection = Global.RIGHT;
	}

	public void setKey(boolean key) {
		this.key = key;
	}

	public boolean getKey() {
		return key;
	}

	public void move() {
		System.out.println("Snake is moving");
		tail = body.removeLast();
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		if (oldDirection + newDirection != 0) {
			oldDirection = newDirection;
		}
		switch (oldDirection) {
		case Global.UP:
			y--;
			if (y < 0) {
				y = Global.HEIGHT - 1;
			}
			break;
		case Global.DOWN:
			y++;
			if (y >= Global.HEIGHT) {
				y = 0;
			}
			break;
		case Global.LEFT:
			x--;
			if (x < 0) {
				x = Global.WIDTH - 1;
			}
			break;
		case Global.RIGHT:
			x++;
			if (x >= Global.WIDTH) {
				x = 0;
			}
			break;
		}
		body.addFirst(new Point(x, y));
	}

	public void eatFood() {
		System.out.println("Snake is eating food");
		body.addLast(tail);
	}

	public Point getHead() {
		return body.getFirst();
	}

	public void changeDirection(int nowDirection) {
		System.out.println("Snake is changing direction");
		newDirection = nowDirection;
	}

	public void drawSelf(Graphics g) {
		System.out.println("Snake is drawing itself");
		g.setColor(Color.blue);
		for (int i = 0; i < body.size(); i++) {
			g.fillRoundRect((int) body.get(i).x* Global.CELL_SIZE, (int) body.get(i).y * Global.CELL_SIZE, Global.CELL_SIZE,
					Global.CELL_SIZE, 10, 10);
		}
		if (!body.isEmpty()) {
			g.setColor(Color.black);
			g.fillRoundRect((int) body.getFirst().getX() * Global.CELL_SIZE,
					(int) body.getFirst().getY() * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, 10, 10);
		}
	}

	public boolean isEatSelf() {
		System.out.println("Snake isn't eaten by itself");
		for (int i = 1; i < body.size(); i++) {
			if (body.get(i).equals(getHead())) {
				return true;
			}
		}
		return false;
	}

	public boolean isFoodInSnake(Point p) {
		for (Point b : body) {
			if (b.x == p.x && b.y == p.y) {
				return true;
			}
		}
		return false;
	}

	public void addSnakeListener(SnakeListener snakerListener) {
		this.snakeListener = snakerListener;
	}

	public void start() {
		new SnakeDrive().start();
	}

	public class SnakeDrive extends Thread {
		@Override
		public void run() {
			while (life) {
				try {
					sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (key) {
					move();
					System.out.println(sleep);
					snakeListener.snakeMove(Snake.this);
					try {
						sleep(sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	public void shoot() {
		if (body.size() >= 5) {
			body.removeLast();
		}
		System.out.println("shooting");
	}

	public boolean produceBarrier(LinkedList<Barriers> barriers, int type) {
		Point head = body.getFirst();
		for (int i = 0; i < barriers.size(); i++) {
			Barriers barrier = barriers.get(i);
			if (type == barrier.getFrom() && head.x == barrier.x && head.y == barrier.y) {
				return false;
			}
		}
		if (body.size() >= 6) {
			body.removeFirst();
			return true;
		}
		return false;
	}

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

}
