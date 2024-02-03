import duke.ToDos;
import duke.Storage;
import duke.Deadlines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void checkStorageAdd() {
        Storage storage = new Storage();
        ToDos t = new ToDos("Read book");

        storage.add(t);
        String output = storage.addToListOutput(t);
        assertEquals(output, "Got it. I've added this task:\n" +
                "  [T][ ] Read book\n" +
                "Now you have 1 tasks in the list.");
    }


    @Test
    public void checkStorageList() {
        Storage storage = new Storage();

        storage.add(new ToDos("Read book"));

        String newOutput = storage.printList();
        assertEquals(newOutput, "1. [T][ ] Read book\n");
    }
}
