import dino.tasks.Event;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString_singleDayEvent() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 2, 7, 10, 0); // Example start date and time
        LocalDateTime endDateTime = LocalDateTime.of(2024, 2, 7, 12, 0); // Example end date and time
        Event event = new Event("Meeting", startDateTime, endDateTime);

        String expected = "[E][ ] Meeting(from: 10:00 Feb 07 2024 to: 12:00 Feb 07 2024)";
        String actual = event.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testToString_multiDayEvent() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 2, 7, 10, 0); // Example start date and time
        LocalDateTime endDateTime = LocalDateTime.of(2024, 2, 9, 12, 0); // Example end date and time
        Event event = new Event("Conference", startDateTime, endDateTime);

        String expected = "[E][ ] Conference(from: 10:00 Feb 07 2024 to: 12:00 Feb 09 2024)";
        String actual = event.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testToString_sameStartAndEndDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 2, 7, 10, 0); // Example date and time
        Event event = new Event("Workshop", dateTime, dateTime);

        String expected = "[E][ ] Workshop(from: 10:00 Feb 07 2024 to: 10:00 Feb 07 2024)";
        String actual = event.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testToString_descriptionWithSpecialCharacters() {
        LocalDateTime startDateTime = LocalDateTime.of(2024, 2, 7, 10, 0); // Example start date and time
        LocalDateTime endDateTime = LocalDateTime.of(2024, 2, 7, 12, 0); // Example end date and time
        Event event = new Event("Team's Meeting @Office", startDateTime, endDateTime);

        String expected = "[E][ ] Team's Meeting @Office(from: 10:00 Feb 07 2024 to: 12:00 Feb 07 2024)";
        String actual = event.toString();

        assertEquals(expected, actual);
    }
}
