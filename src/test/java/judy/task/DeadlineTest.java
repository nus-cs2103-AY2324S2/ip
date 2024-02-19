package judy.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private Deadline createDeadline() {
        String by = "2025-01-01 1159";
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime byDateTime = LocalDateTime.parse(by, pattern);
        return new Deadline("project", byDateTime);
    }
    @Test
    public void testString() {
        Deadline d = createDeadline();
        assertEquals(" [D][ ] project (by: Jan 01 2025 11:59 AM)", d.toString());
    }
    @Test
    public void testTodoEncode() {
        Deadline d = createDeadline();
        assertEquals(" D | 0 | project | Jan 01 2025 11:59 AM", d.encode());
    }
}
