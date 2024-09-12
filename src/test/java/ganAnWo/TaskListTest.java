package ganAnWo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ganAnWo.task.Task;
import ganAnWo.task.ToDos;


public class TaskListTest {

    /**
     * Tests whether addTask able to
     * perform as expected.
     *
     */
    @Test
    public void addTask_success() {
        TaskList TaskL = new TaskList();
        Task dummyTaskA = new ToDos("first");
        String a = "Got it. I've added this task:\n";
        String b = dummyTaskA.toString() + "\n";
        String c = "Now you have 1 tasks in the list.";
        String expected = a + b + c;
        assertEquals(expected, TaskL.addTask(dummyTaskA) );
    }

    /**
     * Tests whether getList able to
     * perform as expected.
     *
     */
    @Test
    public void getList_success() {
        TaskList TaskL = new TaskList();
        ArrayList<Task> expected = new ArrayList<>();
        Task dummyTaskA = new ToDos("first");
        TaskL.addTask(dummyTaskA);
        expected.add(dummyTaskA);
        assertEquals(expected.get(0), TaskL.getList().get(0));
    }
}
