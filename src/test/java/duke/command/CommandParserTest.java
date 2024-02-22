package duke.command;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.commons.exceptions.DukeException;

public class CommandParserTest {

    @Test
    public void parseCommand_ValidCommand_ReturnsCommandType() throws DukeException {
        String userInput = "todo eat cake";
        String msg = "Parsing valid command should return correct CommandType.";
        assertEquals(CommandType.TODO, CommandParser.parseCommand(userInput), msg);
    }

    @Test
    public void parseCommand_InvalidCommand_ThrowsDukeException() {
        String userInput = "invalid command here";
        String msg = "Parsing invalid command should throw DukeException.";
        assertThrows(DukeException.class, () -> CommandParser.parseCommand(userInput), msg);
    }

    @Test
    public void parseTaskIndex_ValidIndex_ReturnsIndex() throws DukeException {
        String userInput = "delete 1";
        String msg = "Parsing valid task index should return correct index.";
        assertEquals(0, CommandParser.parseTaskIndex(userInput), msg);
    }

    @Test
    public void parseTaskIndex_NoIndex_ThrowsDukeException() {
        String userInput = "delete";
        String msg = "Parsing no index should throw Duke Exception";
        assertThrows(DukeException.class, () -> CommandParser.parseTaskIndex(userInput), msg);
    }

    @Test
    public void parseTaskIndex_InvalidIndex_ThrowsDukeException() {
        String userInput = "delete -109120931";
        String msg = "Parsing invalid index should throw Duke Exception";
        assertThrows(DukeException.class, () -> CommandParser.parseTaskIndex(userInput), msg);
    }

    @Test
    public void parseToDo_ValidInput_ReturnsDescription() throws DukeException {
        String userInput = "todo eat cake";
        String msg = "Parsing valid todo should return correct description.";
        assertEquals("eat cake", CommandParser.parseToDo(userInput), msg);
    }

    @Test
    public void parseToDo_NoDescription_ThrowsDukeException() {
        String userInput = "todo";
        String msg = "No description should throw DukeException.";
        assertThrows(DukeException.class, () -> CommandParser.parseToDo(userInput), msg);
    }

    @Test
    public void parseDeadline_ValidInput_ReturnsDetails() throws DukeException {
        String userInput = "deadline submit report /by 13-07-2025";
        String[] expected = new String[]{"submit report", "13-07-2025"};
        String msg = "Parsing valid deadline should return correct details.";
        assertArrayEquals(expected, CommandParser.parseDeadline(userInput), msg);
    }

    @Test
    public void parseDeadline_InvalidFormat_ThrowsDukeException() {
        String userInput = "deadline submit report 13-07-2025";
        String msg = "Invalid format should throw DukeException.";
        assertThrows(DukeException.class, () -> CommandParser.parseDeadline(userInput), msg);
    }

    @Test
    public void parseEvent_ValidInput_ReturnDetails() throws DukeException {
        String userInput = "event eat cake /from 01-12-2024 /to 2-12-2025";
        String[] expected = new String[]{"eat cake", "01-12-2024", "2-12-2025"};
        String msg = "Parsing valid event should return correct details.";
        assertArrayEquals(expected, CommandParser.parseEvent(userInput), msg);
    }

    @Test
    public void parseEvent_InvalidInput_ThrowsDukeException() {
        String userInput = "event eat cake 01-12-2024 2-12-2025";
        String msg = "Invalid format should throw DukeException.";
        assertThrows(DukeException.class, () -> CommandParser.parseEvent(userInput), msg);
    }
}