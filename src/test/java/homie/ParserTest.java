package homie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    void parseTodoCommand_validCommand_success() throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList, ui, storage);
        String expectedMessage;
        // Test case 1
        expectedMessage = "Got it. I've added this task:\n"
                + "\t" + "[T][ ] read book"
                + "\nNow you have 1 tasks in the list.";
        String outputMessage = parser.parse("todo read book");
        assertEquals(expectedMessage, outputMessage);
        // Test case 2
        expectedMessage = "Got it. I've added this task:\n"
                + "\t" + "[T][ ] read manga"
                + "\nNow you have 2 tasks in the list.";
        outputMessage = parser.parse("todo read manga");
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    void parseTodoCommand_emptyDescription_failure() throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList, ui, storage);
        String expectedMessage = "Bruh... No description given!"
                + "\nPlease follow this format:"
                + "\ntodo {TODO_DESCRIPTION}";
        // Test case 1: no description
        try {
            parser.parse("todo");
            fail();
        } catch (TodoException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(0, taskList.getSize());
        }
        // Test case 2: one empty white space
        try {
            parser.parse("todo ");
            fail();
        } catch (TodoException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(0, taskList.getSize());
        }
        // Test case 3: two empty white space
        try {
            parser.parse("todo  ");
            fail();
        } catch (TodoException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(0, taskList.getSize());
        }
    }
    @Test
    void parseDeadlineCommand_validDeadlineCommand_success() throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList, ui, storage);
        String expectedMessage;
        // Test case 1
        expectedMessage = "Got it. I've added this task:\n"
                + "\t[D][ ] cs2103 quiz 3 (by: 03-23-2024 23:59)"
                + "\nNow you have 1 tasks in the list.\n";
        String outputMessage = parser.parse("deadline cs2103 quiz 3 /by 23 03 2024 2359");
        assertEquals(expectedMessage, outputMessage);
        // Test case 2
        expectedMessage = "Got it. I've added this task:\n"
                + "\t[D][ ] cs2107 assignment (by: 02-28-2024 23:59)"
                + "\nNow you have 2 tasks in the list.\n";
        outputMessage = parser.parse("deadline cs2107 assignment /by 28 02 2024 2359");
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    void parseDeadlineCommand_emptyDescriptionAndInvalidDateCommand_failure() throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList, ui, storage);
        String expectedMessage = "Bruh... No description given!"
                + "\nPlease follow the format:\ndeadline "
                + "{DEADLINE_DESCRIPTION} /by {dd MM yyyy HHmm}";
        // Test case 1: no description
        try {
            parser.parse("deadline");
            fail();
        } catch (DeadlineException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(0, taskList.getSize());
        }
        // Test case 2: incorrect date format
        expectedMessage = "Bruh... Invalid date format!"
                + "\nPlease follow the format:\ndeadline "
                + "{DEADLINE_DESCRIPTION} /by {dd MM yyyy HHmm}";
        try {
            parser.parse("deadline cs2103 quiz /by 23022024 3pm");
            fail();
        } catch (DeadlineException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(0, taskList.getSize());
        }
    }
}
