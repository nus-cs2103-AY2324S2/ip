package shirmin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void validDateTest(){
        Event testEvent = new Event("test", "2019-10-15 1800","2019-10-15 2300" );
        assertEquals(true, testEvent.hasValidDates());
    }
    @Test
    public void stringRepresentationTest(){
        Event testDeadline = new Event("test", "2019-10-15 1800", "2019-10-15 2300" );
        assertEquals("[E][ ] test (from: Oct 15 2019 18:00 to: Oct 15 2019 23:00)", testDeadline.toString());
    }
}
