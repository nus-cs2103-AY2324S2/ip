package missminutes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    public void testFromStr_ValidInput_ReturnsDeadline() throws MissMinutesException {
        String input = "Finish assignment /by 2024-01-31 1800";
        Deadline deadline = Deadline.fromStr(input);

        assertEquals("Finish assignment", deadline.description);
        assertEquals(LocalDateTime.of(2024, 1, 31, 18, 0), deadline.by);
    }

    @Test
    public void testFromStr_InvalidDateTimeFormat_ThrowsMissMinutesException() {
        String input1 = "Finish assignment /by 2024-01-31 18:00"; // Invalid time
        assertThrows(MissMinutesException.class, () -> Deadline.fromStr(input1));

        String input2 = "Finish assignment /by 2024-01-31"; // No time
        assertThrows(MissMinutesException.class, () -> Deadline.fromStr(input2));

        String input3 = "Finish assignment /by 31-01-2024 1600"; // Invalid dd-mm-yyyy format
        assertThrows(MissMinutesException.class, () -> Deadline.fromStr(input3));
    }

    @Test
    public void testFromStr_InvalidInputFormat_ThrowsMissMinutesException() {
        String input1 = "assignment by 2024-01-31 1800"; // missing /by
        assertThrows(MissMinutesException.class, () -> Deadline.fromStr(input1));

        String input2 = "assignment/by2024-01-31 1800"; // missing space
        assertThrows(MissMinutesException.class, () -> Deadline.fromStr(input2));
    }

    @Test
    public void testToString_ReturnsFormattedString() {
        LocalDateTime by = LocalDateTime.of(2024, 1, 31, 18, 0);
        Deadline deadline = new Deadline("Finish assignment", by);

        String expectedString = "[D][ ] Finish assignment (by: Jan 31 2024 6PM)";
        assertEquals(expectedString, deadline.toString());
    }
}
