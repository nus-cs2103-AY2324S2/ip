package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_addTodoCommand() throws DukeException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddTodoCommand, "Parser should return AddTodoCommand for 'todo' input.");
    }

    @Test
    public void parse_invalidCommand() {
        assertThrows(DukeException.class, () -> {
            Parser.parse("invalid command");
        });
    }

    // ... add more test methods if necessary ...
}

