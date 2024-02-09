package area;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_returnDeadline_stringReturnedsuccess(){
        Deadline  deadline = new Deadline("CS2103T" , "2021-12-31");
        assertEquals("[D][ ] CS2103T (by: Dec 31 2021)", deadline.toString());
    }
}
