package whz.entity;

import whz.util.Global;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class StupidSnake extends Entity {
    //蛇的当前方向与要变向的方向
    public int direction = Global.DOWN;

    // 蛇尾的坐标
//    public Point tail;
    // 吃掉食物的个数
    public int food = 0;

    //蛇是否存活
    public boolean life = true;

//    public boolean key = true;

    // 蛇监听器，监听蛇的移动
//    public SnakeListener snakeListener;

    // 蛇身体的所有节点对应的坐标
    public LinkedList<Point> body = new LinkedList();

    // 蛇每次移动的间隔时间
    public int interval = 150;

    // 蛇的名字
    public String name = "snake0";

    // 方向对应的按键
    public int LEFT_KEY = KeyEvent.VK_LEFT;
    public int RIGHT_KEY = KeyEvent.VK_RIGHT;
    public int UP_KEY = KeyEvent.VK_UP;
    public int DOWN_KEY = KeyEvent.VK_DOWN;



    public StupidSnake(String name, Color color, Point pos) {
        setColor(color);
        this.name = name;
        body.push(pos);

    }

    // 监听键盘
    public KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            var key = e.getKeyCode();
//            System.out.println("你按下了:" + key);
            var newDirection = direction;

            if (key == LEFT_KEY) {
                newDirection = Global.LEFT;
            } else if (key == RIGHT_KEY) {
                newDirection = Global.RIGHT;
            } else if (key == UP_KEY) {
                newDirection = Global.UP;
            } else if (key == DOWN_KEY) {
                newDirection = Global.DOWN;
            }

            // 不能突然反方向吃到自己
            if (body.size() > 1 && getNext(newDirection).equals(body.get(1))) {
                return;
            }
            setDirection(newDirection);
        }
    };


    // 蛇移动
    public void move(boolean eatFood) {
        // 获取蛇头的(x, y)坐标
        var head = getNext();
        body.addFirst(head);

        // 若吃到食物，保持尾部
        if (!eatFood) {
//            tail = body.removeLast();
            body.removeLast();
        } else {
            food++;
        }

    }

    // 获取蛇头坐标
    public Point getHead() {
        return body.getFirst();
    }

    // 获取要移动的下一个坐标，是否撞墙、撞别的蛇由Controller判断
    public Point getNext() {
        return getNext(direction);
    }

    public Point getNext(int direction) {
        var head = getHead();
        var nextHead = head;
        var x = head.x;
        var y = head.y;
        switch (direction) {
            case Global.UP:
                y--;
                break;
            case Global.DOWN:
                y++;
                break;
            case Global.LEFT:
                x--;
                break;
            case Global.RIGHT:
                x++;
                break;
        }
        x = (x + Global.DOUBLE_WIDTH) % Global.DOUBLE_WIDTH;
        y = (y + Global.DOUBLE_HEIGHT) % Global.DOUBLE_HEIGHT;

        nextHead = new Point(x, y);
        return nextHead;
    }

    // 判断是否吃到自己
    public boolean isEatSelf() {
        var head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(head)) {
//                System.out.println("Snake is eaten by itself");
                return true;
            }
        }
        return false;
    }

    // 判断别的蛇是否触碰到该蛇
    @Override
    public boolean isHit(Point point) {
        for (var b : body) {
            if (b.equals(point)) {
                return true;
            }
        }
        return false;
    }

    // 绘制蛇
    @Override
    public void drawMyself(Graphics graphics) {
        graphics.setColor(Color.blue);
        // 遍历蛇的所有节点坐标
        for (int i = 0; i < body.size(); i++) {
            // 绘制蛇的节点
            graphics.fillRoundRect((int) body.get(i).x * Global.CELL_SIZE, (int) body.get(i).y * Global.CELL_SIZE, Global.CELL_SIZE,
                    Global.CELL_SIZE, 10, 10);
        }

        if (!body.isEmpty()) {
            graphics.setColor(getColor());
            graphics.fillRoundRect((int) body.getFirst().getX() * Global.CELL_SIZE,
                    (int) body.getFirst().getY() * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, 10, 10);
        }

    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

//    public int getInterval() {
//        return interval;
//    }
//
//    public void setInterval(int interval) {
//        this.interval = interval;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}

