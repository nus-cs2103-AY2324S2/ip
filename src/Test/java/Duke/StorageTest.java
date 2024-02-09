package Duke;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testLoadTasks() {
        String filePath = "test.txt";
        Storage storage = new Storage(filePath);

        try {
            Task[] loadedTasks = storage.loadTasks();
            assertEquals(100, loadedTasks.length);
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}
