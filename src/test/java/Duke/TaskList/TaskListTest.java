package Duke.TaskList;

import Duke.DukeException.DukeException;
import Duke.Task.Deadlines.Deadlines;
import Duke.Task.Task;
import Duke.Task.ToDos.ToDos;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testMark() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Deadlines("Return book", LocalDate.parse("2019-12-12")));
        TaskList taskList = new TaskList(tasks);
        taskList.mark(0);
        assertEquals("  [D][X] Return book (by: Dec 12 2019)", taskList.getTask(0).toString());
    }

    @Test
    public void testDelete() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("Read book "));
        tasks.add(new Deadlines("Return book", LocalDate.parse("2019-12-12")));
        TaskList taskList = new TaskList(tasks);
        taskList.delete(0);
        assertEquals("  [D][ ] Return book (by: Dec 12 2019)", taskList.getTask(0).toString());
    }
}
