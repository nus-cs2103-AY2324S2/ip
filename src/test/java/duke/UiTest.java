package duke;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import duke.Tasks.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UiTest {

    @Test
    public void testShowAddedMessage() {
        Ui ui = new Ui();
        String taskDescription = "return book";
        Task task = new TodoTask(taskDescription);

        int count = 1;
        String expectedMessage = "   ____________________________________________________________\n" +
                                 "    Got it. I've added this task:\n" +
                                 "      " + task.tag() + task.mark() + " " + taskDescription + "\n" +
                                 "    Now you have " + count + " tasks in the list.\n" +
                                 "   ____________________________________________________________\n";

        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method
        ui.showAddedMessage(task, count);

        // Reset System.out
        System.setOut(System.out);

        // Check the output
        assertEquals(expectedMessage, outContent.toString());
    }
}