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

    // �ƶ�+��ʳ��
    public void snakesMove() {
        var foods = gamePanel.getFoods();
        var snakes = gamePanel.getSnakes();
        // �ƶ�����ʳ��
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

    // ���߼��������ײǽ��ײ�ߵȣ��ƶ���
    public void checkAndPaint() {
        var foods = gamePanel.getFoods();
        var snakes = gamePanel.getSnakes();
        var barriers = gamePanel.getBarriers();
        // �ƶ�����ʳ��
        snakesMove();
        List<StupidSnake> dieSnake = new ArrayList<>();

        // �ж��Ƿ����������
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
                // ����Snake2��
                if (snake2.isHit(head)) {
                    snake.life = false;
                    dieSnake.add(snake);
                    // ��ͷ��ͷ��˫������
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

        // �ж��Ƿ����������������
        if (dieSnake.size() > 0) {
            String tips = "";
            for (var snake : dieSnake) {
                tips += "���:";
                tips += snake.name;
                tips += ',';
            }

            tips += "ʧ����";
            System.out.println(tips);
            gamePanel.repaint();
            JOptionPane.showConfirmDialog(null, tips);
            System.exit(0);
        }

        // �ж��Ƿ�Ҫ����ʳ��
        if (foods.size() <= 0) {
            addRandomFood(1);
        }
        gamePanel.repaint();
    }

    // �������ʳ��
    public List<Food> generateFood(int num) {
        var foods = new ArrayList<Food>();
        var snakes = gamePanel.getSnakes();
        for (int i = 0; i < num; i++) {
            int x, y;
            while (true) {
                x = new Random().nextInt(Global.DOUBLE_WIDTH - 1);
                y = new Random().nextInt(Global.DOUBLE_HEIGHT - 1);
                Food p = new Food(x, y);
                // ��������
                for (var snake : snakes) {
                    if (snake.isHit(p)) {
                        continue;
                    }
                }

                // �����������ʳ��
                for (var food : foods) {
                    if (food.equals(p)) {
                        continue;
                    }
                }

                // ������ǰ���ɵ�ʳ��
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
