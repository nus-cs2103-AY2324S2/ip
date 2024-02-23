package oak.utility;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeUtilityTest {
    @Test
    public void parseValidUserInputStringToLocalDateTimeTest() {
        String validDateTimeString = "2019-12-12 @ 10:20";

        assertEquals(
                LocalDateTime.parse("2019-12-12T10:20"),
                DateTimeUtility.parseStringToLocalDateTime(validDateTimeString)
                );
    }

    @Test
    public void parseValidTaskListStringToLocalDateTimeTest() {
        String validDateTimeString = "2019-12-12T10:20";

        assertEquals(
                LocalDateTime.parse(validDateTimeString),
                DateTimeUtility.parseStringToLocalDateTime(validDateTimeString)
        );
    }

    @Test
    public void parseLocalDateTimeToStringTest() {
        LocalDateTime validDateTime = LocalDateTime.parse("2019-12-12T10:20");
        String expectedString = "12 12 2019 @ 1020";

        assertEquals(
                expectedString,
                DateTimeUtility.parseLocalDateTimeToString(validDateTime)
        );
    }

    // TODO: Invalid Date Time
}
