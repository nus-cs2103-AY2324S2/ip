package task; //same package as the class being tested
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.TaskList;

public class DeleteTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testDelete() {

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));
        tasks.add(new Todo("Task 3"));

        // Create TaskList instance
        TaskList taskList = new TaskList(tasks);

        // Delete the first task from the list
        String result = taskList.delete(1);

        // Check if the task is deleted successfully
        assertEquals("Noted. I've removed this task:\n"
                + "[T][ ] Task 2\nNow you have 2 tasks in the list.", result);

        // Check if the size of the task list is updated
        assertEquals(2, taskList.getTaskList().size());


    }
}
