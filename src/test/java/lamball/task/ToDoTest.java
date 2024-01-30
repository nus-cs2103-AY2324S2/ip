package lamball.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void todoTest() {
        // Command test
        assertEquals(new ToDo("TEST").command(), "todo TEST");

        // toString test
        assertEquals(new ToDo("TEST").toString(), "[T][ ] TEST");

    }
}
