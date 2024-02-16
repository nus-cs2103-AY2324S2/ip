package demon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

/**
 * A test of the Storage class, load method.
 */
public class StorageTest {
    @Test
    public void storageTest_success() throws Exception {
        List<String> expected = List.of("T | 1 | asdsad");
        assertEquals(expected, new Storage("src/main/taskList.txt").load(),"Load method returns unexpected result");
    }
}
