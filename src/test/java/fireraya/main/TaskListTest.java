package fireraya.main;

import fireraya.task.Task;
import fireraya.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void emptyListTest(){
        TaskList tasklist = new TaskList();
        int i = tasklist.size();
        assertEquals(0, i);
    }

    @Test
    public void getTaskTest(){
        Task t = new Todo("Eat bread");
        t.markAsDone();
        TaskList tasklist = new TaskList();
        tasklist.add(t);
        assertEquals(t, tasklist.getTasks().get(0));
    }
}
