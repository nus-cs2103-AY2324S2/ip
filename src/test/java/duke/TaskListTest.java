import duke.TaskList;
import duke.Task;
import duke.Todo;
import duke.TimeBlock;
import duke.Deadline;
import duke.BotException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    /*
     * Test if a todo task can be added to the task list
     */
    @Test
    public void addTodoTest() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Test Todo");
        assertEquals(1, taskList.getTaskCount());
        assertTrue(taskList.getTaskByNum(1) instanceof Todo);
    }

    /*
     * Test if a task can be removed from the task list
     */
    @Test
    public void removeTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Test Todo");
        taskList.removeTask(1);
        assertEquals(0, taskList.getTaskCount());
    }

    /*
     * Test if a task can be marked as done
     */
    @Test
    public void markTaskAsDoneTest() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Test Todo");
        taskList.markTaskAsDone(1);
        Task task = taskList.getTaskByNum(1);
        assertTrue(task.isDone());
    }

    /*
     * Test if an event task can be added to the task list
     */
    @Test
    public void addEventTest() {
        TaskList taskList = new TaskList();
        try {
            taskList.addEvent("Test Event", "05/03/2089 1500", "05/03/2089 1600");
            assertEquals(1, taskList.getTaskCount());
            assertTrue(taskList.getTaskByNum(1) instanceof TimeBlock);
        } catch (BotException e) {
            fail("Exception should not have been thrown.");
        }
    }

    /*
     * Test if a deadline task can be added to the task list
     */
    @Test
    public void addDeadlineTest() {
        TaskList taskList = new TaskList();
        try {
            taskList.addDeadline("Test Deadline", "05/03/2089 1500");
            assertEquals(1, taskList.getTaskCount());
            assertTrue(taskList.getTaskByNum(1) instanceof Deadline);
        } catch (BotException e) {
            fail("Exception should not have been thrown.");
        }
    }

    /*
     * Test if we can remove a task that does not exist
     */
    @Test
    public void removeNonExistentTaskTest() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.removeTask(1);
        });
    }

    /*
     * Test if we can mark a task that does not exist as done
     */
    @Test
    public void markNonExistentTaskAsDoneTest() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.markTaskAsDone(1);
        });
    }
}