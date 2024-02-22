package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class StorageTest {
    @Test
    public void storageTest() {
        Storage s = new Storage();
        s.add("testString");
        assertEquals(s.read(1), "testString");
        s.edit(1, "nani");
        assertEquals(s.read(1), "nani");
    }
}
