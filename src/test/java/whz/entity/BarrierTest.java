package whz.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BarrierTest {

    @Test
    void isHit() {
        var barrier = new Barrier();
        for(int i = 0; i < 5; i++){
            Assertions.assertEquals(
                barrier.isHit(new Point(i, 0)),
                true
            );
        }
        for(int i = 1; i< 5; i++){
            Assertions.assertEquals(
                barrier.isHit(new Point(3, i)),
                false
            );
        }
    }
}