package duke;

import duke.exceptions.InvalidCmdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    void parse_unknownCommand() {
        try {
            Parser.parse("what is this command");
            fail();
        } catch (InvalidCmdException ce) {
            assertEquals("Unknown command, please try again.", ce.getMessage());
        }
    }

    @Test
    void parse_command_with_differentParameters() {
        try {
            Parser.parse("event project meeting /by Aug 6th /to Aug 7th");
            fail();
        } catch (InvalidCmdException ce) {
            assertEquals("Missing parameter: /from", ce.getMessage());
        }
    }
}
