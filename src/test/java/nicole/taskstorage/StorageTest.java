package nicole.taskstorage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

import nicole.nicoleexceptions.NicoleException;

import java.io.File;
import java.io.IOException;

public class StorageTest {

    @Test
    public void storage_saveTasksToFile_noExceptionThrown() {
        try {
            Storage storage = new Storage();
            storage.saveTasksToFile();
        } catch (NicoleException e) {
            fail();
        }
    }

    @Test
    public void storage_loadTasksFromFile_noNegativeExceptionThrown() {
        try {
            File tasksFile = new File("tasks.txt");
            tasksFile.createNewFile();
            new Storage().loadTasksFromFile(tasksFile);
        } catch (NicoleException | IOException e) {
            if (e.toString().equals("ERROR. Sorry sorry I have trouble loading your tasks from storage...")) {
                fail();
            }
        }
    }
}
