package tasks;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void testDeadlineCreation() {
        Deadline deadline = new Deadline("return book","2/12/2019 1800");
        assertEquals("return book", deadline.getDescription());
        assertFalse(deadline.checkDone());
    }

    @Test
    public void testDeadlineMark() {
        Deadline deadline = new Deadline("return book","2/12/2019 1800");
        deadline.setMark();
        assertTrue(deadline.checkDone());
    }
}
