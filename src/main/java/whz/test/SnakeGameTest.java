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
		var snake1 = new StupidSnake("snake1", Color.black, new Point(3, 1));
		snake1.UP_KEY = KeyEvent.VK_W;
		snake1.DOWN_KEY = KeyEvent.VK_S;
		snake1.LEFT_KEY = KeyEvent.VK_A;
		snake1.RIGHT_KEY = KeyEvent.VK_D;
		snake1.setDirection(Global.RIGHT);
		for (int i = 1; i < 4; i++) {
			snake1.body.add(new Point(3 - i, 1));
		}

		// 初始化蛇2
		var snake2 = new StupidSnake("snake2", Color.yellow, new Point(70, 1));
		snake2.setDirection(Global.LEFT);
		for (int i = 1; i < 4; i++) {
			snake2.body.add(new Point(70 + i, 1));
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
//		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		// 添加食物
		baseController.addRandomFood(5);

//		jf.setContentPane();
//		System.out.println(jf.getBounds().getSize());
//		System.exit(0);
        jf.pack();
		var insets = jf.getInsets();
		System.out.println(insets);
		jf.setSize(Global.DOUBLE_WIDTH * Global.CELL_SIZE + insets.left + insets.right,
				Global.DOUBLE_HEIGHT * Global.CELL_SIZE + insets.top + insets.bottom);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

		gamePanel.repaint();

		while (true) {
		    if (!Global.BEGIN) {
				System.out.println(Global.BEGIN);
		    	continue;
			}

			if (gamePanel.getSnakes().size() < 2 && Global.DOUBLE) {
				gamePanel.addSnake(snake2);
				jf.addKeyListener(snake2.keyAdapter);
			}

			baseController.checkAndPaint();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}






