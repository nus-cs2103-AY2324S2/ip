package duke.storage;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void loadTest() throws IOException {
        // read fail from an invalid path, NullPointerException should be thrown
        String inValidPath = "abcabcabc";
        Storage storage = new Storage(inValidPath);
        Assertions.assertThrows(NullPointerException.class, storage::load);

        // read success from a valid path, NullPointerException should be thrown
        String validPath = "./test_data/test_duke.txt";
        Storage storage2 = new Storage(validPath);
        Assertions.assertNotNull(storage2.load());

        // read fail from an invalid path with only the folder name, NullPointerException should be thrown
        String inValidPath2 = "/only_file";
        Storage storage3 = new Storage(inValidPath2);
        Assertions.assertThrows(NullPointerException.class, storage::load);
    }
}
