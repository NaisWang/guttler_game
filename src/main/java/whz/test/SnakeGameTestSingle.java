package whz.test;

import whz.control.DoubleController;
import whz.control.SingleController;
import whz.entity.*;
import whz.view.DoubleGamePanel;
import whz.view.SingleGamePanel;

import javax.swing.*;

public class SnakeGameTestSingle {
	private static JTextField grade;

	public static void main(String[] args) {
		// 创建食物对象
		SingleFood food = new SingleFood();

		// 创建蛇对象
		Snake_1 snake_1 = new Snake_1();




		//创建障碍物对象
		Barrier Barrier = new Barrier();

		// 创建游戏面板
		SingleGamePanel singleGamePanle = new SingleGamePanel();

		// 创建游戏控制器
		SingleController singleController = new SingleController(snake_1, food, Barrier, singleGamePanle);

		JFrame jf = new JFrame();
		jf.add(singleGamePanle);

		// 往2个蛇上添加控制器
		snake_1.addSnakeListener(singleController);


		// 往JFrame上添加键盘监听器
		jf.addKeyListener(singleController);

		singleGamePanle.requestFocus();
		singleController.startGame();

		jf.setSize(1140, 800);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

	}

}






