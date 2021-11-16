package whz.test;

import javax.swing.JFrame;
import javax.swing.JTextField;

import whz.control.BaseController;
import whz.entity.*;
import whz.util.Global;
import whz.view.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SnakeGameTest extends JFrame{
	private static JTextField grade;

  public 	BaseController baseController = null;
  public	GamePanel gamePanel = null;

	public SnakeGameTest() {
		baseController = new BaseController();
		gamePanel = baseController.getGamePanel();

		// 初始化蛇1
		var snake1 = new StupidSnake("snake1", Color.black, new Point(3, 15));
		snake1.UP_KEY = KeyEvent.VK_W;
		snake1.DOWN_KEY = KeyEvent.VK_S;
		snake1.LEFT_KEY = KeyEvent.VK_A;
		snake1.RIGHT_KEY = KeyEvent.VK_D;
		snake1.setDirection(Global.RIGHT);
		for (int i = 1; i < 4; i++) {
			snake1.body.add(new Point(3 - i, 15));
		}

		// 初始化蛇2
		var snake2 = new StupidSnake("snake2", Color.yellow, new Point(70, 15));
		snake2.setDirection(Global.LEFT);
		for (int i = 1; i < 4; i++) {
			snake2.body.add(new Point(70 + i, 15));
		}

		// 初始化障碍物
		Barrier barrier = new Barrier();

		gamePanel.addSnake(snake1);
		gamePanel.addBarrier(barrier);
		// 初始化UI
		add(gamePanel);

		// 往JFrame上添加键盘监听器
		var snakes = gamePanel.getSnakes();

		for (var snake : snakes) {
			addKeyListener(snake.keyAdapter);
		}
//		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		// 添加食物
		baseController.addRandomFood(5);

//		setContentPane();
//		System.out.println(getBounds().getSize());
//		System.exit(0);
		pack();
		var insets = getInsets();
		System.out.println(insets);
		setSize(Global.DOUBLE_WIDTH * Global.CELL_SIZE + insets.left + insets.right,
				Global.DOUBLE_HEIGHT * Global.CELL_SIZE + insets.top + insets.bottom);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		gamePanel.repaint();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (!Global.BEGIN) {
						System.out.print("");
						continue;
					}

					if (gamePanel.getSnakes().size() < 2 && Global.DOUBLE) {
						gamePanel.addSnake(snake2);
						addKeyListener(snake2.keyAdapter);
					}

					baseController.checkAndPaint();
					try {
						//System.out.println(Global.DIFF);
						switch (Global.DIFF) {
							case 1:
								Thread.sleep(100);
								break;
							case 2:
								Thread.sleep(50);
								break;
							case 0:
							default:
								Thread.sleep(150);
								break;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();

	}

	public static void main(String[] args) {
		new SnakeGameTest();
	}

}






