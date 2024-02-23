package Duke;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testInRange() {
        Event event = new Event("Test", LocalDate.of(2022,1, 1),
                LocalDate.of(2022, 3,1));
        //assert true if any event day overlaps with date range
        assertTrue(event.inRange(LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1)));
        //assert false if all event day does not overlap with date range
        assertFalse(event.inRange(LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1)));
    }
}
