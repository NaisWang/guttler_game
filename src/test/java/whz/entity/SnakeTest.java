package whz.entity;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import whz.listener.SnakeListener;
import whz.util.Global;

import java.awt.*;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
    static int oldDirection,newDirection;
    static int food;
    static int sleep;
    static boolean life;
    static boolean key;
    static int oldbodysize;
    SnakeListener snakeListener;
    LinkedList<Point> body = new LinkedList();
    Snake snake = new Snake();



    @BeforeClass
    public static void method(){
        food = 3;
        life = true;
        key = true;
        sleep = 150;
        oldDirection = Global.RIGHT;
    }
    @Test
    void setFood() {
        Snake snake = new Snake();
        snake.setFood(food);
        Assertions.assertEquals(snake.getFood(),food);
    }

    @Test
    void setSleep() {
        snake.setSleep(sleep);
        Assertions.assertEquals(snake.getSleep(),food);
    }


    @Test
    void setLife() {
        snake.setLife(life);
        Assertions.assertEquals(snake.life,life);
    }

    @Test
    void setKey() {
        snake.setKey(key);
        Assertions.assertEquals(snake.key,key);
    }
    @Test
    void move() {


    }

    @Test
    void eatFood() {
        Snake snake = new Snake();
        oldbodysize=snake.body.size();
        snake.eatFood();
        Assertions.assertEquals(snake.body.size(),oldbodysize+1);
    }

    @Test
    void getHead() {
    }

    @Test
    void changeDirection() {
    }

    @Test
    void drawSelf() {

        System.out.println("this is draw");
    }

    @Test
    void isEatSelf() {
    }

    @Test
    void isFoodInSnake() {
    }

    @Test
    void addSnakeListener() {
    }

    @Test
    void shoot() {
    }

    @Test
    void produceBarrier() {
    }

    @Test
    void isEatenBarrier() {
    }

    @Test
    void isShoot() {

    }
}