package unim.task;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    @Test
    public void testToStringWithLocalDateRange() {
        LocalDate startDate = LocalDate.parse("2022-05-02");
        LocalDate endDate = LocalDate.parse("2022-05-05");
        Event event = new Event("team workshop", startDate, endDate);
        assertEquals("[E][ ] team workshop (from: May 2 2022 to: May 5 2022)", event.toString());
    }
}
