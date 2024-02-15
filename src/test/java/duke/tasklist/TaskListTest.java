package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.dukeexception.DukeException;
import duke.task.Task;
import duke.task.deadlines.Deadlines;
import duke.task.todos.ToDos;



public class TaskListTest {
    @Test
    public void testMark() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        LocalDateTime by = LocalDateTime.parse("2012-12-12 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        tasks.add(new Deadlines("Return book", by));
        TaskList taskList = new TaskList(tasks);
        taskList.mark(0);
        assertEquals("  [D][X] Return book (by: Dec 12 2012 12:00)", taskList.getTask(0).toString());
    }

    @Test
    public void testDelete() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("Read book "));
        LocalDateTime by = LocalDateTime.parse("2012-12-12 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        tasks.add(new Deadlines("Return book", by));
        TaskList taskList = new TaskList(tasks);
        taskList.delete(0);
        assertEquals("  [D][ ] Return book (by: Dec 12 2012 12:00)", taskList.getTask(0).toString());
    }
}
