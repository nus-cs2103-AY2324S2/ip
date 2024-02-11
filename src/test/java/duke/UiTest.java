package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Test class for the Ui class, which is responsible for handling user interface interactions.
 */
public class UiTest {

    /**
     * Tests the showWelcomeMessage method, verifying the output in the console.
     */
    @Test
    public void testShowWelcomeMessage() {
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ui.showWelcomeMessage();

        String expectedOutput = "____________________________________________________________\n"
                + " Hello! I'm Duck\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
        System.setOut(System.out); // Reset System.out to the console
    }

    /**
     * Tests the showGoodbyeMessage method, verifying the output in the console.
     */
    @Test
    public void testShowGoodbyeMessage() {
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ui.showGoodbyeMessage();

        String expectedOutput = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
        System.setOut(System.out); // Reset System.out to the console
    }

    /**
     * Tests the showTaskList method, verifying the output in the console.
     */
    @Test
    public void testShowTaskList() {
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1"));
        tasks.add(new Task("Task 2"));

        ui.showTaskList(tasks);

        String expectedOutput = "____________________________________________________________\n"
                + " Here are the 2 tasks in your list:\n"
                + " 1.[ ][ ] Task 1\n"
                + " 2.[ ][ ] Task 2\n"
                + "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
        System.setOut(System.out); // Reset System.out to the console
    }
}

