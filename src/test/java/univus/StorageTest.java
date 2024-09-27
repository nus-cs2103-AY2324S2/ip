package univus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import univus.task.Deadline;
import univus.task.TaskList;


public class StorageTest {
    @Test
    public void loadTest() {
        String filePath = "./data/Test.txt";
        Storage storage = new Storage(filePath);
        TaskList actual = storage.loadFromFile();
        String message = "deadline return book /by 2019-12-05";
        int startIndex = message.indexOf("/");
        String dueDate = message.substring(startIndex + 1);
        String description = message.split("/")[0];
        Deadline expected = new Deadline(description, dueDate);
        assertEquals(expected.toString(), actual.getTask(1).toString());
    }
}
