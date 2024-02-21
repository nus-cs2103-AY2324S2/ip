package task; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testToString() {
        LocalDate startDate = LocalDate.of(2022 , 2 , 2);
        LocalTime startTime = LocalTime.of(10, 00);
        LocalDate endDate = LocalDate.of(2022 , 2 , 10);
        LocalTime endTime = LocalTime.of(10, 00);

        Event eventWithCorrectInput = new Event("test event description",
                startDate, startTime, endDate, endTime);
        String tString1 = eventWithCorrectInput.toString();
        assertEquals("[E][ ] test event description (from: 02 Feb 2022 10:00 | to: 10 Feb 2022 10:00)", tString1);
    }
}
