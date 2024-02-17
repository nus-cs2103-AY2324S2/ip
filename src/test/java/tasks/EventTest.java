package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventFormatTest() {
        Event e = new Event("Description", LocalDate.parse("2019-12-02"),
                LocalDate.parse("2019-12-02"));

        assertEquals("[E][ ] Description (from: Dec 2 2019 to: Dec 2 2019)", e.toString());
        assertEquals("E N Description /from 2019-12-02 /to 2019-12-02", e.toSaveFormat());

        e.isDone = true;

        assertEquals("[E][X] Description (from: Dec 2 2019 to: Dec 2 2019)", e.toString());
        assertEquals("E Y Description /from 2019-12-02 /to 2019-12-02", e.toSaveFormat());

    }
}
