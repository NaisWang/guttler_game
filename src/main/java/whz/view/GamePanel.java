package whz.view;

import whz.entity.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private List<StupidSnake> snakes;
    private List<Food> foods;

    public GamePanel() {
        snakes = new ArrayList<>();
        foods = new ArrayList<>();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (var snake : snakes) {
            snake.drawMyself(g);
        }

        for (var food : foods) {
            food.drawMyself(g);
        }
    }

    public void addSnake(StupidSnake snake) {
        snakes.add(snake);
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public List<StupidSnake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<StupidSnake> snakes) {
        this.snakes = snakes;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
