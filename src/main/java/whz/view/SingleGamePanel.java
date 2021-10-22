package whz.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import whz.entity.Barrier;
import whz.entity.SingleFood;
import whz.entity.Snake;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * GamePanel
 * Ways of GamePanel:
 * 1.display()
 */

public class SingleGamePanel extends JPanel {
	private Snake snake;
	private Barrier barrier;
	private SingleFood food;
	public JComboBox rank = new JComboBox();
	public JButton stopButton = new JButton("\u6682\u505C");
	public JButton continueButton = new JButton("\u7EE7\u7EED");
	public JLabel label = new JLabel("\u5206\u6570\uFF1A");
	public JLabel grade = new JLabel("0");
	public JLabel lblRank = new JLabel("rank:");
	public JLabel rank_1 = new JLabel("1.");
	public JLabel rank_2 = new JLabel("2.");
	public JLabel rank_3 = new JLabel("3.");
	 
	public SingleGamePanel() {
		setLayout(null);
		
		rank.setModel(new DefaultComboBoxModel(new String[] {"easy", "normal", "difficult"}));
		rank.setBounds(334, 101, 88, 23);
		add(rank);
		
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}});


		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		continueButton.setBounds(214, 355, 97, 23);
		add(continueButton);
		
		JLabel lblenter = new JLabel("\u6682\u505C/\u7EE7\u7EED\uFF1Aenter     \u52A0\u901F\uFF1A\u7A7A\u683C");
		lblenter.setBounds(51, 399, 256, 15);
		add(lblenter);
		label.setBounds(334, 52, 58, 23);
		
		add(label);
		grade.setBounds(372, 56, 58, 15);
		
		add(grade);
		lblRank.setBounds(334, 149, 58, 15);
		
		add(lblRank);
		rank_1.setBounds(334, 171, 88, 15);
		
		add(rank_1);
		rank_2.setBounds(334, 196, 88, 15);
		
		add(rank_2);
		rank_3.setBounds(334, 221, 97, 15);
		
		add(rank_3);
	}
	
	public void display(Snake snake, SingleFood food, Barrier barrier) {
		System.out.println("GamePanel is showing the snake, the food and the barrier");
		this.snake = snake;
		this.food = food;
		this.barrier = barrier;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(snake!=null&&food!=null&&barrier!=null) {
			snake.drawSelf(g,1);
			food.drawSelf(g);
			barrier.drawSelf(g);
		}
	}
}












