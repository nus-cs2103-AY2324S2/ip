package TaskFlow.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import TaskFlow.Parser;
import TaskFlow.exception.DukeException;

/**
 * A test class for testing the functionality of the Parser class.
 */
public class ParserTest {

    /**
     * To test that parsing an exit command returns the expected ExitCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validExitCommand_exitCommandReturned() throws DukeException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    /**
     * To test that parsing a list command returns the expected ListCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validListCommand_listCommandReturned() throws DukeException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    /**
     * To test that parsing a ToDo command returns the expected ToDoCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validToDoCommand_toDoCommandReturned() throws DukeException {
        Command command = Parser.parse("todo Buy groceries");
        assertTrue(command instanceof ToDoCommand);
    }

    /**
     * To test that parsing a delete command returns the expected DeleteCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validDeleteCommand_deleteCommandReturned() throws DukeException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    /**
     * To test that parsing a mark command returns the expected MarkCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validMarkCommand_markCommandReturned() throws DukeException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    /**
     * To test that parsing an unmark command returns the expected UnmarkCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validUnmarkCommand_unmarkCommandReturned() throws DukeException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }

    /**
     * To test that parsing a deadline command returns the expected DeadlineCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validDeadlineCommand_deadlineCommandReturned() throws DukeException {
        Command command = Parser.parse("deadline Finish project /by 2022-12-31 12:00pm");
        assertTrue(command instanceof DeadlineCommand);
    }

    /**
     * To test that parsing an event command returns the expected EventCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validEventCommand_eventCommandReturned() throws DukeException {
        Command command = Parser.parse("event Birthday party /from 2022-12-31 /to 2023-01-01");
        assertTrue(command instanceof EventCommand);
    }

    /**
     * To test that parsing a find command returns the expected FindCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validFindCommand_findCommandReturned() throws DukeException {
        Command command = Parser.parse("find important");
        assertTrue(command instanceof FindCommand);
    }

    /**
     * To test that parsing an archive command returns the expected ArchiveCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validArchiveCommand_archiveCommandReturned() throws DukeException {
        Command command = Parser.parse("archive 1");
        assertTrue(command instanceof ArchiveCommand);
    }

    /**
     * To test that parsing an unarchive command returns the expected UnarchiveCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validUnarchiveCommand_unarchiveCommandReturned() throws DukeException {
        Command command = Parser.parse("unarchive 1");
        assertTrue(command instanceof UnarchiveCommand);
    }

    /**
     * To test that parsing a help command returns the expected HelpCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validHelpCommand_helpCommandReturned() throws DukeException {
        Command command = Parser.parse("help ");
        assertTrue(command instanceof HelpCommand);
    }

    /**
     * To test that parsing a list archive command returns the expected ListArchiveCommand instance.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void parse_validListArchiveCommand_listArchiveCommandReturned()
            throws DukeException {
        Command command = Parser.parse("list archive");
        assertTrue(command instanceof ListArchiveCommand);
    }

    /**
     * Test case to ensure that parsing commands with different cases (upper case, lower case, camel case)
     * returns the corresponding valid Command instances.
     *
     * @throws DukeException If there is an unexpected Duke related exception during the test.
     */
    @Test
    public void parse_upperCaseCommands_validCommandReturned() throws DukeException {
        Command c1 = Parser.parse("BYE");
        Command c2 = Parser.parse("Delete 1");
        Command c3 = Parser.parse("DEADLINE Finish project /by 2022-12-31 12:00pm");
        Command c4 = Parser.parse("toDo Buy groceries");
        Command c5 = Parser.parse("eveNT Birthday party /from 2022-12-31 /to 2023-01-01");

        assertTrue(c1 instanceof ExitCommand);
        assertTrue(c2 instanceof DeleteCommand);
        assertTrue(c3 instanceof DeadlineCommand);
        assertTrue(c4 instanceof ToDoCommand);
        assertTrue(c5 instanceof EventCommand);
    }
}
