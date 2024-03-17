package hal.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest(){
        assertEquals(new Deadline(false, "Do Homework", "2019-10-15").toString(),
                "[D][ ] Do Homework (by: Oct 15 2019)");
        assertEquals(new Deadline(true, "Read Book", "2019-11-15").toString(),
                "[D][X] Read Book (by: Nov 15 2019)");
    }

    @Test
    public void getFileStringTest(){
        assertEquals(new Deadline(false, "Submit RVX", "2019-10-15").getFileString(),
                "D | 0 | Submit RVX | 2019-10-15");
        assertEquals(new Deadline(true, "Read Book", "2019-11-15").getFileString(),
                "D | 1 | Read Book | 2019-11-15");
    }
}
