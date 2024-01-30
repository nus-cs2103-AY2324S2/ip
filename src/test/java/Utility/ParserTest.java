package Utility;

import Exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void formatDate_correctFormat_success() throws DukeException {
        assertEquals("2024-02-02 2200", Parser.formatDate("Feb-02-2024 2200"));
        assertEquals("2024-12-12 2359", Parser.formatDate("Dec-12-2024 2359"));
    }

    @Test
    public void formatDate_wrongFormat_exceptionThrown() {
        try {
            Parser.formatDate("2024-12-12 2359");
        } catch (DukeException e) {
            assertEquals("File is of wrong format.", e.getMessage());
        }
    }
}
