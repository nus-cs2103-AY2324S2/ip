import duke.TimeBlock;
import duke.BotException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for TimeBlock
 */
class TimeBlockTest {

    /**
     * Test case for valid input to the TimeBlock constructor
     */
    @Test
    void testValidInputConstructor() {
        assertDoesNotThrow(() -> {
            new TimeBlock("description", "1/1/2022 1200", "1/1/2022 1300");
        });
    }

    /**
     * Test case for invalid from time input to the TimeBlock constructor
     */
    @Test
    void testInvalidFromTime() {
        assertThrows(BotException.class, () -> {
            new TimeBlock("description", "invalid time", "1/1/2022 1300");
        });
    }

    /**
     * Test case for invalid to time input to the TimeBlock constructor
     */
    @Test
    void testInvalidToTime() {
        assertThrows(BotException.class, () -> {
            new TimeBlock("description", "1/1/2022 1200", "invalid time");
        });
    }

    /**
     * Test case for the toString method of TimeBlock
     */
    @Test
    void testToString() throws BotException {
        TimeBlock timeBlock = new TimeBlock("description", "1/1/2022 1200", "1/1/2022 1300");
        String expected = "E |   | description | from: 1/1/2022 1200 to: 1/1/2022 1300";
        System.out.println(timeBlock.toString());
        boolean isSame = timeBlock.toString().equals(expected);
        assertTrue(isSame);
    }
}