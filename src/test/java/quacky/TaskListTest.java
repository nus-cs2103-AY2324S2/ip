package quacky;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void markTask_outOfBounds_throwsException() {
        try {
            TaskList tasks = new TaskList();
            tasks.markCompleteTask(50);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Quack. The task is not found", e.getMessage());
        }
    }

    @Test
    public void addTask_taskNotMutated_success() {
        try {
            TaskList tasks = new TaskList();
            Task todo1 = new Todo("Finish Assignments");
            tasks.addTask(todo1);
            assertEquals(tasks.printTask(0), new Todo("Finish Assignments").toString());
        } catch (QuackyException e) {
            //This should never happen as it is a todo
            fail();
        }
    }

}