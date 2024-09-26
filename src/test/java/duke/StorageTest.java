package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void EmptyStorageLoadTest() {
        Storage storage = new Storage("./taskList.txt");
        TaskList t1 = storage.load();
        assertEquals(t1.size(), new TaskList().size());
    }

}
