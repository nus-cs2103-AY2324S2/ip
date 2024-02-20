package duke.storage;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void parse_load_success() throws IOException {
        // read success from a valid path, NullPointerException should not be thrown
        String validPath = "./test_data/test_duke.txt";
        Storage storage = new Storage(validPath);
        Assertions.assertNotNull(storage.load());
    }

    @Test
    public void parse_load_exceptionThrown1() {
        // read fail from an invalid path, IOException should be thrown
        String inValidPath = "abcabcabc";
        Storage storage = new Storage(inValidPath);
        Assertions.assertThrows(NullPointerException.class, storage::load);

        // read fail from an invalid path with only the folder name, IOException should be thrown
        String inValidPath2 = "/only_file";
        Storage storage2 = new Storage(inValidPath2);
        Assertions.assertThrows(IOException.class, storage2::load);
    }

    @Test
    public void parse_load_exceptionThrown2() {
        // read fail from an invalid path with only the folder name, IOException should be thrown
        String inValidPath2 = "/only_file";
        Storage storage2 = new Storage(inValidPath2);
        Assertions.assertThrows(IOException.class, storage2::load);
    }
}
