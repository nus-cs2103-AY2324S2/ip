package nicole.taskstorage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

import nicole.nicoleexceptions.NicoleException;
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
            Storage storage = new Storage();
            storage.loadTasksFromFile();
        } catch (NicoleException e) {
            if (e.toString().equals("ERROR. Sorry sorry I have trouble loading your tasks from storage...")) {
                fail();
            }
        }
    }
}
