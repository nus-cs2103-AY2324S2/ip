package duke.storage;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void load_loadFile_success() throws IOException {
        // read success from a valid path, NullPointerException should not be thrown
        String validPath = "./test_data/test_duke.txt";
        Storage storage = new Storage(validPath);
        Assertions.assertNotNull(storage.load());
    }

    @Test
    public void load_invalidPath_exceptionThrown() {
        // read fail from an invalid path, IOException should be thrown
        String inValidPath = "abcabcabc";
        Storage storage = new Storage(inValidPath);
        Assertions.assertThrows(NullPointerException.class, storage::load);
    }

    @Test
    public void load_folderNameOnly_exceptionThrown() {
        // read fail from an invalid path with only the folder name, IOException should be thrown
        String inValidPath2 = "/only_file";
        Storage storage2 = new Storage(inValidPath2);
        Assertions.assertThrows(IOException.class, storage2::load);
    }
}
