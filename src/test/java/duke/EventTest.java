package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void matchdate_testCase_1() throws DukeException {
        LocalDate localDate = LocalDate.of(2024, 1, 1);
        assertTrue(new Event("concert", "2023-12-31 19:00", "2024-01-01 1:00")
                .canMatchDate(localDate));
    }

    @Test
    public void matchdate_testCase_2() throws DukeException {
        LocalDate localDate = LocalDate.of(2019, 1, 1);
        assertFalse(new Event("concert", "2023-12-31 19:00", "2024-01-01 1:00")
                .canMatchDate(localDate));
    }

    @Test
    public void taskToLine_test() throws DukeException {
        assertEquals("E | X | concert | 2023-12-31 19:00:00 | 2024-01-01 01:00:00",
                new Event("concert", "2023-12-31 19:00", "2024-01-01 1:00")
                        .taskToLine());
    }
}
