package task;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void testToString() {
        LocalDate startDate = LocalDate.of( 2022 , 2 , 2);
        LocalTime startTime = LocalTime.of(10, 00);
        LocalDate endDate = LocalDate.of( 2022 , 2 , 10);
        LocalTime endTime = LocalTime.of(10, 00);

        Event EventWithCorrectInput = new Event("test deadline description",
                startDate, startTime, endDate, endTime);
        String tString1 = EventWithCorrectInput.toString();
        assertEquals("[E][ ] test deadline description (from: 2 Feb 2022 10:00 | to: 10 Feb 2022 10:00)", tString1);
    }
}
