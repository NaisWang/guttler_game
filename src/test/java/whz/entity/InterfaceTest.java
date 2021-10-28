package whz.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import whz.control.BaseController;
import whz.util.Global;
import whz.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author : whz
 */
public class InterfaceTest {

	private PrintStream console = null;
	private ByteArrayOutputStream bytes = null;
	private JFrame jf = null;
	private GamePanel gamePanel;
	private StupidSnake snake1;
	private StupidSnake snake2;

	void initSnake() {
		snake1 = new StupidSnake("snake1", Color.black, new Point(1, 3));
		snake2 = new StupidSnake("snake1", Color.black, new Point(2, 2));
	}

	void initGamePanel(GamePanel gamePanel) {
		// init foods
		gamePanel.addFood(new Food(1, 3));
		gamePanel.addFood(new Food(1, 5));

		// init barrier
		gamePanel.addBarrier(new Barrier());
		gamePanel.addBarrier(new Barrier());

		this.gamePanel = gamePanel;
	}

	void initJF() {
		jf = new JFrame();
		jf.add(gamePanel);
		jf.setSize(100, 100);
		jf.setFocusable(true);
		jf.setVisible(true);
	}


	@Test
	void showInterface() {
		assertDoesNotThrow(() ->
		{
			initGamePanel(new GamePanel());

			//init Snake
			initSnake();
			gamePanel.addSnake(snake1);
			gamePanel.addSnake(snake2);

			initJF();
			gamePanel.repaint();
			jf.dispose();

		});
	}

	void initConsoleDetect() {
		bytes = new ByteArrayOutputStream();// 把标准输出指定到ByteArrayOutputStream中
		console = System.out;// 获取System.out 输出流的句柄
		System.setOut(new PrintStream(bytes));// 将原本输出到控制台Console的字符流重定向到bytes
	}

	//测试checkoutAndPaint中的吃到自己
	@Test
	void checkAndPaintForEatOtherSnake() {
		BaseController baseController = new BaseController();
		initGamePanel(baseController.getGamePanel());

		//init Snake
		initSnake();
		snake1.body.add(new Point(1, 3));
		snake1.body.add(new Point(1, 4));
		snake1.body.add(new Point(2, 4));
		snake1.body.add(new Point(2, 3));
		snake1.body.add(new Point(1, 3));
		gamePanel.addSnake(snake1);
		gamePanel.addSnake(snake2);

		initConsoleDetect();

		baseController.checkAndPaint();

		Assertions.assertEquals("snake1,fail", bytes.toString());
	}


	//测试checkoutAndPaint中的是否碰到障碍物
	@Test
	void checkAndPaintForMeetBarriers() {
		BaseController baseController = new BaseController();
		initGamePanel(baseController.getGamePanel());

		//init Snake
		initSnake();
		snake1.body.getFirst().x = 0;
		gamePanel.addSnake(snake1);
		gamePanel.addSnake(snake2);

		initConsoleDetect();

		baseController.checkAndPaint();

		Assertions.assertEquals("snake1,fail", bytes.toString());
	}

	//测试checkoutAndPaint中的碰到Snake2
	@Test
	void checkAndPaintForMeetSnake2() {
		BaseController baseController = new BaseController();
		initGamePanel(baseController.getGamePanel());

		//init Snake
		initSnake();
		snake2.body.add(new Point(2, 3));
		snake1.body.getFirst().y = 2;
		snake1.body.getFirst().x = 2;
		gamePanel.addSnake(snake1);
		gamePanel.addSnake(snake2);

		initConsoleDetect();

		baseController.checkAndPaint();

		Assertions.assertEquals("snake1,snake1,fail", bytes.toString());
	}

	//测试checkoutAndPaint中的自动生成食物
	@Test
	void checkAndPaintForGenerateFood() {
		BaseController baseController = new BaseController();
		initGamePanel(baseController.getGamePanel());

		//init Snake
		initSnake();
		gamePanel.addSnake(snake1);
		gamePanel.addSnake(snake2);

		initConsoleDetect();

		snake1.food = 99;
		baseController.checkAndPaint();
		Assertions.assertNotEquals("snake1,success", bytes.toString());

		initConsoleDetect();
		snake1.food = 100;
		baseController.checkAndPaint();
		Assertions.assertEquals("snake1,success", bytes.toString());

		initConsoleDetect();
		snake1.food = 101;
		baseController.checkAndPaint();
		Assertions.assertEquals("snake1,success", bytes.toString());

	}

	//测试checkoutAndPaint中的判断是否成功
	@Test
	void checkAndPaintForJudgeSuccess() {
		BaseController baseController = new BaseController();
		initGamePanel(baseController.getGamePanel());

		//init Snake
		initSnake();
		gamePanel.addSnake(snake1);
		gamePanel.addSnake(snake2);

		gamePanel.getFoods().clear();

		baseController.checkAndPaint();

		Assertions.assertEquals(1, gamePanel.getFoods().size());
	}

	//测试生成食物时碰到随机生成的食物
	@Test
	void judgeGenerateFoodMeetSnake() {
		BaseController baseController = new BaseController();
		ArrayList stupidSnakeList = new ArrayList<StupidSnake>();
		stupidSnakeList.add(new StupidSnake("snake1", Color.white, new Point(1,3)));

		ArrayList<Food> foods = new ArrayList<>();
		foods.add(new Food(2,5));

		Assertions.assertFalse(baseController.judgeGenerateFoodIsEnable(new Food(1,3), stupidSnakeList, null, foods));
	}

	//测试生成食物时碰到随机生成的食物
	@Test
	void judgeGenerateFoodMeetAgoFood() {

		BaseController baseController = new BaseController();
		ArrayList stupidSnakeList = new ArrayList<StupidSnake>();
		stupidSnakeList.add(new StupidSnake("snake1", Color.white, new Point(1,3)));

		ArrayList<Food> foods = new ArrayList<>();
		foods.add(new Food(2,5));

		Assertions.assertFalse(baseController.judgeGenerateFoodIsEnable(new Food(2,5), stupidSnakeList, null, foods));
	}

	//测试生成食物时生成gamePanel中含有的食物
	@Test
	void judgeGenerateFoodAlreadyExist() {
		BaseController baseController = new BaseController();
		baseController.getGamePanel().getFoods().add(new Food(1,5));
		ArrayList stupidSnakeList = new ArrayList<StupidSnake>();
		stupidSnakeList.add(new StupidSnake("snake1", Color.white, new Point(1,3)));

		ArrayList<Food> foods = new ArrayList<>();
		foods.add(new Food(2, 6));

		Assertions.assertFalse(baseController.judgeGenerateFoodIsEnable(new Food(1,5), stupidSnakeList, null, foods));
	}

	//测试生成的食物在障碍物上
	@Test
	void judgeGenerateFoodInBarriers() {
		BaseController baseController = new BaseController();
		initGamePanel(baseController.getGamePanel());
		ArrayList stupidSnakeList = new ArrayList<StupidSnake>();
		stupidSnakeList.add(new StupidSnake("snake1", Color.white, new Point(1,3)));

		ArrayList<Food> foods = new ArrayList<>();
		foods.add(new Food(2, 6));

		Assertions.assertFalse(baseController.judgeGenerateFoodIsEnable(new Food(0,0), stupidSnakeList, gamePanel.getBarriers(), foods));
	}


}
