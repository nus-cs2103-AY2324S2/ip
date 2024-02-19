import Kokbot.Kokbot;
import Kokbot.Parser;
import Kokbot.Command;
import Kokbot.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseDeadline_normalInput_success() {
        Parser parser = new Parser();
        try {
            assertEquals(new Command(Kokbot.CommandType.DEADLINE, new String[]{"read book", "2021-08-24 18:00"}),
                    parser.parseDeadline("deadline read book /by 2021-08-24 18:00"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseDeadline_noBy_failure() {
        Parser parser = new Parser();
        try {
            parser.parseDeadline("deadline read book");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown usage - /by not found in \"deadline\" command.", e.getMessage());
        }
    }

    @Test
    public void parseDeadline_noDescription_failure() {
        Parser parser = new Parser();
        try {
            parser.parseDeadline("deadline /by 2021-08-24 18:00");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown usage - description of \"deadline\" should not be empty.", e.getMessage());
        }
    }

    @Test
    public void parseEvent_normalInput_success() {
        Parser parser = new Parser();
        try {
            assertEquals(new Command(Kokbot.CommandType.EVENT, new String[]{"read book", "2021-08-24 18:00", "2021-08-24 20:00"}),
                    parser.parseEvent("event read book /from 2021-08-24 18:00 /to 2021-08-24 20:00"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseEvent_noFrom_failure() {
        Parser parser = new Parser();
        try {
            parser.parseEvent("event read book /to 2021-08-24 20:00");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown usage - /from not found in \"event\" command.", e.getMessage());
        }
    }

    @Test
    public void parseEvent_noTo_failure() {
        Parser parser = new Parser();
        try {
            parser.parseEvent("event read book /from 2021-08-24 18:00");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown usage - /to not found in \"event\" command.", e.getMessage());
        }
    }

}
