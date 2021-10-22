package whz.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BarrierTest {

    @Test
    void isHit() {
        var barrier = new Barrier();
        Assertions.assertEquals(
                barrier.isHit(new Point(8, 0)),
                true
        );
    }
}