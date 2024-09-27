package Jelly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void invalidDateTest(){

        Deadline deadline = new Deadline("TestDeadline", "now", false);
        assertEquals(false, deadline.hasValidDate());
    }

    @Test
    public void validDateTest(){

        Deadline deadline = new Deadline("TestDeadline", "1998-08-05", false);
        assertEquals(true, deadline.hasValidDate());
    }

    @Test
    public void markTest(){

        Deadline deadline = new Deadline("TestDeadline", "", false);
        deadline.mark();
        assertEquals(true, deadline.isDone());
    }
}
