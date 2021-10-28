package whz.control;

import whz.entity.Barrier;
import whz.entity.Food;
import whz.entity.StupidSnake;
import whz.util.Global;
import whz.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseController {
	GamePanel gamePanel = new GamePanel();

	public void init() {
		gamePanel.getFoods().clear();
		addRandomFood(5);

		List<StupidSnake> snakes = gamePanel.getSnakes();
		Point[] points = {new Point(1, 2), new Point(Global.DOUBLE_WIDTH - 2, 2)};
		int index = 0;
		for (StupidSnake stupidSnake : snakes) {
			stupidSnake.body.clear();
			if (index == 0) {
				stupidSnake.body.add(new Point(3, 1));
				stupidSnake.setDirection(Global.RIGHT);
				for (int i = 1; i < 4; i++) {
					stupidSnake.body.add(new Point(3 - i, 1));
				}
			} else {
				stupidSnake.setDirection(Global.LEFT);
				stupidSnake.body.add(new Point(70, 1));
				for (int i = 1; i < 4; i++) {
					stupidSnake.body.add(new Point(70 + i, 1));
				}
			}
			index++;
		}


	}

	// �ƶ�+��ʳ��
	public void snakesMove() {
		var foods = gamePanel.getFoods();
		var snakes = gamePanel.getSnakes();
		// �ƶ�����ʳ��
		for (var snake : snakes) {
			var eatFood = false;
			for (var food : foods) {
				if (food.isEatenBySnake(snake.getNext())) {
					eatFood = true;
					foods.remove(food);
					break;
				}
			}
			snake.move(eatFood);
			//System.out.println(snake.name + ", " + snake.getHead().x + ", " + snake.getHead().y);
		}
	}

	// ���߼��������ײǽ��ײ�ߵȣ��ƶ���
	public void checkAndPaint() {
		var foods = gamePanel.getFoods();
		var snakes = gamePanel.getSnakes();
		var barriers = gamePanel.getBarriers();
		// �ƶ�����ʳ��
		snakesMove();
		List<StupidSnake> dieSnake = new ArrayList<>();

		// �ж��Ƿ����������
		for (var snake : snakes) {
			if (snake.isEatSelf()) {
				snake.life = false;
				dieSnake.add(snake);
				break;
			}

			// �ж��Ƿ�����ǽ���ϰ���
			for (var barrier : barriers) {
				if (barrier.isHit(snake.getHead())) {
					snake.life = false;
					dieSnake.add(snake);
					break;
				}
			}


			var head = snake.getHead();
			for (var snake2 : snakes) {
				if (snake2 == snake) {
					continue;
				}

				// ����Snake2��
				if (snake2.isHit(head)) {
					snake.life = false;
					dieSnake.add(snake);
					break;
				}
			}
		}

		// �ж��Ƿ����������������
		if (dieSnake.size() > 0) {
			String tips = "";
			for (var snake : dieSnake) {
				tips += snake.name;
				tips += ',';
			}

			tips += "fail";
			System.out.print(tips);
			int isDelete = JOptionPane.showConfirmDialog(null, tips + ", �Ƿ�Ҫ���¿�ʼ��Ϸ", "��ʾ", JOptionPane.YES_NO_OPTION);
			if (isDelete == JOptionPane.YES_OPTION) {
				init();
				return;
			} else {
				System.exit(0);
			}
		}

		// �ж��Ƿ�Ҫ����ʳ��
		if (foods.size() <= 0) {
			addRandomFood(1);
		}
		// ��������һ���̶ȣ�Ӯ��
		for (var snake : snakes) {
			if (snake.food >= 10) {
				String tips = "";
				tips += snake.name;
				tips += ',';
				tips += "Ӯ��";
				//gamePanel.repaint();
				int isDelete = JOptionPane.showConfirmDialog(null, tips + ", �Ƿ�Ҫ���¿�ʼ��Ϸ", "��ʾ", JOptionPane.YES_NO_OPTION);
				if (isDelete == JOptionPane.YES_OPTION) {
					init();
					return;
				} else {
					System.exit(0);
				}
			}
		}
		gamePanel.repaint();
	}

	// �������ʳ��
	public List<Food> generateFood(int num) {
		var foods = new ArrayList<Food>();
		var snakes = gamePanel.getSnakes();
		var barriers = gamePanel.getBarriers();
		for (int i = 0; i < num; i++) {
			boolean flag = false;
			while (!flag) {
				Food p = randomGenerateFoodCoordinate();
				flag = judgeGenerateFoodIsEnable(p, snakes, barriers, foods);
			}
		}
		return foods;
	}

	public void addRandomFood(int num) {
		gamePanel.getFoods().addAll(generateFood(num));
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	private Food randomGenerateFoodCoordinate() {
		return new Food(new Random().nextInt(Global.DOUBLE_WIDTH - 1), new Random().nextInt(Global.DOUBLE_HEIGHT - 1));
	}

	public Boolean judgeGenerateFoodIsEnable(Food p, List<StupidSnake> snakes, List<Barrier> barriers, ArrayList<Food> foods) {
		// ��������
		for (var snake : snakes) {
			if (snake.isHit(p)) {
				return false;
			}
		}

		// �����������ʳ��
		for (var food : foods) {
			if (food.equals(p)) {
				return false;
			}
		}

		// ������ǰ���ɵ�ʳ��
		for (var food : gamePanel.getFoods()) {
			if (food.isHit(p)) {
				return false;
			}
		}

		// �����ϰ���
		for (var barrier : barriers) {
			if (barrier.isHit(p)) {
				return false;
			}
		}

		foods.add(p);
		return true;
	}
}
