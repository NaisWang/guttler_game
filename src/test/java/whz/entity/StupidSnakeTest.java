package whz.entity;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import whz.control.BaseController;
import whz.util.Global;
import whz.view.GamePanel;

import javax.lang.model.type.IntersectionType;
import javax.swing.*;
import javax.xml.validation.Validator;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class StupidSnakeTest {
	static int oldDirection, newDirection;
	static int food;
	static int sleep;
	static boolean life;
	static boolean key;
	static int oldbodysize;

	LinkedList<Point> body = new LinkedList();

	@BeforeClass
	public static void method() {
		StupidSnake snake = new StupidSnake("???1", Color.black, new Point(2, 0));
		snake.UP_KEY = KeyEvent.VK_W;
		snake.DOWN_KEY = KeyEvent.VK_S;
		snake.LEFT_KEY = KeyEvent.VK_A;
		snake.RIGHT_KEY = KeyEvent.VK_D;
	}


	@Test
	void getDirection() {
		StupidSnake snake = new StupidSnake("???1", Color.black, new Point(2, 0));
		snake.setDirection(Global.RIGHT);
		System.out.print(Global.RIGHT);
		Assertions.assertEquals(Global.RIGHT, snake.getDirection());
	}

	@Test
	void move() {
		StupidSnake snake = new StupidSnake("???1", Color.black, new Point(1, 0));
//        System.out.print(snake.body.size());
//        snake.body.add(new Point(0,0));
		int size = snake.body.size();

		boolean eatFood = true;
		snake.move(eatFood);
		Assertions.assertEquals(size + 1, snake.body.size());
		size = snake.body.size();
		boolean eatFood1 = false;
		snake.move(eatFood1);
		Assertions.assertEquals(size, snake.body.size());
	}

	@Test
	void iseatenitself() {
		StupidSnake snake = new StupidSnake("???1", Color.black, new Point(1, 0));
		snake.body.addFirst(new Point(2, 2));
		snake.body.add(new Point(2, 1));
		snake.body.add(new Point(3, 1));
		snake.body.add(new Point(2, 2));
		snake.body.add(new Point(3, 2));
		Assertions.assertTrue(snake.isEatSelf());

	}

	@Test
	void keyListen() throws AWTException {
		// init
		JFrame jf = new JFrame();
		StupidSnake snake = new StupidSnake("snake1", Color.black, new Point(1, 2));

		snake.direction = Global.DOWN;

		jf.addKeyListener(snake.keyAdapter);

		jf.setSize(100,100);
		jf.setFocusable(true);
		jf.setVisible(true);

		HashMap<Integer, Integer> keyCorr = new HashMap<>();
		keyCorr.put(KeyEvent.VK_LEFT, Global.LEFT);
		keyCorr.put(KeyEvent.VK_UP, Global.UP);
		keyCorr.put(KeyEvent.VK_RIGHT, Global.RIGHT);
		keyCorr.put(KeyEvent.VK_DOWN, Global.DOWN);

		// ?????? Robot ??????
		Robot robot = new Robot();

		// ??????????????????????????????????????????
		robot.setAutoWaitForIdle(true);

		for (Integer key : keyCorr.keySet()) {
			// ???????????????
			robot.keyPress(key);

			Assertions.assertEquals(keyCorr.get(key), snake.direction);
		}
	}
}
