package ciara.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import ciara.commands.AddCommand;
import ciara.commands.Command;
import ciara.commands.DeleteCommand;
import ciara.commands.ExitCommand;
import ciara.commands.FindCommand;
import ciara.commands.ListCommand;
import ciara.commands.MarkCommand;
import ciara.exceptions.CiaraException;
import ciara.exceptions.InvalidArgumentException;
import ciara.exceptions.InvalidCommandException;
import ciara.exceptions.MissingArgumentException;
import ciara.storage.Deadline;
import ciara.storage.Event;
import ciara.storage.Todo;

/**
 * Test cases for methods in the Parser Class
 *
 * @author RyanNgWH
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ParserTest {
    /**
     * Tests successful user date to instant conversion
     */
    @Test
    public void userDateToInstant_validDate_success() {
        Instant expected = Instant.ofEpochSecond(1706518200);

        String date = "2024/01/29";
        String time = "16:50";

        assertEquals(expected, Parser.userDateToInstant(date, time));
    }

    /**
     * Tests parsing of successful 'bye' command
     */
    @Test
    public void parse_bye_success() throws CiaraException {
        Command expected = new ExitCommand();

        assertEquals(expected, Parser.parse("bye"));
        assertEquals(expected, Parser.parse("Bye"));
    }

    /**
     * Tests parsing of successful 'list' command without filters
     */
    @Test
    public void parse_listNoFilter_success() throws CiaraException {
        Command expected = new ListCommand(false);

        assertEquals(expected, Parser.parse("list"));
        assertEquals(expected, Parser.parse("list "));
        assertEquals(expected, Parser.parse("list  "));
        assertEquals(expected, Parser.parse("  list  "));
        assertEquals(expected, Parser.parse("List"));
    }

    /**
     * Tests parsing of successful 'list' command with date filter
     */
    @Test
    public void parse_listDateFilter_success() throws CiaraException {
        Command expected = new ListCommand(Instant.ofEpochSecond(1706457600), false);

        assertEquals(expected, Parser.parse("list /date 2024/01/29"));
        assertEquals(expected, Parser.parse("list /date 2024/01/29 "));
        assertEquals(expected, Parser.parse(" list /date 2024/01/29 "));
        assertEquals(expected, Parser.parse("list  /date 2024/01/29"));
        assertEquals(expected, Parser.parse("list /date  2024/01/29"));
        assertEquals(expected, Parser.parse("List /date  2024/01/29"));
    }

    /**
     * Tests parsing of successful 'list' command of archived tasks
     */
    @Test
    public void parse_listArchivedTasks_success() throws CiaraException {
        Command expected = new ListCommand(true);

        assertEquals(expected, Parser.parse("list /archive"));
        assertEquals(expected, Parser.parse("list /archive "));
        assertEquals(expected, Parser.parse("list /archive  "));
        assertEquals(expected, Parser.parse("  list /archive  "));
        assertEquals(expected, Parser.parse("List /archive"));
    }

    /**
     * Tests parsing of 'list' command with date filter with malformed date
     */
    @Test
    public void parse_listDateFilter_exceptionThrown() throws CiaraException {
        String expectedMessage = "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD'";

        InvalidArgumentException exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("list /date  "));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class, () -> Parser.parse("list /date 2024"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class, () -> Parser.parse("list /date 2024-122-12"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class, () -> Parser.parse("list /date 2024-25-25"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class, () -> Parser.parse("list /date 12pm"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class, () -> Parser.parse("list /date 12:00"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class, () -> Parser.parse("List /date 12:00"));
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests parsing of 'list' command with of archived tasks with date filter
     * with malformed date
     */
    @Test
    public void parse_listArchivedDateFilter_exceptionThrown() throws CiaraException {
        String expectedMessage = "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD'";

        InvalidArgumentException exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("list /archive /date  "));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class,
                () -> Parser.parse("list /archive /date 2024"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class,
                () -> Parser.parse("list /archive /date 2024-122-12"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class,
                () -> Parser.parse("list /archive /date 2024-25-25"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class,
                () -> Parser.parse("list /archive /date 12pm"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class,
                () -> Parser.parse("list /archive /date 12:00"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class,
                () -> Parser.parse("List /archive /date 12:00"));
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests parsing of 'list' command with unknown filter
     */
    @Test
    public void parse_listUnknownFilter_exceptionThrown() throws CiaraException {
        String expectedMessage = "Unknown argument 'fake' for the 'list' command";

        InvalidArgumentException exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("list fake  "));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("List fake"));
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests parsing of 'list' command of archived tasks with unknown filter
     */
    @Test
    public void parse_listArchivedUnknownFilter_exceptionThrown() throws CiaraException {
        String expectedMessage = "Unknown argument 'fake' for the 'list' command";

        InvalidArgumentException exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("list /archive fake  "));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("List /archive fake"));
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests parsing of successful 'mark' command
     */
    @Test
    public void parse_mark_success() throws CiaraException {
        Command expected = new MarkCommand(0, true);

        assertEquals(expected, Parser.parse("mark 1"));
        assertEquals(expected, Parser.parse("mark 1 "));
        assertEquals(expected, Parser.parse(" mark 1"));
        assertEquals(expected, Parser.parse(" mark 1 "));
        assertEquals(expected, Parser.parse("Mark 1"));
    }

    /**
     * Tests parsing of 'mark' command with missing/malformed argument
     */
    @Test
    public void parse_mark_exceptionThrown() throws CiaraException {
        String expectedMessage = "Missing argument - Index of task required";
        MissingArgumentException missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("mark"));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("mark "));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse(" mark"));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse(" mark "));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("Mark"));
        assertEquals(expectedMessage, missingException.getMessage());

        expectedMessage = "Index to mark is not an integer";
        InvalidArgumentException invalidException = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("mark me"));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("mark me "));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse(" mark me"));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse(" mark me "));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("Mark me"));
        assertEquals(expectedMessage, invalidException.getMessage());
    }

    /**
     * Tests parsing of successful 'unmark' command
     */
    @Test
    public void parse_unmark_success() throws CiaraException {
        Command expected = new MarkCommand(0, false);

        assertEquals(expected, Parser.parse("unmark 1"));
        assertEquals(expected, Parser.parse("unmark 1 "));
        assertEquals(expected, Parser.parse(" unmark 1"));
        assertEquals(expected, Parser.parse(" unmark 1 "));
        assertEquals(expected, Parser.parse("Unmark 1"));
    }

    /**
     * Tests parsing of 'unmark' command with missing/malformed argument
     */
    @Test
    public void parse_unmark_exceptionThrown() throws CiaraException {
        String expectedMessage = "Missing argument - Index of task required";
        MissingArgumentException missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("unmark"));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(MissingArgumentException.class, () -> Parser.parse("unmark "));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(MissingArgumentException.class, () -> Parser.parse(" unmark"));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(MissingArgumentException.class, () -> Parser.parse(" unmark "));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(MissingArgumentException.class, () -> Parser.parse("Unmark"));
        assertEquals(expectedMessage, missingException.getMessage());

        expectedMessage = "Index to unmark is not an integer";
        InvalidArgumentException invalidException = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("unmark me"));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(InvalidArgumentException.class, () -> Parser.parse("unmark me "));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(InvalidArgumentException.class, () -> Parser.parse(" unmark me"));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(InvalidArgumentException.class, () -> Parser.parse(" unmark me "));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(InvalidArgumentException.class, () -> Parser.parse("Unmark me"));
        assertEquals(expectedMessage, invalidException.getMessage());
    }

    /**
     * Tests parsing of successful 'delete' command
     */
    @Test
    public void parse_delete_success() throws CiaraException {
        Command expected = new DeleteCommand(0, false);

        assertEquals(expected, Parser.parse("delete 1"));
        assertEquals(expected, Parser.parse("delete 1 "));
        assertEquals(expected, Parser.parse(" delete 1"));
        assertEquals(expected, Parser.parse(" delete 1 "));
        assertEquals(expected, Parser.parse("delete  1"));
        assertEquals(expected, Parser.parse("Delete 1"));
    }

    /**
     * Tests parsing of 'delete' command with missing/malformed argument
     */
    @Test
    public void parse_delete_exceptionThrown() throws CiaraException {
        String expectedMessage = "Missing argument - Index of task required";
        MissingArgumentException missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("delete"));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("delete "));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse(" delete"));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse(" delete "));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("Delete"));
        assertEquals(expectedMessage, missingException.getMessage());

        expectedMessage = "Index to delete is not an integer";
        InvalidArgumentException invalidException = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("delete me"));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("delete me "));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse(" delete me"));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse(" delete me "));
        assertEquals(expectedMessage, invalidException.getMessage());

        invalidException = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("Delete me"));
        assertEquals(expectedMessage, invalidException.getMessage());
    }

    /**
     * Tests parsing of successful 'todo' command
     */
    @Test
    public void parse_todo_success() throws CiaraException {
        Command expected = new AddCommand(new Todo("Nights Into Days"));

        assertEquals(expected, Parser.parse("todo Nights Into Days"));
        assertEquals(expected, Parser.parse("todo Nights Into Days "));
        assertEquals(expected, Parser.parse(" todo Nights Into Days"));
        assertEquals(expected, Parser.parse("Todo Nights Into Days"));
    }

    /**
     * Tests parsing of 'todo' command with missing argument
     */
    @Test
    public void parse_todo_exceptionThrown() throws CiaraException {
        String expectedMessage = "Missing argument - Description of a todo cannot be empty";
        MissingArgumentException missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("todo"));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("todo "));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse(" todo"));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse(" todo "));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("Todo"));
        assertEquals(expectedMessage, missingException.getMessage());
    }

    /**
     * Tests parsing of successful 'deadline' command
     */
    @Test
    public void parse_deadline_success() throws CiaraException {
        Command expected = new AddCommand(new Deadline("Nights Into Days", Instant.ofEpochSecond(1706500800)));

        assertEquals(expected, Parser.parse(" deadline Nights Into Days /by 2024/01/29 12:00"));
        assertEquals(expected, Parser.parse("deadline Nights Into Days /by 2024/01/29 12:00 "));
        assertEquals(expected, Parser.parse(" deadline Nights Into Days /by 2024/01/29 12:00 "));
        assertEquals(expected, Parser.parse("Deadline Nights Into Days /by 2024/01/29 12:00"));
    }

    /**
     * Tests parsing of 'deadline' command with missing/malformed argument
     */
    @Test
    public void parse_deadline_exceptionThrown() throws CiaraException {
        String expectedMessage = "Missing Argument - Argument '/by' missing";
        MissingArgumentException missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("deadline test"));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("deadline test "));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse(" deadline test"));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse(" deadline test "));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("Deadline test"));
        assertEquals(expectedMessage, missingException.getMessage());

        // Invalid date format
        expectedMessage = "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD hh:mm'";

        InvalidArgumentException exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("deadline test /by"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class, () -> Parser.parse("deadline test /by 2024"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("deadline test /by 2024-122-12"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("deadline test /by 2024-25-25"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class, () -> Parser.parse("deadline test /by 12pm"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class, () -> Parser.parse("deadline test /by 12:00"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("deadline test /by 2024/12/12"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(InvalidArgumentException.class, () -> Parser.parse("Deadline test /by 12:00"));
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests parsing of successful 'event' command
     */
    @Test
    public void parse_event_success() throws CiaraException {
        Command expected = new AddCommand(new Event("Nights Into Days", Instant.ofEpochSecond(1706500800),
                Instant.ofEpochSecond(1706508000)));

        assertEquals(expected,
                Parser.parse(" event Nights Into Days /from 2024/01/29 12:00 /to 2024/01/29 14:00"));
        assertEquals(expected,
                Parser.parse("event Nights Into Days /from 2024/01/29 12:00 /to 2024/01/29 14:00 "));
        assertEquals(expected,
                Parser.parse(" event Nights Into Days /from 2024/01/29 12:00 /to 2024/01/29 14:00 "));
        assertEquals(expected,
                Parser.parse("Event Nights Into Days /from 2024/01/29 12:00 /to 2024/01/29 14:00"));
    }

    /**
     * Tests parsing of 'event' command with missing/malformed argument
     */
    @Test
    public void parse_event_exceptionThrown() throws CiaraException {
        String expectedMessage = "Missing Argument - Argument '/from' missing";
        MissingArgumentException missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("event test"));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("event test "));
        assertEquals(expectedMessage, missingException.getMessage());

        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse(" event test"));
        assertEquals(expectedMessage, missingException.getMessage());

        expectedMessage = "Missing Argument - Argument '/to' missing";
        missingException = assertThrows(
                MissingArgumentException.class, () -> Parser.parse("event test /from"));
        assertEquals(expectedMessage, missingException.getMessage());

        // Invalid date format
        expectedMessage = "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD hh:mm'";

        InvalidArgumentException exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("event test /from test /to test"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(
                InvalidArgumentException.class, (

                ) -> Parser.parse("event test /from 2024/12/12 /to test"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(
                InvalidArgumentException.class, (

                ) -> Parser.parse("event test /from 2024-122-12 /to 2024/12/12"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(
                InvalidArgumentException.class, (

                ) -> Parser.parse("event test /from 2024-25-25 /to 2024/12/12"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("event test /from 12pm /to 2pm"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(
                InvalidArgumentException.class, () -> Parser.parse("event test /from 12:00 /to 14:00"));
        assertEquals(expectedMessage, exception.getMessage());

        exception = assertThrows(
                InvalidArgumentException.class, (

                ) -> Parser.parse("event test /from 2024/12/12 14:00 /to 2024/12/12"));
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests parsing of successful 'find' command
     */
    @Test
    public void parse_find_success() throws CiaraException {
        Command expected = new FindCommand("Back To Alonica", false);

        assertEquals(expected, Parser.parse("find Back To Alonica"));
        assertEquals(expected, Parser.parse("find Back To Alonica "));
        assertEquals(expected, Parser.parse(" find Back To Alonica "));
        assertEquals(expected, Parser.parse("Find Back To Alonica"));

        // Test no arguments
        expected = new FindCommand(false);
        assertEquals(expected, Parser.parse("find"));
        assertEquals(expected, Parser.parse(" find"));
        assertEquals(expected, Parser.parse("find "));
        assertEquals(expected, Parser.parse("Find"));
    }

    /**
     * Tests parsing of unknown command
     */
    @Test
    public void parse_unknown_exceptionThrown() throws CiaraException {
        String expectedMessage = "Unknown command 'alonica'";
        InvalidCommandException missingException = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("alonica"));
        assertEquals(expectedMessage, missingException.getMessage());
    }
}
