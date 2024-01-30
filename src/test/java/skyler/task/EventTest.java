package skyler.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import skyler.exception.SkylerException;

public class EventTest {

    @Test
    public void testEventToString() throws SkylerException {
        LocalDate from = LocalDate.of(2022, 2, 10);
        LocalDate to = LocalDate.of(2022, 2, 15);
        Event event = new Event("Test Event", from, to, false);

        String expectedString = "[E][ ] Test Event (from: Feb 10 2022 to: Feb 15 2022)";
        assertEquals(expectedString, event.toString());
    }

}