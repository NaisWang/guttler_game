package whz.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import whz.entity.DoubleFood;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : whz
 */
class DoubleFoodTest {


	@Test
	void isEatenBySnake() {
		DoubleFood doubleFood =new DoubleFood();
		doubleFood.addFood(new ArrayList<>(Arrays.asList(new Point(1,2),new Point(3,5))));
		Assertions.assertTrue(doubleFood.isEatenBySnake(new Point(1,2)));
		Assertions.assertFalse(doubleFood.isEatenBySnake(new Point(3,4)));
	}


}