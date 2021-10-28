package whz.view;

import whz.entity.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private List<StupidSnake> snakes = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();
    private List<Barrier> barriers = new ArrayList<>();

    public GamePanel() {
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("fffff");
        for (var snake : snakes) {
            snake.drawMyself(g);
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

    public void addFood(Food food){
       this.foods.add(food);
    }

}
