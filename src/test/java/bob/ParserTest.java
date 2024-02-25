package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bob.exception.BobException;

public class ParserTest {
    @Test
    public void parse_listWithDescription_exceptionThrown() {
        try {
            Parser.parse("list a");
            fail();
        } catch (BobException e) {
            assertEquals("tell me what's your on or due_in", e.getMessage());
        }
    }

    @Test
    public void parse_listOnInvalidDate_exceptionThrown() {
        try {
            Parser.parse("list /on a");
            fail();
        } catch (BobException e) {
            assertEquals("i only understand d/M/yyyy, but you gave me a", e.getMessage());
        }
    }

    @Test
    public void parse_listOnInvalidDays_exceptionThrown() {
        try {
            Parser.parse("list /due_in a");
            fail();
        } catch (BobException e) {
            assertEquals("how many days is a days???", e.getMessage());
        }
    }

    @Test
    public void parse_markInvalidTaskIndex_exceptionThrown() {
        try {
            Parser.parse("mark a");
            fail();
        } catch (BobException e) {
            assertEquals("a? that's not even a number", e.getMessage());
        }
    }

    @Test
    public void parse_deleteInvalidTaskIndex_exceptionThrown() {
        try {
            Parser.parse("delete a");
            fail();
        } catch (BobException e) {
            assertEquals("a? that's not even a number", e.getMessage());
        }
    }

    @Test
    public void parse_markEmptyDescription_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (BobException e) {
            assertEquals("mark what", e.getMessage());
        }
    }

    @Test
    public void parse_deleteEmptyDescription_exceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (BobException e) {
            assertEquals("delete what", e.getMessage());
        }
    }

    @Test
    public void parse_addDeadlineInvalidDateTime_exceptionThrown() {
        try {
            Parser.parse("deadline a /by a");
            fail();
        } catch (BobException e) {
            assertEquals("i only understand d/M/yyyy HHmm, but you gave me a", e.getMessage());
        }
    }

    @Test
    public void parse_addEventInvalidDateTime_exceptionThrown() {
        try {
            Parser.parse("event a /from a /to a");
            fail();
        } catch (BobException e) {
            assertEquals("i only understand d/M/yyyy HHmm, but you gave me a", e.getMessage());
        }
    }

    @Test
    public void parse_addTodoEmptyDescription_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (BobException e) {
            assertEquals("todo what", e.getMessage());
        }
    }

    @Test
    public void parse_addDeadlineEmptyDescription_exceptionThrown() {
        try {
            Parser.parse("deadline");
            fail();
        } catch (BobException e) {
            assertEquals("deadline what", e.getMessage());
        }
    }
    @Test
    public void parse_addEventEmptyDescription_exceptionThrown() {
        try {
            Parser.parse("event");
            fail();
        } catch (BobException e) {
            assertEquals("event what", e.getMessage());
        }
    }

    @Test
    public void parse_addDeadlineMissingBy_exceptionThrown() {
        try {
            Parser.parse("deadline a");
            fail();
        } catch (BobException e) {
            assertEquals("tell me what's your by", e.getMessage());
        }
    }

    @Test
    public void parse_addEventMissingFrom_exceptionThrown() {
        try {
            Parser.parse("event a /to 12/2/2024 2158");
            fail();
        } catch (BobException e) {
            assertEquals("tell me what's your from", e.getMessage());
        }
    }

    @Test
    public void parse_addEventMissingTo_exceptionThrown() {
        try {
            Parser.parse("event a /from 12/2/2024 2158");
            fail();
        } catch (BobException e) {
            assertEquals("tell me what's your to", e.getMessage());
        }
    }

    @Test
    public void parse_findEmptyDescription_exceptionThrown() {
        try {
            Parser.parse("find");
            fail();
        } catch (BobException e) {
            assertEquals("find what", e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Parser.parse("");
            fail();
        } catch (BobException e) {
            assertEquals("what", e.getMessage());
        }
    }
}
