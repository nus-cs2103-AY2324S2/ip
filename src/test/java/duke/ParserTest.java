package duke;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.duke.command.ByeCommand;
import seedu.duke.command.CheckCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EventCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.MarkCommand;
import seedu.duke.command.TodoCommand;
import seedu.duke.command.UnmarkCommand;
import seedu.duke.exception.InvalidCommandFormatException;
import seedu.duke.exception.UnknownCommandException;
import seedu.duke.parser.Parser;




public class ParserTest {

    @Test
    public void testUnknownCommand() {
        assertThrows(UnknownCommandException.class, () -> Parser.parse("blah"));
    }

    @Test
    public void testList() throws UnknownCommandException, InvalidCommandFormatException {
        Command c = Parser.parse("list");
        assertTrue(c instanceof ListCommand);

        assertThrows(InvalidCommandFormatException.class, () -> Parser.parse("list aa"));
    }

    @Test
    public void testBye() throws UnknownCommandException, InvalidCommandFormatException {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ByeCommand);

        assertThrows(InvalidCommandFormatException.class, () -> Parser.parse("bye aa"));
    }

    @Test
    public void testCheck() throws UnknownCommandException, InvalidCommandFormatException {
        Command c = Parser.parse("check 2022-01-01");
        assertTrue(c instanceof CheckCommand);

        assertThrows(InvalidCommandFormatException.class, () -> Parser.parse("check 2222"));
        assertThrows(InvalidCommandFormatException.class, () -> Parser.parse("check"));
    }

    @Test
    public void testDelete() throws UnknownCommandException, InvalidCommandFormatException {
        Command c = Parser.parse("delete 1");
        assertTrue(c instanceof DeleteCommand);

        assertThrows(InvalidCommandFormatException.class, () -> Parser.parse("delete"));
        assertThrows(InvalidCommandFormatException.class, () -> Parser.parse("delete a"));
    }

    @Test
    public void testEvent() throws UnknownCommandException, InvalidCommandFormatException {
        Command c = Parser.parse("event hello /from 2022-10-10 18:00 /to 2022-10-11 18:00");


        assertTrue(c instanceof EventCommand);


        assertThrows(InvalidCommandFormatException.class, (
                ) -> Parser.parse("event /from 2022-10-10 18:00 /to 2022-10-11 18:00"));


        assertThrows(InvalidCommandFormatException.class, (
                ) -> Parser.parse("event asd /from 2022-10-10 18:00 "));

        assertThrows(InvalidCommandFormatException.class, (
                ) -> Parser.parse("event  /to 2022-10-11 18:00"));

        assertThrows(InvalidCommandFormatException.class, (
                ) -> Parser.parse("event  /from a /to b"));
    }

    @Test
    public void testMark() throws UnknownCommandException, InvalidCommandFormatException {
        Command c = Parser.parse("mark 1");

        assertTrue(c instanceof MarkCommand);

        assertThrows(InvalidCommandFormatException.class, () -> Parser.parse("mark"));

        assertThrows(InvalidCommandFormatException.class, () -> Parser.parse("mark a"));
    }

    @Test
    public void testTodo() throws UnknownCommandException, InvalidCommandFormatException {
        Command c = Parser.parse("todo a");

        assertTrue(c instanceof TodoCommand);

        assertThrows(InvalidCommandFormatException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void testUnmark() throws UnknownCommandException, InvalidCommandFormatException {
        Command c = Parser.parse("unmark 1");

        assertTrue(c instanceof UnmarkCommand);

        assertThrows(InvalidCommandFormatException.class, () -> Parser.parse("unmark"));

        assertThrows(InvalidCommandFormatException.class, () -> Parser.parse("unmark a"));
    }

}
