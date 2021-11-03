package whz.control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import whz.entity.Barrier;
import whz.entity.Food;
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

    @BeforeEach
    public void setUp(){
        baseController = new BaseController();
        gamePanel = baseController.getGamePanel();
        snake1 = new StupidSnake("snake1", Color.black, new Point(3, 1));
        snake1.UP_KEY = KeyEvent.VK_W;
        snake1.DOWN_KEY = KeyEvent.VK_S;
        snake1.LEFT_KEY = KeyEvent.VK_A;
        snake1.RIGHT_KEY = KeyEvent.VK_D;
        snake1.setDirection(Global.RIGHT);
        for (int i = 1; i < 4; i++) {
            snake1.body.add(new Point(3 - i, 1));
        }
        barrier = new Barrier();
        gamePanel.addSnake(snake1);
        gamePanel.addBarrier(barrier);
        baseController.addRandomFood(1);
    }

    @Test
    void moveDirection(){
        var head=snake1.getHead();
        System.out.println(head);
        var before_x=head.x;
        var before_y=head.y;
        snake1.setDirection(Global.UP);
        snake1.move(false);
        head=snake1.getHead();
        Assertions.assertEquals(before_x,head.x);
        Assertions.assertEquals(before_y-1,head.y);    //ио

        before_x=head.x;
        before_y=head.y;
        snake1.setDirection(Global.DOWN);
        snake1.move(false);
        head=snake1.getHead();
        Assertions.assertEquals(before_y+1,head.y);    //об

        before_x=head.x;
        before_y=head.y;
        snake1.setDirection(Global.LEFT);
        snake1.move(false);
        head=snake1.getHead();
        Assertions.assertEquals(before_x-1,head.x);    //вС

        before_x=head.x;
        before_y=head.y;
        snake1.setDirection(Global.RIGHT);
        snake1.move(false);
        head=snake1.getHead();
        Assertions.assertEquals(before_x+1,head.x);    //ср

    }

    @Test
    void snakesMove() {
        Point headBefore = snake1.getHead();
        System.out.println(headBefore);
        int BeforeFoodSize = baseController.getGamePanel().getFoods().size();
        baseController.getGamePanel().getFoods().add(new Food(3,2));
        snake1.direction = Global.DOWN;
        baseController.snakesMove();
        Point headAfter = snake1.getHead();
        Assertions.assertNotEquals(headBefore,headAfter);

        Assertions.assertEquals(BeforeFoodSize, baseController.getGamePanel().getFoods().size());

    }

    @Test
    void addRandomFood() {
        int len = gamePanel.getFoods().size();
        baseController.addRandomFood(1);
        Assertions.assertEquals(len + 1,gamePanel.getFoods().size());
    }

}