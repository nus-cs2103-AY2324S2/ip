package drew.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void parseSave_statusCorrupted_exceptionThrown() {
        String corruptedInput = "T | Not 1 or 0 | Test";
        Storage testStorage = new Storage("test");
        Exception exception = assertThrows(
                IllegalArgumentException.class, () -> testStorage.parseSave(corruptedInput));
        String expected = "Load Error: File Corrupted";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parseSave_todoArgumentCountWrong_exceptionThrown() {
        String corruptedInput = "T | 0";
        Storage testStorage = new Storage("test");
        Exception exception = assertThrows(
                IllegalArgumentException.class, () -> testStorage.parseSave(corruptedInput));
        String expected = "Load Error: File Corrupted";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parseSave_eventDateWrongFormat_exceptionThrown() {
        String corruptedInput = "E | 1 | test | 2099-123-2 | 2099-12-23";
        Storage testStorage = new Storage("test");
        Exception exception = assertThrows(DateTimeParseException.class, () -> testStorage.parseSave(corruptedInput));
    }
}
