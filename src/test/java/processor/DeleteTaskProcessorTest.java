package processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class DeleteTaskProcessorTest {
    private TaskList taskList;
    private Ui chatbotUi;
    private DeleteTaskProcessor deleteTaskProcessor;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        chatbotUi = new Ui();
        deleteTaskProcessor = new DeleteTaskProcessor(taskList, chatbotUi);
    }

    @Test
    public void testProcessCommand_ValidInput_DeletesTask() throws IOException {
        // Arrange
        String userInput = "delete 1";
        Task taskToDelete = new Task("desc", false);
        taskList.addTask(taskToDelete);

        // Act
        deleteTaskProcessor.processCommand(userInput);

        // Assert
        assertEquals(0, taskList.size());
    }

    @Test
    public void testProcessCommand_InvalidInput_NumberFormatExceptionThrown() {
        // Arrange
        String userInput = "delete abc";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        deleteTaskProcessor.processCommand(userInput);

        // Assert
        assertEquals("You must use a number to delete successfully.", outputStream.toString().trim());
    }

    @Test
    public void testProcessCommand_InvalidInput_IndexOutOfBoundsExceptionThrown() {
        // Arrange
        String userInput = "delete 500";
        taskList.addTask(new Task("desc", false));
        taskList.addTask(new Task("desc", false));
        taskList.addTask(new Task("desc", false));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        deleteTaskProcessor.processCommand(userInput);

        // Assert
        assertTrue(outputStream.toString().trim().contains("You must select a number within the scope of the task list"));
    }
}