package Task;

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
    }
}
