package area;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toString_returnDeadline_stringReturnedsuccess() {
        Deadline deadline = new Deadline("CS2103T", "2024-12-31");
        assertEquals("[D][ ] CS2103T 0 (by: Dec 31 2024)", deadline.toString());
    }
}
