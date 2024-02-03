package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTask_toDo_successStringReturned() {
        assertEquals("[T][ ] task",
                new TaskList().addTask(new ToDo("task", false)));

        assertEquals("[T][X] task",
                new TaskList().addTask(new ToDo("task", true)));
    }

    @Test
    public void addTask_deadline_successStringReturned() {
        assertEquals("[D][ ] task (by: Oct 5 2024)",
                new TaskList().addTask(
                        new Deadline(
                                "task",
                                false,
                                LocalDate.parse("2024-10-05"))));
    }

    @Test
    public void addTask_event_successStringReturned() {
        assertEquals("[E][ ] task (from: now to: tomorrow)",
                new TaskList().addTask(
                        new Event("task", false, "now", "tomorrow")));
    }
}
