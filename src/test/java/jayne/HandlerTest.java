package jayne;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jayne.task.TaskList;
import jayne.task.Todo;

//Test
class HandlerTest {
    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() throws JayneException {
        this.storage = new Storage("./out/test.txt");
        taskList = new TaskList(storage);
    }

    @AfterEach
    void tearDown() {
        taskList.getStorage().deleteFile();
    }

    @Test
    void handleDelete_withValidInput() throws JayneException {
        // Setup initial conditions
        Todo todo = new Todo("Sample Task");
        taskList.addTask(todo);
        String[] parts = {"delete", "1"};

        // Perform action
        Handler.handleDelete(parts, taskList);

        // Check results
        assertEquals(0, taskList.getTaskCount(), "Task count should decrease after deletion");
    }

    @Test
    void handleDelete_withInvalidInput() {
        // Setup initial conditions
        String[] parts = {"delete", "xyz"}; // Invalid task number

        // Perform action and check results
        assertThrows(JayneException.class, () -> {
            Handler.handleDelete(parts, taskList);
        }, "Should throw an exception for invalid task number");
    }

    @Test
    void handleUnmark_withValidInput() throws JayneException {
        // Setup initial conditions
        Todo todo = new Todo("Sample Task");
        todo.markAsDone();
        taskList.addTask(todo);
        String[] parts = {"unmark", "1"};

        // Perform action
        Handler.handleUnmark(parts, taskList);

        // Check results
        assertFalse(taskList.getTask(1).isCompleted(), "Task should be marked as not done");
    }

    @Test
    void handleMark_withValidInput() throws JayneException {
        // Setup initial conditions
        Todo todo = new Todo("Sample Task");
        taskList.addTask(todo);
        String[] parts = {"mark", "1"};

        // Perform action
        Handler.handleMark(parts, taskList);

        // Check results
        assertTrue(taskList.getTask(1).isCompleted(), "Task should be marked as done");
    }
}

