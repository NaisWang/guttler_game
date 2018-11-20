package whz.listener;

import whz.entity.Snake;

/**
 * Description: snakeListener is used to listen for the movement of the snake
 * 
 * events of SnakeListener: 1.snakeMoved()
 * 
 * @author whz
 *
 */

public interface SnakeListener {
	void snakeMove(Snake snake);
}
