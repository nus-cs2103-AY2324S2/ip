package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventTest {
    @Test
    public void Event_toString() {
        assertEquals("[E][ ] open house (from: Jan-28-2024 to: Jan-30-2024)",
                new Event("open house", LocalDate.of(2024, 1, 28), LocalDate.of(2024, 1, 30)).toString());
    }

    @Test
    public void Event_toStorageFormat() {
        assertEquals("E |   | open house | 28-1-24 | 30-1-24",
                new Event("open house", LocalDate.of(2024, 1, 28), LocalDate.of(2024, 1, 30)).convertToStorageFormat());
    }
}
