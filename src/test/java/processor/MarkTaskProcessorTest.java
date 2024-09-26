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

public class MarkTaskProcessorTest {
    private MarkTaskProcessor processor;
    private TaskList taskList;
    private Ui chatbotUi;

    @BeforeEach
    public void setUp() {
        taskList = new tasks.TaskList();
        chatbotUi = new Ui();
        processor = new MarkTaskProcessor(taskList, chatbotUi);
        taskList.addTask(new Task("Task 1", false));
        taskList.addTask(new Task("Task 2", false));
        taskList.addTask(new Task("Task 3", false));
    }

    @Test
    public void testProcessCommand_ValidInput() throws IOException {
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        processor.processCommand("mark 1");

        String expectedOutput = "Nice! I've marked this task as done:";

        // Assert
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @Test
    public void testProcessCommand_InvalidNumberFormat() throws IOException {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        processor.processCommand("mark abc");

        String expectedOutput = "You must use a number to mark.";

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testProcessCommand_IndexOutOfBounds() throws IOException {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        processor.processCommand("mark 500");

        String expectedOutput = "You must select a number within the size of the Task List.";
        
        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }
}