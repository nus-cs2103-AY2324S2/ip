package processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class FindTaskProcessorTest {
    private TaskList taskList;
    private Ui chatbotUi;
    private Factory factory;
    private FindTaskProcessor findTaskProcessor;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        chatbotUi = new Ui();
        factory = new Factory(taskList, chatbotUi);
        findTaskProcessor = factory.createFindTaskProcessor();
    }


    @Test
    public void testProcessCommand_withEmptyTaskList() {
        // Redirect System.out to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStream));
        // Execute
        findTaskProcessor.processCommand("find Task");

        

        // Verify
        String expectedOutput = "No matching tasks in your list.";

        assertEquals(expectedOutput, outputStream.toString().trim());

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testProcessCommand_withPartialMatch() {
        // Set up
        taskList.addTask(new Task("desc", false));
        taskList.addTask(new Task("desc", false));
        taskList.addTask(new Task("Another Task", false));

        // Redirect System.out to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Execute
        findTaskProcessor.processCommand("find Task");

        // Verify
        String expectedOutput = "Here are the matching tasks in your list:";
        assertTrue(outputStream.toString().trim().contains(expectedOutput));

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testProcessCommand_withCaseInsensitiveMatch() {
        // Set up
        taskList.addTask(new Task("desc", false));
        taskList.addTask(new Task("desc", false));
        taskList.addTask(new Task("Another Task", false));

        // Redirect System.out to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Execute
        findTaskProcessor.processCommand("find desc");

        // Verify
        String expectedOutput = "desc";
        assertTrue(outputStream.toString().trim().contains(expectedOutput));

        // Reset System.out
        System.setOut(System.out);
    }
}