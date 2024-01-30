package Duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class EventTest {
    @Test
    public void validDateTest(){
        Event testEvent = new Event("test", "2019-10-15 1800","2019-10-15 2300" );
        assertEquals(true, testEvent.hasValidDate());
    }

}
