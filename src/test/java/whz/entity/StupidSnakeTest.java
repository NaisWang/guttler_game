package whz.entity;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import whz.util.Global;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class StupidSnakeTest {
    static int oldDirection,newDirection;
    static int food;
    static int sleep;
    static boolean life;
    static boolean key;
    static int oldbodysize;

    LinkedList<Point> body = new LinkedList();

    @BeforeClass
    public static void method(){
        StupidSnake snake = new StupidSnake("Íæ¼Ò1", Color.black, new Point(2, 0));
        snake.UP_KEY = KeyEvent.VK_W;
        snake.DOWN_KEY = KeyEvent.VK_S;
        snake.LEFT_KEY = KeyEvent.VK_A;
        snake.RIGHT_KEY = KeyEvent.VK_D;
    }


    @Test
    void getDirection() {
        StupidSnake snake = new StupidSnake("Íæ¼Ò1", Color.black, new Point(2, 0));
        snake.setDirection(Global.RIGHT);
        System.out.print(Global.RIGHT);
        Assertions.assertEquals(Global.RIGHT,snake.getDirection());
    }

    @Test
    void move(){
        StupidSnake snake = new StupidSnake("Íæ¼Ò1", Color.black, new Point(1, 0));
//        System.out.print(snake.body.size());
//        snake.body.add(new Point(0,0));
        int size = snake.body.size();

        boolean eatFood=true;
        snake.move(eatFood);
        Assertions.assertEquals(size+1,snake.body.size());
        size = snake.body.size();
        boolean eatFood1 =false;
        snake.move(eatFood1);
        Assertions.assertEquals(size,snake.body.size());





    }
}