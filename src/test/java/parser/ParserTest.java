package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import command.*;
import org.junit.jupiter.api.Test;

import exception.BuddyException;



public class ParserTest {
    @Test
    public void parse_normalExit_success() {
        try {
            boolean isCorrectCommand = Parser.parse("bye") instanceof ExitCommand;
            assertEquals(isCorrectCommand, true);
        } catch (BuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_normalList_success() {
        try {
            boolean isCorrectCommand = Parser.parse("list") instanceof ListCommand;
            assertEquals(isCorrectCommand, true);
        } catch (BuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_normalMark_success() {
        try {
            boolean isCorrectCommand = Parser.parse("mark 2") instanceof MarkCommand;
            assertEquals(isCorrectCommand, true);
        } catch (BuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_incorrectMarkIndex_exceptionThrown() {
        try {
            Parser.parse("mark two");
            fail();
        } catch (BuddyException e) {
            assertEquals("Not a valid task number buddy!", e.getMessage());
        }
    }

    @Test
    public void parse_emptyMarkIndex_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (BuddyException e) {
            assertEquals("I need a task number buddy!", e.getMessage());
        }
    }

    @Test
    public void parse_normalUnmark_success() {
        try {
            boolean isCorrectCommand = Parser.parse("unmark 10") instanceof UnmarkCommand;
            assertEquals(isCorrectCommand, true);
        } catch (BuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_incorrectUnmarkIndex_exceptionThrown() {
        try {
            Parser.parse("unmark haha");
            fail();
        } catch (BuddyException e) {
            assertEquals("Not a valid task number buddy!", e.getMessage());
        }
    }

    @Test
    public void parse_emptyUnmarkIndex_exceptionThrown() {
        try {
            Parser.parse("unmark");
            fail();
        } catch (BuddyException e) {
            assertEquals("I need a task number buddy!", e.getMessage());
        }
    }

    @Test
    public void parse_normalDelete_success() {
        try {
            boolean isCorrectCommand = Parser.parse("delete 3") instanceof DeleteCommand;
            assertEquals(isCorrectCommand, true);
        } catch (BuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_incorrectDeleteIndex_exceptionThrown() {
        try {
            Parser.parse("delete asdf");
            fail();
        } catch (BuddyException e) {
            assertEquals("Not a valid task number buddy!", e.getMessage());
        }
    }

    @Test
    public void parse_emptyDeleteIndex_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (BuddyException e) {
            assertEquals("I need a task number buddy!", e.getMessage());
        }
    }
    @Test
    public void parse_normalFind_success() {
        try {
            boolean isCorrectCommand = Parser.parse("find 2") instanceof FindCommand;
            assertEquals(isCorrectCommand, true);
        } catch (BuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_emptyFind_exceptionThrown() {
        try {
            Parser.parse("find");
            fail();
        } catch (BuddyException e) {
            assertEquals("What are you trying to find buddy?", e.getMessage());
        }
    }

    @Test
    public void parse_normalTodo_success() {
        try {
            boolean isCorrectCommand = Parser.parse("todo task") instanceof AddCommand;
            assertEquals(isCorrectCommand, true);
        } catch (BuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_emptyTodo_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (BuddyException e) {
            assertEquals(e.getMessage(), "Please include a task!");
        }
    }

    @Test
    public void parse_normalDeadline_success() {
        try {
            boolean isCorrectCommand = Parser.parse("deadline task /by 01/01/2001 0000") instanceof AddCommand;
            assertEquals(isCorrectCommand, true);
        } catch (BuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_emptyDeadlineTime_exceptionThrown() {
        try {
            Parser.parse("deadline task");
            fail();
        } catch (BuddyException e) {
            assertEquals(e.getMessage(), "Please include a deadline!");
        }
    }

    @Test
    public void parse_incorrectDeadlineFormat_exceptionThrown() {
        try {
            Parser.parse("deadline task /by 23-08-2001 1908");
            fail();
        } catch (BuddyException e) {
            assertEquals(e.getMessage(), "Not a valid date format!");
        }
    }

    @Test
    public void parse_normalEvent_success() {
        try {
            boolean isCorrectCommand = Parser.parse("event task /from 01/01/2001 0000 /to 01/01/2001 0200")
                    instanceof AddCommand;
            assertEquals(isCorrectCommand, true);
        } catch (BuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_emptyEventStartTime_exceptionThrown() {
        try {
            Parser.parse("event task");
            fail();
        } catch (BuddyException e) {
            assertEquals(e.getMessage(), "Please include start/end time!");
        }
    }

    @Test
    public void parse_emptyEventEndTime_exceptionThrown() {
        try {
            Parser.parse("event task /from 01/01/2001 0000");
            fail();
        } catch (BuddyException e) {
            assertEquals(e.getMessage(), "Please include end time!");
        }
    }

    @Test
    public void parse_incorrectEventFormat_exceptionThrown() {
        try {
            Parser.parse("event task /from 23/08/2001 1908 /to 23-08-2001 9008");
            fail();
        } catch (BuddyException e) {
            assertEquals(e.getMessage(), "Not a valid date format!");
        }
    }

    @Test
    public void parse_reverseEventFormat_exceptionThrown() {
        try {
            Parser.parse("event task /to 23/08/2001 1908 /from 24/08/2001 0000");
            fail();
        } catch (BuddyException e) {
            assertEquals(e.getMessage(), "Please include end time!");
        }
    }

    @Test
    public void parse_invalidInput_exceptionThrown() {
        try {
            Parser.parse("not a valid input");
            fail();
        } catch (BuddyException e) {
            assertEquals(e.getMessage(), "Not a valid command!");
        }
    }
}
