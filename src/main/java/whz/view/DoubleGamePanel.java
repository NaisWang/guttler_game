package whz.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import whz.entity.Bullets;
import whz.entity.Bullets.Bullet;
import whz.entity.DoubleBarrier;
import whz.entity.DoubleBarrier.Barriers;
import whz.entity.DoubleFood;
import whz.entity.Snake_1;
import whz.entity.Snake_2;

public class DoubleGamePanel extends JPanel {
	private Snake_1 snake_1;
	private Snake_2 snake_2;
	private DoubleFood food;
	private Bullets bullets;
	private DoubleBarrier doubleBarrier;

	public DoubleGamePanel() {
		setLayout(null);
	}

	public void display(Snake_1 snake_1, Snake_2 snake_2, DoubleFood food, Bullets bullets, DoubleBarrier doubleBarrier) {
		this.snake_1 = snake_1;
		this.snake_2 = snake_2;
		this.food = food;
		this.bullets = bullets;
		this.doubleBarrier = doubleBarrier;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (snake_1 != null && snake_2 != null && food != null) {
			snake_1.drawSelf(g, 1);
			snake_2.drawSelf(g, 2);
			food.drawMyself(g);
			for (Bullet b : bullets.getBullet()) {
				b.drawMyself(g);
			}
			for (Barriers b : doubleBarrier.barriers) {
				b.drawMyself(g);
			}
		}
	}
}
