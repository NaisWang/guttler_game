package whz.view;

import whz.entity.*;

import javax.swing.*;
import java.awt.*;
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

	public GamePanel() {
		initScoreText();
	}

	public GamePanel(int gameModel) {
		this.gameModel = gameModel;
		initScoreText();
	}

	// 初始化分数栏
	private void initScoreText(){
		scoreText.setText("snake1 score : 0");
		scoreText.setFocusable(false);
		this.add(scoreText);
		if(gameModel == 1){
			scoreTextTwo.setText("snake2 score : 0");
			scoreTextTwo.setFocusable(false);
			this.add(scoreTextTwo);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int i = 0;
		for (var snake : snakes) {
			snake.drawMyself(g);
			if(i++ == 0){
				scoreText.setText("snake1 score: " + snake.food);
			}else{
				scoreTextTwo.setText("snake2 score: " + snake.food);
			}
		}

		for (var food : foods) {
			food.drawMyself(g);
		}

		for (var barrier : barriers) {
			barrier.drawMyself(g);
		}

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
