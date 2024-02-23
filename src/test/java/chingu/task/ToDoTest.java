package chingu.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() {
        ToDo testDeadline = new ToDo("Make a soup for Mike", "low");
        String expected = "[T][ ] Make a soup for Mike Priority: low";

        assertEquals(expected, testDeadline.toString());
    }

    @Test
    public void toStringTest2() {
        ToDo testDeadline = new ToDo("Do laundry at Home", "High");
        String expected = "[T][ ] Do laundry at Home Priority: High";

        assertEquals(expected, testDeadline.toString());
    }
}
