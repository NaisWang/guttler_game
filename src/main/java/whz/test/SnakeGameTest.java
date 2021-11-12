package whz.test;

import javax.swing.JFrame;
import javax.swing.JTextField;

import whz.control.BaseController;
import whz.entity.*;
import whz.util.Global;
import whz.view.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SnakeGameTest extends JFrame {
	private static JTextField grade;

	public 	BaseController baseController = new BaseController();
	public	GamePanel gamePanel = baseController.getGamePanel();
 	public SnakeGameTest() {

		// ��ʼ����1
		var snake1 = new StupidSnake("snake1", Color.black, new Point(3, 1));
		snake1.UP_KEY = KeyEvent.VK_W;
		snake1.DOWN_KEY = KeyEvent.VK_S;
		snake1.LEFT_KEY = KeyEvent.VK_A;
		snake1.RIGHT_KEY = KeyEvent.VK_D;
		snake1.setDirection(Global.RIGHT);
		for (int i = 1; i < 4; i++) {
			snake1.body.add(new Point(3 - i, 1));
		}



		// ��ʼ���ϰ���
		Barrier barrier = new Barrier();

		gamePanel.addSnake(snake1);
		gamePanel.addBarrier(barrier);
		// ��ʼ��UI
//		JFrame jf = new JFrame();
		add(gamePanel);

		// ��JFrame����Ӽ��̼�����
		var snakes = gamePanel.getSnakes();

		for (var snake : snakes) {
			addKeyListener(snake.keyAdapter);
		}
//		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		// ���ʳ��
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


	}

	public void addSnake2(){
		// ��ʼ����2
		var snake2 = new StupidSnake("snake2", Color.yellow, new Point(70, 1));
		snake2.setDirection(Global.LEFT);
		for (int i = 1; i < 4; i++) {
			snake2.body.add(new Point(70 + i, 1));
		}

		gamePanel.addSnake(snake2);
		addKeyListener(snake2.keyAdapter);
	}

}






