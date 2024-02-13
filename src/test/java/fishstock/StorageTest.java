package fishstock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void loadTask_validTask_success() throws Exception {
        Storage storage = new Storage("./test");

        storage.loadTask("T|borrow book|1");
        assertEquals("[T][X] borrow book", storage.list.get(0).toString());

        storage.loadTask("D|return book|27/1/2024 17:15|0");
        assertEquals("[D][ ] return book (by: Jan 27 2024, 5:15PM)", storage.list.get(1).toString());

        storage.loadTask("E|project meeting|1/1/1998 12:00|1/1/1998 22:00|1");
        assertEquals("[E][X] project meeting (from: Jan 01 1998, 12:00PM to: Jan 01 1998, 10:00PM)",
                storage.list.get(2).toString());
    }

    @Test
    public void loadTask_invalidTask_exceptionThrown() {
        Storage storage = new Storage("./test");

        try {
            // Missing mark
            storage.loadTask("T|borrow book|");
            fail();
        } catch (Exception e) {
            assertEquals("File corrupted!... Starting new session...\n", e.getMessage());
        }

        try {
            // Missing task type
            storage.loadTask("return book|27/1/2024 17:15|0");
            fail();
        } catch (Exception e) {
            assertEquals("File corrupted!... Starting new session...\n", e.getMessage());
        }

        try {
            // Dates swapped
            storage.loadTask("E|project meeting|1/1/1998 22:00|1/1/1998 13:00|1");
            fail();
        } catch (Exception e) {
            assertEquals("File corrupted!... Starting new session...\n", e.getMessage());
        }
    }
}
