package whz.entity;

import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : whz
 */
class DoubleFoodTest {


	@org.junit.jupiter.api.Test
	void isEatenBySnake() {
		DoubleFood doubleFood = new DoubleFood();
		doubleFood.addFood(new ArrayList<Point>(Arrays.asList(new Point(1,2), new Point(2,3))));
		Assertions.assertFalse(doubleFood.isEatenBySnake(new Point(1, 2)));
	}


}