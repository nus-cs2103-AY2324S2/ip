package jayne.task;

import jayne.Storage;

import jayne.JayneException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private TaskList taskList;
    private Storage storage;

    private static final String FILE_PATH = "./out/test.txt";
    @BeforeEach
    void setUp() throws JayneException {
        this.storage = new Storage(FILE_PATH);
        taskList = new TaskList(storage);
    }

    @AfterEach
    void tearDown() {
        // Delete the file after each test
        taskList.getStorage().deleteFile();
    }

    @Test
    void addTask_addsTaskCorrectly() throws JayneException {
        // Given
        Task newTask = new Todo("Test Todo");

        // When
        taskList.addTask(newTask);

        // Then
        assertEquals(1, taskList.getTaskCount(), "Task count should increase after adding a task");
        assertSame(newTask, taskList.getTask(1), "The added task should be retrievable from the task list");
    }

    @Test
    void deleteTask_deletesTaskCorrectly() throws JayneException {
        // Given
        Task task1 = new Todo("Test Todo 1");
        Task task2 = new Todo("Test Todo 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        // When
        Task deletedTask = taskList.deleteTask(1);

        // Then
        assertEquals(1, taskList.getTaskCount(), "Task count should decrease after deleting a task");
        assertSame(task1, deletedTask, "The deleted task should be the correct one");
        assertSame(task2, taskList.getTask(1), "Remaining task should be correctly positioned in the list");
    }

    @Test
    void deleteTask_withInvalidIndex_throwsException() throws JayneException {
        // Given
        Task task = new Todo("Test Todo");
        taskList.addTask(task);

        // When & Then
        assertThrows(JayneException.class, () -> taskList.deleteTask(2), "Should throw exception for invalid task number");
    }
}

