package dukio;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void unknownCommand_errorThrown() {
        try {
            Parser.parse("blah", null);
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), DukioException.class);
        }
    }
    @Test
    public void deleteCommand_outOfBoundsErrorThrown() {
        try {
            Parser.parse("delete -1", new State());
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), DukioException.class);
        }
        try {
            Parser.parse("delete 1", new State());
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), DukioException.class);
        }
    }

    @Test
    public void todoCommand_noDescriptionErrorThrown() {
        try {
            Parser.parse("todo", new State());
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), DukioException.class);
        }
    }
    @Test
    public void deadlineCommand_noDeadlineErrorThrown() {
        try {
            Parser.parse("deadline hello world", new State());
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "No /by???");
        }
    }
    @Test
    public void eventCommand_noFromErrorThrown() {
        try {
            Parser.parse("event hello world", new State());
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "No /from???");
        }
    }
    @Test
    public void eventCommand_noFromDateErrorThrown() {
        try {
            Parser.parse("event hello world /from", new State());
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Add something after your /from...");
        }
    }

    @Test
    public void eventCommand_noByErrorThrown() {
        try {
            Parser.parse("event hello world /from 2022-01-01", new State());
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "No /to???");
        }
    }

    @Test
    public void eventCommand_noByDateErrorThrown() {
        try {
            Parser.parse("event hello world /from 2022-01-01 /to", new State());
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Add something after your /to...");
        }
    }

    @Test
    public void eventCommand_invalidFromDateErrorThrown() {
        try {
            Parser.parse("event hello world /from 2pm /to 2022-01-01", new State());
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), DateTimeParseException.class);
        }
    }

    @Test
    public void eventCommand_invalidToDateErrorThrown() {
        try {
            Parser.parse("event hello world /from 2022-01-01 /to 3pm", new State());
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), DateTimeParseException.class);
        }
    }
}
