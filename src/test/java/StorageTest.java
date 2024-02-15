package Luke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void loadTasksFromFilesTest() throws LukeException {
        Storage storage = new Storage("src/test/java/StorageTest.txt");
        String list = "Here are the tasks in your list:\n"
                + "1. [T][ ] borrow book\n"
                + "2. [D][ ] [D][X] return book (by: Sunday)\n"
                + "3. [E][ ] project meeting  (from: Mon 2pm to: 4pm)\n";
        TaskList testList = new TaskList(storage.loadFile());
        assertEquals(list, testList.getTaskList().toString());
    }
}