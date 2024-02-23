package quacky;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    void parseCommand_listCommand_success() {
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
            fail();
        }
    }

    @Test
    void parseCommand_deadlineWithNoDate_handlesError() {
        try {
            TaskList tasks = new TaskList();
            UI ui = new UI();
            String command = "deadline /by";
            String expectedOutput = "Quack? (Please provide a date for the deadline)";
            String actualOutput = Parser.parseCommand(command, tasks, ui);
            assertEquals(expectedOutput, actualOutput);
        } catch (QuackyException e) {
            fail();
        }
    }

    @Test
    void parseCommand_deleteTaskOutOfBounds_handlesError() {
        try {
            TaskList tasks = new TaskList();
            UI ui = new UI();
            String command = "delete 1";
            Parser.parseCommand(command, tasks, ui);
            String expectedOutput = "Quack. The task is not found";
            String actualOutput = Parser.parseCommand(command, tasks, ui);
            assertEquals(expectedOutput, actualOutput);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parseCommand_unknownCommand_throwsException() {
        try {
            TaskList tasks = new TaskList();
            UI ui = new UI();
            String command = "sort";
            Parser.parseCommand(command, tasks, ui);
            String actualOutput = Parser.parseCommand(command, tasks, ui);
            fail();
        } catch (QuackyException e) {
            String expectedErrorMessage = "Quack? (I dont know what that means. Try another command)";
            String actualErrorMessage = e.getMessage();
            assertEquals(expectedErrorMessage,actualErrorMessage);
        }
    }
}
