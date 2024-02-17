package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.action.Action;
import duke.action.Delete;
import duke.action.Echo;
import duke.action.Farewell;
import duke.action.Mark;
import duke.action.TaskList;
import duke.action.Unmark;
import duke.exception.EmptyDescriptionException;
import duke.exception.NoIndexException;
import duke.exception.UnknownCommandException;
import duke.exception.WrongDateFormatException;
import duke.exception.WrongDateOrderingException;




public class CommandParserTest {

    @Test
    void test_parseCommand_validCommands() {
        TaskList taskList = new TaskList();
        Action action;

        try {
            // Test "bye" command
            action = CommandParser.parseCommand("bye", taskList);
            assertTrue(action instanceof Farewell);

            // Test "list" command
            action = CommandParser.parseCommand("list", taskList);
            assertTrue(action instanceof TaskList);

            // Test "mark" command
            action = CommandParser.parseCommand("mark 1", taskList);
            assertTrue(action instanceof Mark);

            // Test "unmark" command
            action = CommandParser.parseCommand("unmark 1", taskList);
            assertTrue(action instanceof Unmark);

            // Test "todo" command
            action = CommandParser.parseCommand("todo Task description", taskList);
            assertTrue(action instanceof Echo);

            // Test "delete" command
            action = CommandParser.parseCommand("delete 1", taskList);
            assertTrue(action instanceof Delete);

            // Test "deadline" command
            action = CommandParser.parseCommand("deadline Task description /by 2024-02-03", taskList);
            assertTrue(action instanceof Echo);

            // Test "event" command
            action = CommandParser.parseCommand("event Task description /from 2024-02-03 /to 2024-02-04", taskList);
            assertTrue(action instanceof Echo);

        } catch (Exception e) {
            fail("Exception thrown for valid command: " + e.getMessage());
        }
    }

    @Test
    void test_parseCommand_invalidCommands() {
        TaskList taskList = new TaskList();

        assertThrows(UnknownCommandException.class, () ->
                CommandParser.parseCommand("invalidCommand", taskList));

        assertThrows(NoIndexException.class, () ->
                CommandParser.parseCommand("mark", taskList));

        assertThrows(EmptyDescriptionException.class, () ->
                CommandParser.parseCommand("todo", taskList));

        assertThrows(WrongDateFormatException.class, () ->
                CommandParser.parseCommand("deadline Task description /by invalidDate", taskList));

        assertThrows(WrongDateOrderingException.class, () ->
                CommandParser.parseCommand("event Task description /from 2024-02-04 /to 2024-02-03", taskList));
    }
}

