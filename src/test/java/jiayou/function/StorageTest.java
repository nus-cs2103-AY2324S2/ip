package jiayou.function;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jiayou.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class StorageTest {
    @Test
    public void testParseFromFile() {
        LocalDate from = LocalDate.parse("2010-01-01");
        LocalDate to = LocalDate.parse("2020-01-01");
        Event expected = new Event("Complete homework", from, to);
        String input = "E | 0 | Complete homework | from 2010-01-01 to 2020-01-01";
        assertEquals(expected, Storage.parseFromFile(input));
    }
}