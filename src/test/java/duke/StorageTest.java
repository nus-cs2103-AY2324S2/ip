package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void load_tasksFromFile_success() throws DukeException, IOException {
        Storage storage = new Storage("src/test/java/data/duketest.txt");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String testCase = "Here are the tasks in your list:\n"
                + "1. [T][ ] Buy groceries\n"
                + "2. [D][ ] Read a book (by: Feb 15 2022 6:00PM)\n"
                + "3. [E][ ] Attend meeting (from: Feb 15 2022 2:00PM to: Feb 15 2022 4:00PM)\n";
        MyList myListToBeTested = new MyList(storage.load());
        assertEquals(testCase, myListToBeTested.getTasks());
    }
}
