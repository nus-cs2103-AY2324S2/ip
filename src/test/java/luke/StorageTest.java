package luke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void testLoadTasksFromFiles() throws LukeException {
        Storage storage = new Storage("src/test/java/Luke/StorageTest.txt");
        String list = "[E][ ] project meeting   (from: Mon 2pm to: 4pm)";
        TaskList testList = new TaskList(storage.loadFile());
        assertEquals(list, testList.getTaskList().get(0).toString());
    }
}
