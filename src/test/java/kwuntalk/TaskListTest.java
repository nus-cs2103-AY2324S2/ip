package kwuntalk;

import kwuntalk.exception.DuplicateTaskException;
import kwuntalk.task.Task;
import kwuntalk.task.Todo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void testGetLength() {
        ArrayList<Task> testArrList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            testArrList.add(new Todo("test " + i));
        }
        TaskList testTasks = new TaskList(testArrList.toArray(new Task[0]));
        assertEquals(10, testTasks.getLength());
    }

    @Test
    public void testIsEmpty() {
        ArrayList<Task> testArrList = new ArrayList<>();
        TaskList testTasks = new TaskList(testArrList.toArray(new Task[0]));

        assertTrue(testTasks.isEmpty());
    }

    @Test
    public void testGet() {
        ArrayList<Task> testArrList = new ArrayList<>();
        Task test = new Todo("test");
        testArrList.add(test);
        TaskList testTasks = new TaskList(testArrList.toArray(new Task[0]));

        assertEquals(test, testTasks.get(1));
    }

    @Test
    public void testAdd() {
        ArrayList<Task> testArrList = new ArrayList<>();
        TaskList testTasks = new TaskList(testArrList.toArray(new Task[0]));
        Task test = new Todo("test");

        try {
            testTasks.add(test);
        } catch (DuplicateTaskException ignored) {
        }

        assertEquals(test, testTasks.get(1));
    }

    @Test
    public void testRemove() {
        ArrayList<Task> testArrList = new ArrayList<>();
        Task test = new Todo("test");
        testArrList.add(test);
        TaskList testTasks = new TaskList(testArrList.toArray(new Task[0]));
        testTasks.remove(1);

        assertTrue(testTasks.isEmpty());
    }
}
