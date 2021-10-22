package whz.test;

import javax.swing.JFrame;
import javax.swing.JTextField;

import whz.control.BaseController;
import whz.entity.*;
import whz.util.Global;
import whz.view.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SnakeGameTest {
	private static JTextField grade;

	public static void main(String[] args) {
		BaseController baseController = new BaseController();
		GamePanel gamePanel = baseController.getGamePanel();

		// 初始化蛇1
		var snake1 = new StupidSnake("玩家1", Color.black, new Point(3, 1));
		snake1.UP_KEY = KeyEvent.VK_W;
		snake1.DOWN_KEY = KeyEvent.VK_S;
		snake1.LEFT_KEY = KeyEvent.VK_A;
		snake1.RIGHT_KEY = KeyEvent.VK_D;
		snake1.setDirection(Global.RIGHT);
		for (int i = 1; i < 4; i++) {
			snake1.body.add(new Point(3 - i, 1));
		}

		// 初始化蛇2
		var snake2 = new StupidSnake("玩家2", Color.yellow, new Point(60, 1));
		snake2.setDirection(Global.LEFT);
		for (int i = 1; i < 4; i++) {
			snake2.body.add(new Point(60 + i, 1));
		}

		// 初始化障碍物
		Barrier barrier = new Barrier();

		gamePanel.addSnake(snake1);
//		gamePanel.addSnake(snake2);
		gamePanel.addBarrier(barrier);
		// 初始化UI
		JFrame jf = new JFrame();
		jf.add(gamePanel);

		// 往JFrame上添加键盘监听器
		var snakes = gamePanel.getSnakes();

		for (var snake : snakes) {
			jf.addKeyListener(snake.keyAdapter);
		}
		gamePanel.requestFocus();
		// 加一个食物
		baseController.addRandomFood(1);

		jf.setSize(Global.DOUBLE_WIDTH * 15, Global.DOUBLE_HEIGHT * 18);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

		while (true) {
		    baseController.checkAndPaint();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}






