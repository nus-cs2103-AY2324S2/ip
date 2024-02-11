package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;


public class TaskListTest {
    @Test
    void deadline_wrongDateFormat() {
        try {
            TaskList tasks = new TaskList();
            tasks.addTask(null, "deadline", "testing title", "Oct 09 2024");
            fail();
        } catch (DukeException de) {
            assertEquals(de.getMessage(), "Date not in format: yyyy-MM-dd, please try again.");
        }
    }

    @Test
    void input_taskId_outOfBounds() {
        try {
            TaskList tasks = new TaskList();
            tasks.mark(null, "-1");
        } catch (DukeException de) {
            assertEquals(de.getMessage(), "Index number cannot be out of bounds.");
        }
    }
}
