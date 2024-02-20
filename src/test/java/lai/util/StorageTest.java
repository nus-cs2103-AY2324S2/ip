package lai.util;

import lai.task.Event;
import lai.task.Task;
import lai.task.ToDo;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void readTasksFile_validFile_success() throws IOException {
        File file = File.createTempFile( "test-tasks", ".txt");
        file.deleteOnExit();

        Task task = new Event("testing", "2024-01-01", "2024-02-02");
        Task task2 = new ToDo("testing again");

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(task + System.lineSeparator());
            fw.write(task2 + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

        Storage storage = new Storage(file.getPath());
        List<Task> tasks = storage.readTasksFile();
        assertEquals(tasks.get(0).toString(), task.toString());
        assertEquals(tasks.get(1).toString(), task2.toString());
    }
}
