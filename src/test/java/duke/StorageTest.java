package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testSaveAndLoad() throws DukeException, IOException {
        Storage storage = new Storage("src/test/java/data/duketest.txt");
        MyList myList = new MyList();
        List<Task> loadedTasks;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        Task todo = new Todo("Buy groceries");
        Task deadline = new Deadline("Read a book", LocalDateTime.parse("2022-02-15 1800", formatter));
        Task event = new Event("Attend meeting", LocalDateTime.parse("2022-02-15 1400", formatter),
                LocalDateTime.parse("2022-02-15 1600", formatter));

        myList.addItem(todo);
        myList.addItem(deadline);
        myList.addItem(event);

        storage.save(myList);

        loadedTasks = storage.load();

        assertEquals(3, loadedTasks.size());

        assertEquals("[T][ ] Buy groceries", loadedTasks.get(0).toString());
        assertEquals("[D][ ] Read a book (by: Feb 15 2022 6:00PM)", loadedTasks.get(1).toString());
        assertEquals("[E][ ] Attend meeting (from: Feb 15 2022 2:00PM to: Feb 15 2022 4:00PM)", loadedTasks.get(2).toString());
    }
}
