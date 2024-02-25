package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidCmdException;

public class ParserTest {
    @Test
    void parse_unknownCommand() {
        try {
            Parser.parse("what is this command");
            fail();
        } catch (InvalidCmdException ce) {
            assertEquals("_____________!!!!_____________\n"
                    + "Unknown command, please try again.", ce.getMessage());
        }
    }

    @Test
    void parse_commandWithDifferentParameters() {
        try {
            Parser.parse("event project meeting /by Aug 6th /to Aug 7th");
            fail();
        } catch (InvalidCmdException ce) {
            assertEquals("_____________!!!!_____________\n"
                    + "Missing parameter: /from", ce.getMessage());
        }
    }
}
