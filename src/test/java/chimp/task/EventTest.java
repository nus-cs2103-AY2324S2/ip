package chimp.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventToStringTest() {
        String expected = "[E] [ ] e (from: Feb 1 2015 to: Sep 9 2019)";
        String result = new Event("e", TaskStatus.UNMARKED, LocalDate.of(2015, 2, 1), LocalDate.of(2019, 9, 9)).toString();
        assertEquals(expected, result);
    }

    @Test
    public void eventMarkedToStringTest() {
        String expected = "[E] [X] Company Retreat (from: Mar 15 2022 to: Mar 20 2022)";
        String result = new Event("Company Retreat", TaskStatus.MARKED, LocalDate.of(2022, 3, 15), LocalDate.of(2022, 3, 20)).toString();
        assertEquals(expected, result);
    }

    @Test
    public void eventWithLongDescriptionToStringTest() {
        String expected = "[E] [ ] Planning session for project X (from: Jan 1 2023 to: Jan 2 2023)";
        String result = new Event("Planning session for project X", TaskStatus.UNMARKED, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2)).toString();
        assertEquals(expected, result);
    }
}
