package whz.control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import whz.entity.Barrier;
import whz.entity.StupidSnake;
import whz.util.Global;
import whz.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class BaseControllerTest {
    BaseController baseController;
    GamePanel gamePanel;
    StupidSnake snake1;
    Barrier barrier;

    //初始化
    @BeforeEach
    public void setUp(){
        baseController = new BaseController();
        gamePanel = baseController.getGamePanel();
        // 初始化蛇1
        snake1 = new StupidSnake("玩家1", Color.black, new Point(3, 1));
        snake1.UP_KEY = KeyEvent.VK_W;
        snake1.DOWN_KEY = KeyEvent.VK_S;
        snake1.LEFT_KEY = KeyEvent.VK_A;
        snake1.RIGHT_KEY = KeyEvent.VK_D;
        snake1.setDirection(Global.RIGHT);
        for (int i = 1; i < 4; i++) {
            snake1.body.add(new Point(3 - i, 1));
        }
        // 初始化障碍物
        barrier = new Barrier();
        gamePanel.addSnake(snake1);
        gamePanel.addBarrier(barrier);
        // 加一个食物
        baseController.addRandomFood(1);
    }

    //方向测试
    @Test
    void moveDirection(){
        GamePanel gamePanel = new GamePanel();
        StupidSnake snake=new StupidSnake("玩家1", Color.black, new Point(3, 1));

        var head=snake.getHead();
        var before_x=head.x;
        var before_y=head.y;
        snake.setDirection(Global.UP);
        snake.move(false);
        head=snake.getHead();
        Assertions.assertEquals(before_y-1,head.y);    //上

        before_x=head.x;
        before_y=head.y;
        snake.setDirection(Global.DOWN);
        snake.move(false);
        head=snake.getHead();
        Assertions.assertEquals(before_y+1,head.y);    //下

        before_x=head.x;
        before_y=head.y;
        snake.setDirection(Global.LEFT);
        snake.move(false);
        head=snake.getHead();
        Assertions.assertEquals(before_x-1,head.x);    //左

        before_x=head.x;
        before_y=head.y;
        snake.setDirection(Global.RIGHT);
        snake.move(false);
        head=snake.getHead();
        Assertions.assertEquals(before_x+1,head.x);    //右

    }

    @Test
    void snakesMove() {
        Point headBefore = snake1.getHead();
        baseController.snakesMove();
        Point headAfter = snake1.getHead();
        Assertions.assertNotEquals(headBefore,headAfter);
    }

    @Test
    void addRandomFood() {
        int len = gamePanel.getFoods().size();
        baseController.addRandomFood(1);
        Assertions.assertEquals(len + 1,gamePanel.getFoods().size());
    }
}