package linus.tasks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class DateTaskTest {
    
    @Test
    public void testDateTaskToString() {
        DateTask dateTask = new DateTask("21/01/2112 2112");
        assertEquals("Date: 21 Jan 2112 Time: 9:12 PM", dateTask.toString());
    }

}
