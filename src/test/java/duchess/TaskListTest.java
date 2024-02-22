package duchess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duchess.task.Task;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


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

    /**
     * Tests the markTaskAsDone method of the TaskList class.
     * It checks if a task is correctly marked as done from the task list.
     */
    @Test
    public void markTaskAsDoneTest() {
        TaskList taskList = new TaskList();
        try {
            taskList.addToDo("buy groceries");
            assertEquals(1, taskList.getTasks().size());
            assertEquals("[T][ ] buy groceries", taskList.getTasks().get(0).toString());
            taskList.markTaskAsDone(0);
            assertEquals("X", taskList.getTasks().get(0).getStatusIcon());
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests the unmarkTaskAsDone method of the TaskList class.
     * It checks if a task is correctly marked as not done from the task list.
     */
    @Test
    public void unmarkTaskAsDoneTest() {
        TaskList taskList = new TaskList();
        try {
            taskList.addToDo("buy groceries");
            assertEquals(1, taskList.getTasks().size());
            assertEquals("[T][ ] buy groceries", taskList.getTasks().get(0).toString());
            taskList.getTasks().get(0).markAsDone();
            assertEquals("X", taskList.getTasks().get(0).getStatusIcon());
            taskList.unmarkTaskAsDone(0);
            assertEquals(" ", taskList.getTasks().get(0).getStatusIcon());
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests the findTasksByKeyword method of the TaskList class.
     * It checks if a task is correctly found by keyword from the task list.
     */
    @Test
    public void findTasksByKeywordTest() {
        TaskList taskList = new TaskList();
        try {
            taskList.addToDo("buy groceries");
            taskList.addToDo("sell books");
            taskList.addToDo("buy flowers");

            ArrayList<Pair<Integer, Task>> listOfTasksFound = taskList.findTasksByKeyword("buy");

            assertEquals(2, listOfTasksFound.size());
            assertEquals(0, listOfTasksFound.get(0).getKey());
            assertEquals(2, listOfTasksFound.get(1).getKey());

            Task taskFoundOne =  listOfTasksFound.get(0).getValue();
            Task taskFoundTwo =  listOfTasksFound.get(1).getValue();
            assertEquals("[T][ ] buy groceries", taskFoundOne.toString());
            assertEquals("[T][ ] buy flowers", taskFoundTwo.toString());
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }
    }
}
