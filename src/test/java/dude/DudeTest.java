package dude;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DudeTest {
    @Test
    public void testAddToDo() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Duration duration = Duration.ofDays(1).plusHours(2).plusMinutes(30);
        ToDo todo = new ToDo("Sample task", duration);
        taskList.add(todo);

        assertEquals(1, taskList.size(), "TaskList should have exactly one task after adding a ToDo.");
        assertEquals(todo, taskList.get(0), "The task added to TaskList should be the same as the ToDo created.");
    }

    @Test
    public void testMarkAsDone() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Duration duration = Duration.ofDays(1).plusHours(2).plusMinutes(30);
        ToDo todo = new ToDo("Another sample task", duration);
        taskList.add(todo);

        // Assuming the first task has index 0
        taskList.markAsDone(0);

        assertTrue(taskList.get(0).isDone, "The task should be marked as done.");
    }
}
