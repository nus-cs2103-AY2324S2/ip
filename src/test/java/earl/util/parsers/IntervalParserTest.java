package earl.util.parsers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import earl.exceptions.EarlException;

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
        } catch (EarlException e) {
            String expected = "The indices' format is fraught with invalidity."
                    + " Example format: 1 4-7 9-10";
            assertEquals(expected, e.getMessage());
        }
    }
}
