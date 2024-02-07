package chimp.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventToStringTest() {
        String expected = "[E] [ ] e (from: Feb 1 2015 to: Sep 9 2019 )";
        String result = new Event("e", TaskStatus.UNMARKED, LocalDate.of(2015, 2, 1), LocalDate.of(2019, 9, 9)).toString();
        assertEquals(expected, result);
    }
}
