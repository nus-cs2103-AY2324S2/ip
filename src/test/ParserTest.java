package seedu.talkingcat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_addTodoCommand() throws TalkingCatException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddTodoCommand, "Parser should return AddTodoCommand for 'todo' input.");
    }

    @Test
    public void parse_invalidCommand() {
        assertThrows(TalkingCatException.class, () -> {
            Parser.parse("invalid command");
        });
    }

    @Test
    public void parse_addDeadlineCommand_correctFormat() throws TalkingCatException {
        Command command = Parser.parse("deadline return book /by 2022-12-01 1800");
        assertTrue(command instanceof AddDeadlineCommand, "Parser should return AddDeadlineCommand for 'deadline' input with correct format.");
    }

    @Test
    public void parse_addDeadlineCommand_incorrectFormat() {
        assertThrows(TalkingCatException.class, () -> {
            Parser.parse("deadline return book by next Monday");
        }, "Parser should throw TalkingCatException for 'deadline' input with incorrect format.");
    }

    @Test
    public void parse_addEventCommand_correctFormat() throws TalkingCatException {
        Command command = Parser.parse("event project meeting /at 2022-12-05 1400 to 2022-12-05 1600");
        assertTrue(command instanceof AddEventCommand, "Parser should return AddEventCommand for 'event' input with correct format.");
    }

    @Test
    public void parse_addEventCommand_incorrectFormat() {
        assertThrows(TalkingCatException.class, () -> {
            Parser.parse("event project meeting at this Friday");
        }, "Parser should throw TalkingCatException for 'event' input with incorrect format.");
    }

    @Test
    public void parse_markCommand_validIndex() throws TalkingCatException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand, "Parser should return MarkCommand for 'mark' input with a valid index.");
    }

    @Test
    public void parse_markCommand_invalidIndex() {
        assertThrows(TalkingCatException.class, () -> {
            Parser.parse("mark one");
        }, "Parser should throw TalkingCatException for 'mark' input with an invalid index.");
    }

    @Test
    public void parse_unmarkCommand_validIndex() throws TalkingCatException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand, "Parser should return UnmarkCommand for 'unmark' input with a valid index.");
    }

    @Test
    public void parse_unmarkCommand_invalidIndex() {
        assertThrows(TalkingCatException.class, () -> {
            Parser.parse("unmark one");
        }, "Parser should throw TalkingCatException for 'unmark' input with an invalid index.");
    }

    @Test
    public void parse_deleteCommand_validIndex() throws TalkingCatException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand, "Parser should return DeleteCommand for 'delete' input with a valid index.");
    }

    @Test
    public void parse_deleteCommand_invalidIndex() {
        assertThrows(TalkingCatException.class, () -> {
            Parser.parse("delete one");
        }, "Parser should throw TalkingCatException for 'delete' input with an invalid index.");
    }

    @Test
    public void parse_findCommand_withKeyword() throws TalkingCatException {
        Command command = Parser.parse("find book");
        assertTrue(command instanceof FindCommand, "Parser should return FindCommand for 'find' input with a keyword.");
    }

    @Test
    public void parse_invalidCommand() {
        assertThrows(TalkingCatException.class, () -> {
            Parser.parse("invalid command");
        }, "Parser should throw TalkingCatException for an unrecognized command.");
    }

}

