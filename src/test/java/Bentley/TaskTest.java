package Bentley;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bentley.Deadline;
import bentley.Event;
import bentley.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    // Test that a new task created should be unmarked
    @Test
    public void addUnmarkedDeadline(){
        Task task = new Deadline("return book", "2025-09-09");
        assertEquals("0", task.doneOrNot());
    }
}

    