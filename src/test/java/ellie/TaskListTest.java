package ellie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ellie.task.Task;
import ellie.task.Todo;

public class TaskListTest {

    //Notation: unitBeingTested_descriptionOfTestInputs_expectedOutcome

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        // Create a StorageStub for testing
        Storage storage = new StorageStub("testFilePath", "testDirectory");
        // Initialize TaskList with the StorageStub
        taskList = new TaskList(storage);
    }

    @Test
    public void taskList_testDeleteTask_success() {
        Task task = new Todo("Test Todo", 0);
        taskList.addTask(task);
        taskList.deleteTaskIndex(1);
        assertEquals(0, taskList.taskQuantity());
    }

    @Test
    public void taskList_testAddTask_success() {
        Task task = new Todo("Test Todo", 0);
        Task task2 = new Todo("Test Todo 2", 1);
        taskList.addTask(task);
        taskList.addTask(task2);
        assertEquals(2, taskList.taskQuantity());
    }

}
