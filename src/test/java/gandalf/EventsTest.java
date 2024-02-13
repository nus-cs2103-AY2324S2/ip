package gandalf;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventsTest {

    private Events testTask;

    public EventsTest() {
        try {
            testTask = new Events("finish work", "2024-04-02 1800", "2024-04-03 2000");
        } catch (GandalfException e) {
            // for the sake of testing, exception will never be thrown, no action required
        }
    }

    @Test
    public void toStringTest() {
        assertEquals(testTask.toString(),
                "[E][ ] finish work (from: Tuesday, April 2, 2024 6:00PM to: Wednesday, April 3, 2024 8:00PM)");
    }
}
