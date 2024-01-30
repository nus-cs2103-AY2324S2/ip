package cappy.parser;

import static cappy.parser.Parser.DATE_TIME_FORMAT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cappy.command.CommandType;
import cappy.error.CappyException;
import cappy.task.Deadline;
import cappy.task.Event;
import cappy.task.Task;
import cappy.task.Todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ParserTest {
    private static final LocalDateTime DUE =
            LocalDateTime.parse("2023-01-29T12:00", DATE_TIME_FORMAT);
    private static final LocalDateTime FROM =
            LocalDateTime.parse("2023-01-29T12:00", DATE_TIME_FORMAT);
    private static final LocalDateTime TO =
            LocalDateTime.parse("2023-01-30T14:00", DATE_TIME_FORMAT);

    @Test
    public void parse_emptyString() throws CappyException {
        HashMap<String, String> namedArgs = new HashMap<>();
        ArrayList<String> positionalArgs = new ArrayList<>();
        ParsedInput expectedParsedInput =
                new ParsedInput(CommandType.EMPTY, namedArgs, positionalArgs);
        assertEquals(expectedParsedInput, Parser.parse(""));
    }

    @Test
    public void parse_invalidCommand() throws CappyException {
        HashMap<String, String> namedArgs = new HashMap<>();
        ArrayList<String> positionalArgs = new ArrayList<>();
        ParsedInput expectedParsedInput =
                new ParsedInput(CommandType.INVALID, namedArgs, positionalArgs);
        assertEquals(expectedParsedInput, Parser.parse("nonExistentCommand"));
    }

    @Test
    public void parse_missingValueForOption() {
        HashMap<String, String> namedArgs = new HashMap<>();
        ArrayList<String> positionalArgs = new ArrayList<>();
        ParsedInput expectedParsedInput =
                new ParsedInput(CommandType.DEADLINE, namedArgs, positionalArgs);
        try {
            assertEquals(expectedParsedInput, Parser.parse("deadline description /by"));
            fail();
        } catch (Exception e) {
            assertEquals("Missing value for argument /by", e.getMessage());
        }
        try {
            assertEquals(expectedParsedInput, Parser.parse("deadline description /by "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing value for argument /by", e.getMessage());
        }
    }

    @Test
    public void parse_valueForOption() throws CappyException {
        HashMap<String, String> namedArgs = new HashMap<>();
        namedArgs.put("by", "2023-01-29T12:00");
        ArrayList<String> positionalArgs = new ArrayList<>();
        positionalArgs.add("description");
        ParsedInput expectedParsedInput =
                new ParsedInput(CommandType.DEADLINE, namedArgs, positionalArgs);
        assertEquals(
                expectedParsedInput, Parser.parse("deadline description /by 2023-01-29T12:00"));
    }

    @Test
    public void parse_positionalArg() throws CappyException {
        HashMap<String, String> namedArgs = new HashMap<>();
        ArrayList<String> positionalArgs = new ArrayList<>();
        positionalArgs.add("description");
        ParsedInput expectedParsedInput =
                new ParsedInput(CommandType.TODO, namedArgs, positionalArgs);
        assertEquals(expectedParsedInput, Parser.parse("todo description"));
    }

    @Test
    public void parseCsvLine_todo() throws CappyException {
        String csvLine = "T,0,description,,";
        Todo todo = new Todo("description", false);
        assertEquals(todo, Parser.parseCsvLine(csvLine));
    }

    @Test
    public void parseCsvLine_deadline() throws CappyException {
        String csvLine = "D,0,description,2023-01-29T12:00,";
        Deadline deadline = new Deadline("description", false, DUE);
        assertEquals(deadline, Parser.parseCsvLine(csvLine));
    }

    @Test
    public void parseCsvLine_event() throws CappyException {
        String csvLine = "E,0,description,2023-01-29T12:00,2023-01-30T14:00";
        Event event = new Event("description", false, FROM, TO);
        assertEquals(event, Parser.parseCsvLine(csvLine));
    }

    @Test
    public void parseCsvLine_missingField() {
        String csvLine = "T,0";
        Task task = new Todo("description", false);
        try {
            assertEquals(task, Parser.parseCsvLine(csvLine));
            fail();
        } catch (Exception e) {
            assertEquals("Invalid storage format!", e.getMessage());
        }
        csvLine = "D,0,description,";
        task = new Deadline("description", false, DUE);
        try {
            assertEquals(task, Parser.parseCsvLine(csvLine));
            fail();
        } catch (Exception e) {
            assertEquals("Invalid storage format!", e.getMessage());
        }
    }

    @Test
    public void parseCsvLine_invalidDateFormat() {
        String csvLine = "D,0,description,invalid date,";
        Deadline task = new Deadline("description", false, DUE);
        try {
            assertEquals(task, Parser.parseCsvLine(csvLine));
            fail();
        } catch (Exception e) {
            assertEquals("Invalid storage format!", e.getMessage());
        }
    }
}
