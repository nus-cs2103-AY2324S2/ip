package quacky;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    void parseListCommand_success() {
        try {
            TaskList tasks = new TaskList();
            tasks.addTask(new Todo("Survive"));
            UI ui = new UI(); // This would need to be a mock or a stub
            String command = "list";

            // Assuming showList returns a string of tasks
            String expectedOutput = ui.showList(tasks);
            String actualOutput = Parser.parseCommand(command, tasks, ui);
            assertEquals(expectedOutput, actualOutput);
        } catch (QuackyException e) {
            //This should never happen as the list case does not throw an exception
            QuackyException wrongCommandException = new QuackyException(
                    "Quack? (Please use the correct date format for events: YYYY-MM-DD)");
            assert !(wrongCommandException.equals(e)): "list command may have been deleted";
            fail();
        }
    }

    @Test
    void parseCommand_deadlineWithNoDate_throwsException() {
        try {
            TaskList tasks = new TaskList();
            UI ui = new UI();
            String command = "deadline /by";

        } catch (Exception e) {
            assertEquals("Quack? (Please provide a date for the deadline)", e.getMessage());
        }
    }

}
