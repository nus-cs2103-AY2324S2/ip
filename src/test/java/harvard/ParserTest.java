package harvard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parser_parseUnknownCommand_exceptionThrown() {
        Storage s = new Storage("");
        TaskList tL = new TaskList();
        Ui ui = new Ui();
        try {
            new Parser(s, tL, ui).parse("bob");
            fail();
        } catch (Exception e) {
            assertEquals("Bro... Idk what that is man.", e.getMessage());
        }
    }
}
