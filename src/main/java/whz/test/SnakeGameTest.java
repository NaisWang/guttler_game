package whz.test;

import javax.swing.JFrame;
import javax.swing.JTextField;

import whz.control.DoubleController;
import whz.entity.Bullets;
import whz.entity.DoubleBarrier;
import whz.entity.DoubleFood;
import whz.entity.Snake_1;
import whz.entity.Snake_2;
import whz.view.DoubleGamePanel;

public class SnakeGameTest {
	private static JTextField grade;

	public static void main(String[] args) {
		// 创建食物对象
		DoubleFood food = new DoubleFood();

		// 创建蛇对象
		Snake_1 snake_1 = new Snake_1();
		Snake_2 snake_2 = new Snake_2();

		//创建子弹对象
		Bullets bullets = new Bullets();

		//创建障碍物对象
		DoubleBarrier doubleBarrier = new DoubleBarrier();

		// 创建游戏面板
		DoubleGamePanel doubleGamePanle = new DoubleGamePanel();

		// 创建游戏控制器
		DoubleController doubleController = new DoubleController(snake_1, snake_2, food, bullets, doubleGamePanle, doubleBarrier);

		JFrame jf = new JFrame();
		jf.add(doubleGamePanle);

		// 往2个蛇上添加控制器
		snake_1.addSnakeListener(doubleController);
		snake_2.addSnakeListener(doubleController);

		// 往JFrame上添加键盘监听器
		jf.addKeyListener(doubleController);

		doubleGamePanle.requestFocus();
		doubleController.start();

		jf.setSize(1140, 800);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

	}

}






