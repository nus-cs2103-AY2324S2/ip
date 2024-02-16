package earl.util.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import earl.exceptions.EarlException;
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
            InputParser.parse("TODO ip");
            fail();
        } catch (EarlException e) {
            assertEquals("Input defies parsing: TODO ip", e.getMessage());
        }
    }
}
