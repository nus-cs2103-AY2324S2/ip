package cat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParserTest {
    @Test
    public void parse_onlyCommandName_invalidCommandDataThrown() {
        Parser.InvalidCommandData thrown =
                Assertions.assertThrows(Parser.InvalidCommandData.class, () -> Parser.parse("event"));

        Assertions.assertEquals("Parameters to command not given.", thrown.getMessage());
    }

    @Test
    public void parse_noDescription_invalidCommandDataThrown() {
        Parser.InvalidCommandData thrown =
                Assertions.assertThrows(Parser.InvalidCommandData.class, ()
                        -> Parser.parse("deadline /by 12/12/2023 1200"));

        Assertions.assertEquals("No value given to description", thrown.getMessage());
    }
}
