package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testFileRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Deadline d = new Deadline("homework", LocalDateTime.parse("2023-01-01 2359", formatter));
        assertEquals("D |   | homework | by: 2023-01-01 2359", d.fileRepresentation());
        DoAfter a = new DoAfter("homework", LocalDateTime.parse("2023-01-01 2359", formatter));
        assertEquals("A |   | homework | after: 2023-01-01 2359", a.fileRepresentation());
        Event e = new Event("homework",
                LocalDateTime.parse("2022-01-01 2359", formatter),
                LocalDateTime.parse("2023-01-01 2359", formatter));
        assertEquals("E |   | homework | from: 2022-01-01 2359 to: 2023-01-01 2359", e.fileRepresentation());
    }
}
