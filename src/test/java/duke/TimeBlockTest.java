import duke.TimeBlock;
import duke.BotException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TimeBlockTest {

    @Test
    void testValidInputConstructor() {
        assertDoesNotThrow(() -> {
            new TimeBlock("description", "1/1/2022 1200", "1/1/2022 1300");
        });
    }

    @Test
    void testInvalidFromTime() {
        assertThrows(BotException.class, () -> {
            new TimeBlock("description", "invalid time", "1/1/2022 1300");
        });
    }

    @Test
    void testInvalidToTime() {
        assertThrows(BotException.class, () -> {
            new TimeBlock("description", "1/1/2022 1200", "invalid time");
        });
    }

    @Test
    void testToString() throws BotException {
        TimeBlock timeBlock = new TimeBlock("description", "1/1/2022 1200", "1/1/2022 1300");
        String expected = "E |   | description | from: 1/1/2022 1200 to: 1/1/2022 1300";
        System.out.println(timeBlock.toString());
        boolean isSame = timeBlock.toString().equals(expected);
        assertTrue(isSame);
    }
}