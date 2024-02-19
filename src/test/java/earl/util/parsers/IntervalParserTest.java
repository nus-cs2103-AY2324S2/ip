package earl.util.parsers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import earl.exceptions.ParserException;

class IntervalParserTest {

    @Test
    void parse_normalInput_indicesReturnedSuccess() throws Exception {
        Integer[] indices = IntervalParser.parse("1 3-5")
                .toArray(Integer[]::new);
        assertArrayEquals(new Integer[]{4, 3, 2, 0}, indices);
    }

    @Test
    void parse_invalidInput_exceptionThrown() {
        try {
            IntervalParser.parse("3 to 5");
            fail();
        } catch (Exception e) {
            assertInstanceOf(ParserException.class, e);
        }
    }
}
