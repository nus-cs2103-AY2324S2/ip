package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_deadline_emptyCommandExceptionThrown() {
        try {
            assertEquals(0, Parser.parse("deadline "));
            fail();
        } catch (DukeException e) {
            assertEquals("Empty command description", e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommandExceptionThrown() {
        try {
            assertEquals(0, Parser.parse("asujfhnau"));
            fail();
        } catch (DukeException e) {
            assertEquals("sry idk what that means", e.getMessage());
        }
    }
}
