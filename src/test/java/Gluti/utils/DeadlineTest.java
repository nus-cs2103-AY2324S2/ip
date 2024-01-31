package Gluti.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void paramTest(){
        Deadline testDeadline = new Deadline("paramTest", "21/12/1999");
        assertEquals(testDeadline.toString(), "[D][ ] paramTest(by: Dec 21 1999)");
    }
}