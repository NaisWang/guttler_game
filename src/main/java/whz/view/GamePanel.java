package whz.view;

import whz.entity.*;
import whz.util.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

	// 游戏模式 0: 表示单人模式； 1： 表示双人模式
	private int gameModel = 0;

	private List<StupidSnake> snakes = new ArrayList<>();
	private List<Food> foods = new ArrayList<>();
	private List<Barrier> barriers = new ArrayList<>();
	private JTextField scoreText = new JTextField();
	private JTextField scoreTextTwo = new JTextField();
	private ImageIcon imageIcon = new ImageIcon("src/main/resources/photo.jpg");
	private JButton jButton = new JButton("单人模式");
	private JButton jButton2 = new JButton("双人模式");
	private JLabel jLabel = new JLabel(imageIcon);
	private String[] diffiStrs = {"简单", "普通", "困难"};
	private JComboBox jComboBox = new JComboBox(diffiStrs);

	private JTextField foodPoints = new JTextField();
	private JTextField snake1Points = new JTextField();
	private JTextField snake2Points = new JTextField();

	public GamePanel() {
		setLayout(null);
		add(jLabel);
		jButton.setBounds(Global.F_WIDTH / 2 - 150, Global.F_HEIGHT / 2 - 50, 100, 80);
		jButton.setName("singleBtn");
		jButton.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(e);
						Global.BEGIN = true;
						jButton.setVisible(false);
						jButton2.setVisible(false);
						jComboBox.setVisible(false);
						initScoreText();
						Global.DIFF = jComboBox.getSelectedIndex();
					}
				}
		);
		jButton2.setName("two");
		jButton2.setBounds(Global.F_WIDTH / 2 + 50, Global.F_HEIGHT / 2 - 50, 100, 80);
		jButton2.setName("doubleBtn");
		jButton2.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
//						System.out.println(e);
						Global.BEGIN = true;
						Global.DOUBLE = true;
						gameModel = 1;
						initScoreText();
						jButton.setVisible(false);
						jButton2.setVisible(false);
						jComboBox.setVisible(false);
						Global.DIFF = jComboBox.getSelectedIndex();
					}
				}
		);
		add(jButton);
		add(jButton2);
		jComboBox.setBounds(Global.F_WIDTH / 2 - 50, Global.F_HEIGHT / 2, 100, 100);
		jComboBox.setName("diffComboBox");
		add(jComboBox);
		jButton.setFocusable(false);
		jButton2.setFocusable(false);
		jComboBox.setFocusable(false);
		initPoints();
	}

	public GamePanel(int gameModel) {
		this.gameModel = gameModel;
		initScoreText();
	}

	private void initPoints() {
		foodPoints.setName("foodPoints");
		snake1Points.setName("snake1Points");
		snake2Points.setName("snake2Points");
		foodPoints.setFocusable(false);
		snake1Points.setFocusable(false);
		snake2Points.setFocusable(false);
		add(foodPoints);
		add(snake1Points);
		add(snake2Points);
	}


	// 初始化分数栏
	private void initScoreText() {
		scoreText.setText("snake1 score : 0");
		scoreText.setName("scoreText");
		scoreText.setFocusable(false);
		scoreText.setBounds(0, 0, 150, 20);
		this.add(scoreText);
		if (gameModel == 1) {
			scoreTextTwo.setText("snake2 score : 0");
			scoreTextTwo.setName("scoreTextTwo");
			scoreTextTwo.setFocusable(false);
			scoreTextTwo.setBounds(Global.F_WIDTH - 150, 0, 150, 20);
			this.add(scoreTextTwo);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int i = 0;
		for (var snake : snakes) {
			snake.drawMyself(g);

			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < snake.body.size(); j++) {
				sb.append(snake.body.get(j).getX() + "," + snake.body.get(j).getY() + "-");
			}

			if (i++ == 0) {
				scoreText.setText("snake1 score: " + snake.food);
				snake1Points.setText(sb.toString().substring(0, sb.length() - 1));
			} else {
				scoreTextTwo.setText("snake2 score: " + snake.food);
				snake2Points.setText(sb.toString().substring(0, sb.length() - 1));
			}
		}

		StringBuffer sb = new StringBuffer();
		for (var food : foods) {
			food.drawMyself(g);
			sb.append(food.getX() + "," + food.getY() + "-");
		}
		foodPoints.setText(sb.toString().substring(0, sb.length() - 1));

		for (var barrier : barriers) {
			barrier.drawMyself(g);
		}

//		g.drawImage(imageIcon.getImage(), 0, 0, Global.DOUBLE_WIDTH * Global.CELL_SIZE , Global.DOUBLE_HEIGHT * Global.CELL_SIZE , this);
	}

	public void addSnake(StupidSnake snake) {
		snakes.add(snake);
	}

	public void addBarrier(Barrier barrier) {
		barriers.add(barrier);
	}

	public List<StupidSnake> getSnakes() {
		return snakes;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public List<Barrier> getBarriers() {
		return barriers;
	}

	public void addFood(Food food) {
		this.foods.add(food);
	}

}
