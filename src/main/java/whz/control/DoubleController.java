package whz.control;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

import whz.entity.Bullets;
import whz.entity.Bullets.Bullet;
import whz.entity.DoubleBarrier;
import whz.entity.DoubleBarrier.Barriers;
import whz.entity.DoubleFood;
import whz.entity.Snake;
import whz.entity.Snake_1;
import whz.entity.Snake_2;
import whz.listener.SnakeListener;
import whz.util.Global;
import whz.view.DoubleGamePanel;

public class DoubleController extends KeyAdapter implements SnakeListener {
	
	boolean snake_1Up = false;
	boolean snake_1Down = false;
	boolean snake_1Left = false;
	boolean snake_1Right = false;
	boolean snake_2Up = false;
	boolean snake_2Down = false;
	boolean snake_2Left = false;
	boolean snake_2Right = false;
	Snake_1 snake_1;
	Snake_2 snake_2;
	DoubleFood food;
	Bullets bullets;
	DoubleBarrier doubleBarrier;
	DoubleGamePanel doubleGamePanle;

	public DoubleController(Snake_1 snake_1, Snake_2 snake_2, DoubleFood food, Bullets bullets,
			DoubleGamePanel doubleGamePanle, DoubleBarrier doubleBarrier) {
		super();
		this.snake_1 = snake_1;
		this.snake_2 = snake_2;
		this.food = food;
		this.bullets = bullets;
		this.doubleBarrier = doubleBarrier;
		this.doubleGamePanle = doubleGamePanle;
	}

	public void start() {
		snake_1.start();
		snake_2.start();
		food.addFood(getPoint());
	}

	// �������5��ʳ��
	public ArrayList<Point> getPoint() {
		ArrayList<Point> point = new ArrayList(5);
		int x;
		int y;
		for (int i = 1; i <= 5; i++) {
			while (true) {
				x = new Random().nextInt(Global.DOUBLE_WIDTH - 1);
				y = new Random().nextInt(Global.DOUBLE_HEIGHT - 1);
				// �ж�������ɵ�ʳ�������������߽ڵ���
				for (int t = 0; t < snake_1.body.size(); t++) {
					if ( t < snake_1.body.size()&&snake_1.body.get(t).x == x && snake_1.body.get(t).y == y) {
						continue;
					}
				}
				for (int t = 0; t < snake_2.body.size(); t++) {
					if (t < snake_2.body.size()&&snake_2.body.get(t).x == x && snake_2.body.get(t).y == y) {
						continue;
					}
				}

				// �ж�������ɵ�ʳ���������������е�ʳ����
				for (int t = 0; t < food.getFood().size(); t++) {
					if (t < food.getFood().size() && food.getFood().get(t).x == x && food.getFood().get(t).y == y) {
						continue;
					}
				}

				break;
			}
			point.add(new Point(x, y));
		}
		return point;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_W:
			snake_1Up = true;
			break;
		case KeyEvent.VK_S:
			snake_1Down = true;
			break;
		case KeyEvent.VK_A:
			snake_1Left = true;
			break;
		case KeyEvent.VK_D:
			snake_1Right = true;
			break;
		case KeyEvent.VK_UP:
			System.out.println("up");
			snake_2Up = true;
			break;
		case KeyEvent.VK_DOWN:
			snake_2Down = true;
			break;
		case KeyEvent.VK_LEFT:
			snake_2Left = true;
			break;
		case KeyEvent.VK_RIGHT:
			snake_2Right = true;
			break;
		case KeyEvent.VK_F:
			if (snake_1.body.size() >= 5) {
				snake_1.shoot();
				bullets.getBullet().add(bullets.new Bullet(snake_1.oldDirection, 1, snake_1.body.getFirst()));
			}
			break;
		case KeyEvent.VK_G:
			if (snake_1.produceBarrier()) {
				doubleBarrier.barriers.add(doubleBarrier.new Barriers(1, snake_1.body.getFirst()));
			}
			break;
		case KeyEvent.VK_H:
			snake_1.setSleep(30);
			break;
		case KeyEvent.VK_N:
			if (snake_2.body.size() >= 5) {
				snake_2.shoot();
				bullets.getBullet().add(bullets.new Bullet(snake_2.oldDirection, 2, snake_2.body.getFirst()));
			}
			break;
		case KeyEvent.VK_M:
			if (snake_2.produceBarrier()) {
				doubleBarrier.barriers.add(doubleBarrier.new Barriers(2, snake_2.body.getFirst()));
			}
			break;
		case KeyEvent.VK_COMMA:
			snake_2.setSleep(30);
			break;
		}
		if (snake_1Up == true && snake_1Down == false && snake_1Left == false && snake_1Right == false) {
			snake_1.changeDirection(Global.UP);
		} else if (snake_1Up == false && snake_1Down == true && snake_1Left == false && snake_1Right == false) {
			snake_1.changeDirection(Global.DOWN);
		} else if (snake_1Up == false && snake_1Down == false && snake_1Left == true && snake_1Right == false) {
			snake_1.changeDirection(Global.LEFT);
		} else if (snake_1Up == false && snake_1Down == false && snake_1Left == false && snake_1Right == true) {
			snake_1.changeDirection(Global.RIGHT);
		} else if (snake_1Up == true && snake_1Down == false && snake_1Left == true && snake_1Right == false) {
			snake_1.changeDirection(Global.UP_LEFT);
		} else if (snake_1Up == true && snake_1Down == false && snake_1Left == false && snake_1Right == true) {
			snake_1.changeDirection(Global.UP_RIGHT);
		} else if (snake_1Up == false && snake_1Down == true && snake_1Left == true && snake_1Right == false) {
			snake_1.changeDirection(Global.DOWN_LEFT);
		} else if (snake_1Up == false && snake_1Down == true && snake_1Left == false && snake_1Right == true) {
			snake_1.changeDirection(Global.DOWN_RIGHT);
		}

