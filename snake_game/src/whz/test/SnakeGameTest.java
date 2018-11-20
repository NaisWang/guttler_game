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
		DoubleFood food = new DoubleFood();
		Snake_1 snake_1 = new Snake_1();
		Snake_2 snake_2 =new Snake_2();
		Bullets bullets = new Bullets();
		DoubleBarrier doubleBarrier = new DoubleBarrier();
		DoubleGamePanel doubleGamePanle = new DoubleGamePanel();
		DoubleController doubleController = new DoubleController(snake_1, snake_2, food, bullets, doubleGamePanle,doubleBarrier);
		JFrame jf = new JFrame();
		jf.add(doubleGamePanle);
		snake_1.addSnakeListener(doubleController);
		snake_2.addSnakeListener(doubleController);
		jf.addKeyListener(doubleController);
		
		doubleGamePanle.requestFocus();
		doubleController.start();
		jf.setSize(1140, 800);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
}






