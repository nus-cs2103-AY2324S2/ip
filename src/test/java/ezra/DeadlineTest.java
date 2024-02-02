package ezra;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline1 = new Deadline("Return book", "29/01/2024 1800");
        String expectedString1 = "[D][ ] Return book (by: 29 Jan 2024 06:00 pm)";
        assertEquals(expectedString1, deadline1.toString());

        Deadline deadline2 = new Deadline("Finish assignment", "01/02/2024 0000");
        String expectedString2 = "[D][ ] Finish assignment (by: 01 Feb 2024 12:00 am)";
        assertEquals(expectedString2, deadline2.toString());
    }

    @Test
    public void testToStorageString() {
        Deadline deadline1 = new Deadline("Return book", "29/01/2024 1800");
        String expectedString1 = "D | 0 | Return book | 29/01/2024 1800";
        assertEquals(expectedString1, deadline1.toStorageString());

        Deadline deadline2 = new Deadline("Finish assignment", "01/02/2024 0000");
        String expectedString2 = "D | 0 | Finish assignment | 01/02/2024 0000";
        assertEquals(expectedString2, deadline2.toStorageString());
    }
}