		if (snake_2Up == true && snake_2Down == false && snake_2Left == false && snake_2Right == false) {
			snake_2.changeDirection(Global.UP);
		} else if (snake_2Up == false && snake_2Down == true && snake_2Left == false && snake_2Right == false) {
			snake_2.changeDirection(Global.DOWN);
		} else if (snake_2Up == false && snake_2Down == false && snake_2Left == true && snake_2Right == false) {
			snake_2.changeDirection(Global.LEFT);
		} else if (snake_2Up == false && snake_2Down == false && snake_2Left == false && snake_2Right == true) {
			snake_2.changeDirection(Global.RIGHT);
		} else if (snake_2Up == true && snake_2Down == false && snake_2Left == true && snake_2Right == false) {
			snake_2.changeDirection(Global.UP_LEFT);
		} else if (snake_2Up == true && snake_2Down == false && snake_2Left == false && snake_2Right == true) {
			snake_2.changeDirection(Global.UP_RIGHT);
		} else if (snake_2Up == false && snake_2Down == true && snake_2Left == true && snake_2Right == false) {
			snake_2.changeDirection(Global.DOWN_LEFT);
		} else if (snake_2Up == false && snake_2Down == true && snake_2Left == false && snake_2Right == true) {
			snake_2.changeDirection(Global.DOWN_RIGHT);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_W:
			snake_1Up = false;
			break;
		case KeyEvent.VK_S:
			snake_1Down = false;
			break;
		case KeyEvent.VK_A:
			snake_1Left = false;
			break;
		case KeyEvent.VK_D:
			snake_1Right = false;
			break;
		case KeyEvent.VK_UP:
			snake_2Up = false;
			break;
		case KeyEvent.VK_DOWN:
			snake_2Down = false;
			break;
		case KeyEvent.VK_LEFT:
			snake_2Left = false;
			break;
		case KeyEvent.VK_RIGHT:
			snake_2Right = false;
			break;
		case KeyEvent.VK_H:
			snake_1.sleep = 150;
		case KeyEvent.VK_COMMA:
			snake_2.sleep = 150;
		}

