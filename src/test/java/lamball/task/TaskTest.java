package lamball.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void taskTest() {
        // Command test
        assertEquals(new Task("TEST").command(), "How did you get here?");

        // toString test
        assertEquals(new Task("TEST").toString(), "[ ] TEST");

    }

}
