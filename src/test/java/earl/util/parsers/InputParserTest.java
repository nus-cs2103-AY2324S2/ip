package earl.util.parsers;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import earl.logic.Handler;
import earl.logic.HelpHandler;
import earl.logic.TodoHandler;

class InputParserTest {

    @Test
    void parse_normalInput_handlerReturnedSuccess() throws Exception {
        Handler handler = InputParser.parse("todo ip");
        assertNotNull(handler);
        assertInstanceOf(TodoHandler.class, handler);
    }

    @Test
    void parse_invalidInput_returnHelpHandler() {
        Handler handler = InputParser.parse("no ip");
        assertInstanceOf(HelpHandler.class, handler);
    }
}
