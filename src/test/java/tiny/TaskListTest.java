package tiny;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tiny.exceptions.TinyException;
import tiny.lists.TaskList;
import tiny.tasks.Deadline;
import tiny.tasks.Todo;

public class TaskListTest {
    @Test
    public void getTest() throws TinyException {
        TaskList tasks = new TaskList();
        Deadline task1 = new Deadline("Assignment", "2022-02-02 2222");
        Todo task2 = new Todo("Project");
        tasks.add(task1);
        tasks.add(task2);
        assertEquals(task1, tasks.get(0));
        assertEquals(task2, tasks.get(1));
    }

    @Test
    public void deleteTest() throws TinyException {
        TaskList tasks = new TaskList();
        Deadline task1 = new Deadline("Assignment", "2022-02-02 2222");
        Todo task2 = new Todo("Project");
        tasks.add(task1);
        tasks.add(task2);
        assertEquals(2, tasks.size());
        tasks.delete(0);
        assertEquals(1, tasks.size());
        assertEquals(task2, tasks.get(0));
    }
}
