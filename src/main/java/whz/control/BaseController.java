package whz.control;

import whz.entity.Food;
import whz.entity.StupidSnake;
import whz.util.Global;
import whz.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseController {
    GamePanel gamePanel = new GamePanel();

    // 移动+吃食物
    public void snakesMove() {
        var foods = gamePanel.getFoods();
        var snakes = gamePanel.getSnakes();
        // 移动，吃食物
        for (var snake : snakes) {
            var eatFood = false;
            for (var food : foods) {
                if (food.isEatenBySnake(snake.getNext())) {
                    eatFood = true;
                    foods.remove(food);
                    break;
                }
            }
            snake.move(eatFood);
            System.out.println(snake.name + ", " + snake.getHead().x + ", " + snake.getHead().y);
        }
    }

    // 主逻辑，检查蛇撞墙、撞蛇等，移动蛇
    public void checkAndPaint() {
        var foods = gamePanel.getFoods();
        var snakes = gamePanel.getSnakes();
        var barriers = gamePanel.getBarriers();
        // 移动，吃食物
        snakesMove();
        List<StupidSnake> dieSnake = new ArrayList<>();

        // 判断是否碰到别的蛇
        for (var snake : snakes) {
            if (snake.isEatSelf()) {
                snake.life = false;
                dieSnake.add(snake);
                break;
            }

            for (var barrier : barriers) {
                if (barrier.isHit(snake.getHead())) {
                    snake.life = false;
                    dieSnake.add(snake);
                    break;
                }
            }


            var head = snake.getHead();
            for (var snake2 : snakes) {
                if (snake2 == snake) {
                    continue;
                }
                // 碰到Snake2了
                if (snake2.isHit(head)) {
                    snake.life = false;
                    dieSnake.add(snake);
                    // 若头碰头，双方都输
                    if (snake2.getHead().equals(head)) {
                        snake2.life = false;
                        dieSnake.add(snake2);
                    }
                    break;
                }
            }

            if (dieSnake.size() > 0) {
                break;
            }
        }

        // 判断是否死亡，死亡则结束
        if (dieSnake.size() > 0) {
            String tips = "";
            for (var snake : dieSnake) {
                tips += "玩家:";
                tips += snake.name;
                tips += ',';
            }

            tips += "失败了";
            System.out.println(tips);
            gamePanel.repaint();
            JOptionPane.showConfirmDialog(null, tips);
            System.exit(0);
        }

        // 判断是否要生成食物
        if (foods.size() <= 0) {
            addRandomFood(1);
        }
        gamePanel.repaint();
    }

    // 随机生成食物
    public List<Food> generateFood(int num) {
        var foods = new ArrayList<Food>();
        var snakes = gamePanel.getSnakes();
        for (int i = 0; i < num; i++) {
            int x, y;
            while (true) {
                x = new Random().nextInt(Global.DOUBLE_WIDTH - 1);
                y = new Random().nextInt(Global.DOUBLE_HEIGHT - 1);
                Food p = new Food(x, y);
                // 碰到蛇了
                for (var snake : snakes) {
                    if (snake.isHit(p)) {
                        continue;
                    }
                }

                // 碰到刚随机的食物
                for (var food : foods) {
                    if (food.equals(p)) {
                        continue;
                    }
                }

                // 碰到以前生成的食物
                for (var food : gamePanel.getFoods()) {
                    if (food.isHit(p)) {
                        continue;
                    }
                }

                foods.add(p);
                break;
            }
        }
        return foods;
    }

    public void addRandomFood(int num) {
        gamePanel.getFoods().addAll(generateFood(num));
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
