package duchess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * TaskListTest class contains JUnit tests for the TaskList class.
 */
public class TaskListTest {

    /**
     * Tests the addToDo method of the TaskList class.
     * It checks if a ToDo task is correctly added to the task list.
     */
    @Test
    public void addToDoTest() {
        TaskList taskList = new TaskList();
        try {
            taskList.addToDo("buy groceries");
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1, taskList.getTasks().size());
        assertEquals("[T][ ] buy groceries", taskList.getTasks().get(0).toString());
    }

    /**
     * Tests the deleteTask method of the TaskList class.
     * It checks if a task is correctly deleted from the task list.
     */
    @Test
    public void deleteTaskTest() {
        TaskList taskList = new TaskList();
        try {
            taskList.addToDo("buy groceries");
            assertEquals(1, taskList.getTasks().size());
            assertEquals("[T][ ] buy groceries", taskList.getTasks().get(0).toString());
            taskList.deleteTask(0);
            assertEquals(0, taskList.getTasks().size());
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }

    }
}
