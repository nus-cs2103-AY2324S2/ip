package drew.storage;
import drew.storage.Storage;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    public void parseSave_statusCorrupted_exceptionThrown() {
        String corruptedInput = "T | Not 1 or 0 | Test";
        Storage test_storage = new Storage("test");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> test_storage.parseSave(corruptedInput));
        String expected = "Load Error: File Corrupted";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parseSave_todoArgumentCountWrong_exceptionThrown() {
        String corruptedInput = "T | 0";
        Storage test_storage = new Storage("test");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> test_storage.parseSave(corruptedInput));
        String expected = "Load Error: File Corrupted";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parseSave_eventDateWrongFormat_exceptionThrown() {
        String corruptedInput = "E | 1 | test | 2099-123-2 | 2099-12-23";
        Storage test_storage = new Storage("test");
        Exception exception = assertThrows(DateTimeParseException.class, () -> test_storage.parseSave(corruptedInput));
    }
}
