
package whz.entity;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import whz.test.SnakeGameTest;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.Arrays;
import java.util.Random;

/**
 * @author : whz
 */
public class ScoreTest extends AssertJSwingJUnitTestCase {

	private FrameFixture window;
	private Integer[] directions1 = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
	private Integer[] directions2 = {KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D};
	private Random random = new Random();

	@Override
	protected void onSetUp() {
		SnakeGameTest frame = GuiActionRunner.execute(() -> new SnakeGameTest());

		window = new FrameFixture(robot(), frame);

	}


	private void initOperate() {

		Runnable runnable1 = new Runnable() {
			@Override
			public void run() {
				while (true) {
					//window.pressKey(directions1[random.nextInt(4)]);
					window.robot().pressKey(directions1[random.nextInt(4)]);
				}
			}
		};


		Runnable runnable2 = new Runnable() {
			@Override
			public void run() {
				while (true) {
					window.pressKey(directions2[random.nextInt(4)]);
				}
			}
		};

		Runnable runnable3 = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try{
						window.optionPane().yesButton().click();
					}catch (Exception e){}
				}
			}
		};

		for (int i = 0; i < 30; i++) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (i % 3 == 0) {
				new Thread(runnable1).start();
			} else if (i % 3 == 1) {
				new Thread(runnable2).start();
			} else {
				new Thread(runnable3).start();
			}
		}
	}

	// 测试双人模式下吃到食物后，食物个数是否正常显示
	@Test
	public void DoubleScoreTest() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				initOperate();
			}
		}).start();
		try{
			Thread.sleep(3000);
		}catch (Exception e){

		}
		window.button("doubleBtn").click();

		int flag1 = 1;
		int flag2 = 1;
		while (true && flag1 != 2 && flag2 != 2) {
			Integer snake1Score = Integer.parseInt(window.textBox("scoreText").text().replace("snake1", "").replace("score", "").replace(":", "").replace(" ", ""));
			Integer snake2Score = Integer.parseInt(window.textBox("scoreTextTwo").text().replace("snake2", "").replace("score", "").replace(":", "").replace(" ", ""));


			String foodPoints = window.textBox("foodPoints").text();
			String snake1Points = window.textBox("snake1Points").text();
			String snake2Points = window.textBox("snake2Points").text();


			String snake1HeadPoint = snake1Points.split("-")[0];
			String snake2HeadPoint = snake2Points.split("-")[0];

			if (Arrays.asList(foodPoints.split("-")).contains(snake1HeadPoint)) {
				flag1 = 2;
				Assertions.assertEquals(snake1Score + 1, Integer.parseInt(window.textBox("scoreText").text().replace("snake1", "").replace("score", "").replace(":", "").replace(" ", "")));
			}

			if (Arrays.asList(foodPoints.split("-")).contains(snake2HeadPoint)) {
				flag1 = 2;
				Assertions.assertEquals(snake2Score + 1, Integer.parseInt(window.textBox("scoreTextTwo").text().replace("snake2", "").replace("score", "").replace(":", "").replace(" ", "")));
			}

		}
	}

	// 测试单人模式下吃到食物后，食物个数是否正常显示
	@Test
	public void SingleScoreTest() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				initOperate();
			}
		}).start();
		try{
			Thread.sleep(3000);
		}catch (Exception e){}

		window.button("singleBtn").click();

		int flag1 = 1;
		while (true && flag1 != 2) {
			Integer snake1Score = Integer.parseInt(window.textBox("scoreText").text().replace("snake1", "").replace("score", "").replace(":", "").replace(" ", ""));

			String foodPoints = window.textBox("foodPoints").text();
			String snake1Points = window.textBox("snake1Points").text();

			System.out.println(foodPoints);
			System.out.println(snake1Points);

			String snake1HeadPoint = snake1Points.split("-")[0];

			if (Arrays.asList(foodPoints.split("-")).contains(snake1HeadPoint)) {
				flag1 = 2;
				Assertions.assertEquals(snake1Score + 1, Integer.parseInt(window.textBox("scoreText").text().replace("snake1", "").replace("score", "").replace(":", "").replace(" ", "")));
			}
		}
	}

}
