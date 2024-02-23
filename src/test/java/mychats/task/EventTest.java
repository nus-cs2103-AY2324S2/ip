package mychats.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {

    @Test
    void eventTest() {
        Event output = new Event("go to the library", "2019-10-15 1800", "2019-10-17 2000");
        assertEquals("[E][ ] go to the library (from: Oct 15 2019 18:00 to: Oct 17 2019 20:00)", output.toString(), "toString() passed");
        assertEquals("E | 0 | go to the library | 2019-10-15 1800 | 2019-10-17 2000", output.toFileString(), "toFileString() passed");
        output.markTask();
        assertEquals("[E][X] go to the library (from: Oct 15 2019 18:00 to: Oct 17 2019 20:00)", output.toString(), "markTask() passed");
        assertEquals("E | 1 | go to the library | 2019-10-15 1800 | 2019-10-17 2000", output.toFileString(), "markTask() passed");
        output.unmarkTask();
        assertEquals("[E][ ] go to the library (from: Oct 15 2019 18:00 to: Oct 17 2019 20:00)", output.toString(), "unmarkTask() passed");
        assertEquals("E | 0 | go to the library | 2019-10-15 1800 | 2019-10-17 2000", output.toFileString(), "unmarkTask() passed");
    }
}
