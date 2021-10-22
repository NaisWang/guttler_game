package whz.control;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import whz.entity.Barrier;
import whz.entity.SingleFood;
import whz.entity.Snake;
import whz.listener.SnakeListener;
import whz.util.Global;
import whz.view.SingleGamePanel;

/*
 * Controller
 * 
 * Function:
 * 	1.Control the snake, the food, the barrier, the gamePanel.
 * 	
 */

public class SingleController extends KeyAdapter implements SnakeListener {
	private Snake snake;
	private SingleFood food;
	private Barrier barrier;
	private SingleGamePanel gamePanel;
	private int nowSleep;
	private static final String EASY = "easy";
	private static final String NORMAL = "normal";
	private static final String DIFFICULT = "difficult";

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			snake.changeDirection(Global.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Global.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Global.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Global.RIGHT);
			break;
		case KeyEvent.VK_SPACE:
			nowSleep = snake.getSleep();
			if (nowSleep != 0) {
				snake.setSleep(nowSleep - 100);
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (KeyEvent.VK_SPACE == e.getKeyCode()) {
			changeRank((String) gamePanel.rank.getSelectedItem());
		}
	}

	@Override
	public void snakeMove(Snake snake) {
		System.out.println("Judge whether the sanke touches the body, food or barrier");
		// display snake, food, barrier after judging
		if (food.isEatenBySnake(snake)) {
			snake.eatFood();
			food.addFood(getPoint());
			changeGrade(snake.getFood());
		}
		if (barrier.isEatenBySnake(snake)) {
			snake.setLife(false);
			int choice = JOptionPane.showConfirmDialog(null, "GameOver, 是否继续游戏？");
			if (choice == 0) {
				init();
			}
		}
		if (snake.isEatSelf()) {
			snake.setLife(false);
			int choice = JOptionPane.showConfirmDialog(null, "GameOver, 是否继续游戏？");
			if (choice == 0) {
				init();
			}
		}
		gamePanel.display(snake, food, barrier);
	}

	public SingleController(Snake snake, SingleFood food, Barrier barrier, SingleGamePanel gamePanel) {
		super();
		this.snake = snake;
		this.food = food;
		this.barrier = barrier;
		this.gamePanel = gamePanel;
	}

	public void startGame() {
		//snake.start();
		food.addFood(getPoint());
	}

	public void changeRank(String rank) {
		switch (rank) {
		case EASY:
			snake.setSleep(300);
			break;
		case NORMAL:
			snake.setSleep(100);
			break;
		case DIFFICULT:
			snake.setSleep(0);
			break;
		}
	}

	public void init() {
		changeRank((String) gamePanel.rank.getSelectedItem());
		//snake.init();
		changeRank();
		gamePanel.grade.setText("0");
	}

	public void changeGrade(int grade) {
		snake.setFood(grade + 1);
		gamePanel.grade.setText(String.valueOf(snake.getFood()));
	}

	public void changeRank() {
		int[] num = new int[4];
		int nowGrade = Integer.parseInt((String) gamePanel.grade.getText());
		FileWriter newFile;
		BufferedWriter bw = null;
		FileReader file;
		BufferedReader br = null;
		try {
			file = new FileReader("src/whz/data/rank_data.txt");
			br = new BufferedReader(file);
			for (int i = 0; i < 3; i++) {
				num[i] = Integer.parseInt(br.readLine().trim());
				System.out.println("sdfsdfsdf" + num[i]);
			}
			num[3] = nowGrade;
			Arrays.sort(num);
			newFile = new FileWriter("src/whz/data/rank_data.txt");
			bw = new BufferedWriter(newFile);
			for (int i = 3; i >= 1; i--) {
				bw.write(String.valueOf(num[i]));
				System.out.println("fsfsdf" + num[i]);
				bw.newLine();
			}
			br.close();
			bw.close();
			gamePanel.rank_1.setText("第一名：" + String.valueOf(num[3]));
			gamePanel.rank_2.setText("第二名：" + String.valueOf(num[2]));
			gamePanel.rank_3.setText("第三名：" + String.valueOf(num[1]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Point getPoint() {
		int rocks[][] = barrier.getRocks();
		int x, y;
		while (true) {
			x = new Random().nextInt(Global.WIDTH);
			y = new Random().nextInt(Global.HEIGHT);
			if (snake.isFoodInSnake(new Point(x, y)) == false && rocks[x][y] != 1) {
				break;
			}
		}
		return new Point(x, y);
	}
	
	public static void setFrameImage(JFrame jf) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image i = tk.getImage("src/whz/data/眼镜.png");
		jf.setIconImage(i);
	}
}

