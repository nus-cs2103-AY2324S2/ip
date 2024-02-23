package TaskFlow.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import TaskFlow.Parser;
import TaskFlow.exception.DukeException;

public class ParserTest {
    @Test
    public void parse_validExitCommand_exitCommandReturned() throws DukeException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parse_validListCommand_listCommandReturned() throws DukeException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_validToDoCommand_toDoCommandReturned() throws DukeException {
        Command command = Parser.parse("todo Buy groceries");
        assertTrue(command instanceof ToDoCommand);
    }

    @Test
    public void parse_validDeleteCommand_deleteCommandReturned() throws DukeException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parse_validMarkCommand_markCommandReturned() throws DukeException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parse_validUnmarkCommand_unmarkCommandReturned() throws DukeException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void parse_validDeadlineCommand_deadlineCommandReturned() throws DukeException {
        Command command = Parser.parse("deadline Finish project /by 2022-12-31 12:00pm");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void parse_validEventCommand_eventCommandReturned() throws DukeException {
        Command command = Parser.parse("event Birthday party /from 2022-12-31 /to 2023-01-01");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void parse_validFindCommand_findCommandReturned() throws DukeException {
        Command command = Parser.parse("find important");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void parse_validArchiveCommand_archiveCommandReturned() throws DukeException {
        Command command = Parser.parse("archive 1");
        assertTrue(command instanceof ArchiveCommand);
    }

    @Test
    public void parse_validUnarchiveCommand_unarchiveCommandReturned() throws DukeException {
        Command command = Parser.parse("unarchive 1");
        assertTrue(command instanceof UnarchiveCommand);
    }

    @Test
    public void parse_validHelpCommand_helpCommandReturned() throws DukeException {
        Command command = Parser.parse("help ");
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    public void parse_validListArchiveCommand_listArchiveCommandReturned()
            throws DukeException {
        Command command = Parser.parse("list archive");
        assertTrue(command instanceof ListArchiveCommand);
    }

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
