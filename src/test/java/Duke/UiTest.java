package Duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

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

    // Add more test methods for other Ui methods...

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

    // Add more test methods for other Ui methods...
}
