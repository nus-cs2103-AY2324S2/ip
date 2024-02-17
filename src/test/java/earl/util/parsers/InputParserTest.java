package earl.util.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import earl.exceptions.ParserException;
import earl.logic.Handler;
import earl.logic.TodoHandler;

class InputParserTest {

    @Test
    void parse_normalInput_handlerReturnedSuccess() throws Exception {
        Handler handler = InputParser.parse("todo ip");
        assertNotNull(handler);
        assertInstanceOf(TodoHandler.class, handler);
    }

    @Test
    void parse_invalidInput_exceptionThrown() {
        try {
            InputParser.parse("no ip");
            fail();
        } catch (ParserException e) {
            assertEquals("no ip", e.getMessage());
        }
    }
}
