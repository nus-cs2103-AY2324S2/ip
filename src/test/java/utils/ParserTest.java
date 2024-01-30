package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.Add;
import commands.Bye;
import commands.Command;
import commands.Delete;
import commands.List;
import commands.Mark;
import commands.Unmark;
import exceptions.ConvoBotException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

class ParserTest {
    @Test
    void parseUserInputByeCommand_validInput_commandParsedSuccessfully() throws ConvoBotException {
        String userInput = "bye";
        Command command = Parser.parseUserInput(userInput);
        assertTrue(command instanceof Bye);
    }

    @Test
    void parseUserInputListCommand_validInput_commandParsedSuccessfully() throws ConvoBotException {
        String userInput = "list";
        Command command = Parser.parseUserInput(userInput);
        assertTrue(command instanceof List);
    }

    @Test
    void parseUserInputMarkCommand_validInput_commandParsedSuccessfully() throws ConvoBotException {
        String userInput = "mark 1";
        Command command = Parser.parseUserInput(userInput);
        assertTrue(command instanceof Mark);
    }

    @Test
    void parseUserInputUnmarkCommand_validInput_commandParsedSuccessfully() throws ConvoBotException {
        String userInput = "unmark 1";
        Command command = Parser.parseUserInput(userInput);
        assertTrue(command instanceof Unmark);
    }

    @Test
    void parseUserInputDeleteCommand_validInput_commandParsedSuccessfully() throws ConvoBotException {
        String userInput = "delete 1";
        Command command = Parser.parseUserInput(userInput);
        assertTrue(command instanceof Delete);
    }

    @Test
    void parseUserInputTodoCommand_validInput_commandParsedSuccessfully() throws ConvoBotException {
        String userInput = "todo Read a book";
        Command command = Parser.parseUserInput(userInput);
        assertTrue(command instanceof Add);
    }

    @Test
    void parseUserInputDeadlineCommand_validInput_commandParsedSuccessfully() throws ConvoBotException {
        String userInput = "deadline Finish project /by 2024-02-01";
        Command command = Parser.parseUserInput(userInput);
        assertTrue(command instanceof Add);
    }

    @Test
    void parseUserInputEventCommand_validInput_commandParsedSuccessfully() throws ConvoBotException {
        String userInput = "event Team meeting /from 2024-02-01 /to 2024-02-02";
        Command command = Parser.parseUserInput(userInput);
        assertTrue(command instanceof Add);
    }

    @Test
    void parseUserInputInvalidCommand_invalidCommand_exceptionThrown() {
        String userInput = "invalidCommand";
        assertThrows(ConvoBotException.class, () -> Parser.parseUserInput(userInput));
    }

    @Test
    void parseUserInputInvalidArguments_invalidArguments_exceptionThrown() {
        String userInput = "mark";
        assertThrows(ConvoBotException.class, () -> Parser.parseUserInput(userInput));
    }

    @Test
    void parseUserInputEmptyInput_emptyInput_exceptionThrown() {
        String userInput = "";
        assertThrows(ConvoBotException.class, () -> Parser.parseUserInput(userInput));
    }

    @Test
    void parseTaskFromLineTodo_validInput_taskParsedSuccessfully() throws IllegalArgumentException {
        String line = "T | 0 | Read a book";
        Task task = Parser.parseTaskFromLine(line);
        assertTrue(task instanceof ToDo);
        assertFalse(task.getIsDone());
        assertEquals("Read a book", task.getDescription());
    }

    @Test
    void parseTaskFromLineDeadline_validInput_taskParsedSuccessfully() throws IllegalArgumentException {
        String line = "D | 1 | Finish project | 2024-02-01";
        Task task = Parser.parseTaskFromLine(line);
        assertTrue(task instanceof Deadline);
        assertTrue(task.getIsDone());
        assertEquals("Finish project", task.getDescription());
    }

    @Test
    void parseTaskFromLineEvent_validInput_taskParsedSuccessfully() throws IllegalArgumentException {
        String line = "E | 0 | Team meeting | 2024-02-01 | 2024-02-02";
        Task task = Parser.parseTaskFromLine(line);
        assertTrue(task instanceof Event);
        assertFalse(task.getIsDone());
        assertEquals("Team meeting", task.getDescription());
    }

    @Test
    void parseTaskFromLineInvalidFormat_invalidFormat_exceptionThrown() {
        String line = "Invalid Format";
        assertThrows(IllegalArgumentException.class, () -> Parser.parseTaskFromLine(line));
    }
}
