package Bentley;

import java.util.ArrayList;

import Bentley.Task;
import Bentley.Deadline;
import Bentley.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    // Test that a new task created should be unmarked
    @Test
    public void addUnmarkedDeadline(){
        Task task = new Deadline("return book", "2025-09-09");
        assertEquals("0", task.doneOrNot());
    }
}

    