		if (snake_1Up == true && snake_1Down == false && snake_1Left == false && snake_1Right == false) {
			snake_1.changeDirection(Global.UP);
		} else if (snake_1Up == false && snake_1Down == true && snake_1Left == false && snake_1Right == false) {
			snake_1.changeDirection(Global.DOWN);
		} else if (snake_1Up == false && snake_1Down == false && snake_1Left == true && snake_1Right == false) {
			snake_1.changeDirection(Global.LEFT);
		} else if (snake_1Up == false && snake_1Down == false && snake_1Left == false && snake_1Right == true) {
			snake_1.changeDirection(Global.RIGHT);
		} else if (snake_1Up == true && snake_1Down == false && snake_1Left == true && snake_1Right == false) {
			snake_1.changeDirection(Global.UP_LEFT);
		} else if (snake_1Up == true && snake_1Down == false && snake_1Left == false && snake_1Right == true) {
			snake_1.changeDirection(Global.UP_RIGHT);
		} else if (snake_1Up == false && snake_1Down == true && snake_1Left == true && snake_1Right == false) {
			snake_1.changeDirection(Global.DOWN_LEFT);
		} else if (snake_1Up == false && snake_1Down == true && snake_1Left == false && snake_1Right == true) {
			snake_1.changeDirection(Global.DOWN_RIGHT);
		}

		if (snake_2Up == true && snake_2Down == false && snake_2Left == false && snake_2Right == false) {
			snake_2.changeDirection(Global.UP);
		} else if (snake_2Up == false && snake_2Down == true && snake_2Left == false && snake_2Right == false) {
			snake_2.changeDirection(Global.DOWN);
		} else if (snake_2Up == false && snake_2Down == false && snake_2Left == true && snake_2Right == false) {
			snake_2.changeDirection(Global.LEFT);
		} else if (snake_2Up == false && snake_2Down == false && snake_2Left == false && snake_2Right == true) {
			snake_2.changeDirection(Global.RIGHT);
		} else if (snake_2Up == true && snake_2Down == false && snake_2Left == true && snake_2Right == false) {
			snake_2.changeDirection(Global.UP_LEFT);
		} else if (snake_2Up == true && snake_2Down == false && snake_2Left == false && snake_2Right == true) {
			snake_2.changeDirection(Global.UP_RIGHT);
		} else if (snake_2Up == false && snake_2Down == true && snake_2Left == true && snake_2Right == false) {
			snake_2.changeDirection(Global.DOWN_LEFT);
		} else if (snake_2Up == false && snake_2Down == true && snake_2Left == false && snake_2Right == true) {
			snake_2.changeDirection(Global.DOWN_RIGHT);
		}
	}

	/*
	 ���ƶ�
	 */
	@Override
	public void snakeMove(Snake snake) {
		// �ж���ͷ�Ƿ�Ե�ʳ��
		if (food.isEatenBySnake(snake.getHead())) {
			// �����ͷ�Ƿ�Ե�ʳ��
			snake.eatFood();
			//����ڳ���ʳ������С��6��������������ʳ��
			if (food.getFood().size() <= 5) {
				food.addFood(getPoint());
			}
		}

		// �ж��Ƿ�����
		snake_1.isShoot(bullets, 1);
		snake_2.isShoot(bullets, 2);

		// �ж��Ƿ������ϰ���
		snake_1.isEatenBarrier(doubleBarrier.barriers,1);
		snake_2.isEatenBarrier(doubleBarrier.barriers,2);

		// �ж����Ƿ�Ե��Լ�
		if(snake_1.isEatSelf()) {
			// ����Ե��Լ�����ȥ��2���ڵ�
			snake_1.body.removeLast();
			snake_1.body.removeLast();
		}
		if(snake_2.isEatSelf()) {
			snake_2.body.removeLast();
			snake_2.body.removeLast();
		}

		if(snake_1.body.size()==3) {
			snake_1.life=false;
			int choice = JOptionPane.showConfirmDialog(null, "���1����, �Ƿ������Ϸ��");
			System.exit(0);
		}
		if(snake_2.body.size()==3) {
			snake_2.life=false;
			System.out.println(snake_2.body.size());
			int choice = JOptionPane.showConfirmDialog(null, "���2����, �Ƿ������Ϸ��");
			System.exit(0);
		}
		// ˢ����Ϸ���
		doubleGamePanle.display(snake_1, snake_2, food, bullets, doubleBarrier);
	}
}
