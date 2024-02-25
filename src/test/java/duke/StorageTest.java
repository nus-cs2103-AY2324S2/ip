package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class StorageTest {
    @Test
    public void testSaveAndLoad() throws IOException, DukeException {

        Path filePath = Path.of("data/test.txt");
        Storage storage = new Storage(filePath.toString());
        TaskList list = new TaskList();

        ToDo todo = new ToDo("read book");
        Deadline deadline = new Deadline("return book", "2024-02-24");
        Event event = new Event("project meeting", "2024-02-24 14:00", "2024-02-24 16:00");


        list.addTask(todo);
        list.addTask(deadline);
        list.addTask(event);

        storage.save(list);
        List<String> lines = Files.readAllLines(filePath);

        assertEquals(3, lines.size());
        assertEquals("T | 0 | read book", lines.get(0));
        assertEquals("D | 0 | return book | 2024-02-24", lines.get(1));
        assertEquals("E | 0 | project meeting | 2024-02-24 14:00 | 2024-02-24 16:00", lines.get(2));
    }

}
