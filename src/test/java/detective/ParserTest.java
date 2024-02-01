package detective;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseCommand_ByeCommand() {
        String input = "message";
        try {
            assertEquals(new DukeException("OOPS!! Sorry, but I don't know what that means. qwq"), Parser.parseCommand(input));
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!! Sorry, but I don't know what that means. qwq", e.getMessage());
        }
    }
}
