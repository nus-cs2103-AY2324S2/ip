package dune;

import dune.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    // correct event format
    @Test
    public void testConvertLineToTask_correctEventFormat() {
        Storage storage = new Storage();
        String s = "E|0|project meeting|2021-08-25T14:00|2021-08-25T16:00";
        try {
            Task task = storage.convertLineToTask(s);
            assertEquals("[E][ ] project meeting (from: 25 Aug 2021 2:0 PM" +
                    " to: 25 Aug 2021 4:0 PM)", task.toString());
        } catch (DuneException e) {
            System.out.println(e.getMessage());
        }
    }

    // incorrect number of components
    @Test
    public void testConvertLineToTask_incorrectNumberOfComponents() {
        Storage storage = new Storage();
        String s = "E|0|project meeting|2021-08-25T14:00";
        try {
            Task task = storage.convertLineToTask(s);
        } catch (ArrayIndexOutOfBoundsException i) {
            assertEquals("Index 4 out of bounds for length 4", i.getMessage());
        } catch (DuneException d) {
            System.out.println(d.getMessage());
        }
    }

    // incorrect event type
    @Test
    public void testConvertLineToTask_incorrectEventType() {
        Storage storage = new Storage();
        String s = "F|0|project meeting|2021-08-25T14:00|2021-08-25T16:00";
        try {
            Task task = storage.convertLineToTask(s);
        } catch (DuneException d) {
            assertEquals("Invalid task type", d.getMessage());
        }
    }
}
