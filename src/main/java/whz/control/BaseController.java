package whz.control;

import whz.entity.Barrier;
import whz.entity.Food;
import whz.entity.StupidSnake;
import whz.util.Global;
import whz.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseController {

	GamePanel gamePanel = null;

	private int isDelete;
	private String tips;

	public BaseController() {
		gamePanel = new GamePanel();
	}

	public BaseController(int gameModel) {
		gamePanel = new GamePanel(gameModel);
	}

	public void init() {
		gamePanel.getFoods().clear();
		addRandomFood(5);

		List<StupidSnake> snakes = gamePanel.getSnakes();
		Point[] points = {new Point(1, 2), new Point(Global.DOUBLE_WIDTH - 2, 2)};
		int index = 0;
		for (StupidSnake stupidSnake : snakes) {
			stupidSnake.body.clear();
			stupidSnake.food = 0;
			if (index == 0) {
				stupidSnake.body.add(new Point(3, 15));
				stupidSnake.setDirection(Global.RIGHT);
				for (int i = 1; i < 4; i++) {
					stupidSnake.body.add(new Point(3 - i, 15));
				}
			} else {
				stupidSnake.setDirection(Global.LEFT);
				stupidSnake.body.add(new Point(70, 15));
				for (int i = 1; i < 4; i++) {
					stupidSnake.body.add(new Point(70 + i, 15));
				}
			}
			index++;
		}


	}

	// 移动+吃食物
	public void snakesMove() {
		var foods = gamePanel.getFoods();
		var snakes = gamePanel.getSnakes();
		// 移动，吃食物
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

	// 主逻辑，检查蛇撞墙、撞蛇等，移动蛇
	public void checkAndPaint() {
		var foods = gamePanel.getFoods();
		var snakes = gamePanel.getSnakes();
		var barriers = gamePanel.getBarriers();
		// 移动，吃食物
		snakesMove();
		List<StupidSnake> dieSnake = new ArrayList<>();

		// 判断是否碰到别的蛇
		for (var snake : snakes) {
			if (snake.isEatSelf()) {
				snake.life = false;
				dieSnake.add(snake);
				break;
			}

			// 判断是否碰到墙或障碍物
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

				// 碰到Snake2了
				if (snake2.isHit(head)) {
					snake.life = false;
					dieSnake.add(snake);
					break;
				}
			}
		}

		// 判断是否死亡，死亡则结束
		if (dieSnake.size() > 0) {
			tips = "";
			for (var snake : dieSnake) {
				tips += snake.name;
				tips += ',';
			}

			tips += "fail";
			System.out.print(tips);
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					@Override
					public void run() {
						isDelete = JOptionPane.showConfirmDialog(null, tips + ", 是否要重新开始游戏", "提示", JOptionPane.YES_NO_OPTION);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (isDelete == JOptionPane.YES_OPTION) {
				init();
				return;
			} else {
				System.exit(0);
			}
		}

		// 判断是否要生成食物
		if (foods.size() <= 0) {
			addRandomFood(1);
		}
		// 分数到达一定程度，赢了
		for (var snake : snakes) {
			if (snake.food >= 100) {
				String tips = "";
				tips += snake.name;
				tips += ',';
				tips += "success";
				System.out.print(tips);
				int isDelete = JOptionPane.showConfirmDialog(null, tips + ", 是否要重新开始游戏", "提示", JOptionPane.YES_NO_OPTION);
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

	// 随机生成食物
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
		// 碰到蛇了
		for (var snake : snakes) {
			if (snake.isHit(p)) {
				return false;
			}
		}

		// 碰到刚随机的食物
		for (var food : foods) {
			if (food.equals(p)) {
				return false;
			}
		}

		// 碰到以前生成的食物
		for (var food : gamePanel.getFoods()) {
			if (food.isHit(p)) {
				return false;
			}
		}

		// 碰到障碍物
		for (var barrier : barriers) {
			if (barrier.isHit(p)) {
				return false;
			}
		}

		foods.add(p);
		return true;
	}
}
