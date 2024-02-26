package SamuelBot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkTest {

    @Test
    public void testMarkAsDone() {
        // Create a new Todo task
        Todo todo = new Todo("Buy groceries");

        // Verify that the task is initially not done
        assertEquals(false, todo.isDone(), "Task should initially be not done");

        // Mark the task as done
        todo.markAsDone();

        // Verify that the task is now marked as done
        assertEquals(true, todo.isDone(), "Task should be marked as done after calling markAsDone");
    }
}
