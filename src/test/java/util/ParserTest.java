package util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseDeadline_correctFormat_success() {
        // entering the right deadline format - deadline [description] /by [deadline]
        // results in a string array with [description, deadline]
        assertArrayEquals(new String[] {"feed dog", "2024-02-06"},
                     new Parser().parseDeadline("deadline feed dog /by 2024-02-06"));
    }

    @Test
    public void parseDate_correctDate_success() {
        // a correct date input returns a LocalDate object
        assertEquals(LocalDate.of(2024, Month.FEBRUARY, 6),
                     new Parser().parseDate("2024-02-06"));
    }
}
