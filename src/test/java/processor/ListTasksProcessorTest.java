package processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class ListTasksProcessorTest {
    private TaskList taskList;
    private Ui chatbotUi;
    private ListTasksProcessor listTasksProcessor;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        chatbotUi = new Ui();
        listTasksProcessor = new ListTasksProcessor(taskList, chatbotUi);
    }

    @Test
    public void testProcessCommand_emptyTaskList_printsEmptyListMessage() throws IOException {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        listTasksProcessor.processCommand("");

        // Assert
        String expectedOutput = "Your list is empty";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testProcessCommand_nonEmptyTaskList_printsTaskList() throws IOException {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Add tasks to the task list
        taskList.addTask(new Task("Task 1", false));
        taskList.addTask(new Task("Task 2", false));
        taskList.addTask(new Task("Task 3", false));

        // Act
        listTasksProcessor.processCommand("");

        // Assert
        String expectedOutput = "Here are the tasks in your list:";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }
}