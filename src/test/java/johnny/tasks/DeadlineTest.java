package johnny.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toString_unMarkedDeadline_success() {
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2013-06-15T23:50"));
        assertEquals("[D][ ] return book (by: Jun 15 2013 11:50 PM)", deadline.toString());
    }

    @Test
    public void toString_markedDeadline_success() {
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2013-06-15T23:50"));
        deadline.mark();
        assertEquals("[D][X] return book (by: Jun 15 2013 11:50 PM)", deadline.toString());
    }

    @Test
    public void addToFile_unMarkedDeadline_success() {
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2013-06-15T23:50"));
        assertEquals("D | 0 | return book | 2013/06/15 2350\n", deadline.addToFile());
    }

    @Test
    public void addToFile_markedDeadline_success() {
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2013-06-15T23:50"));
        deadline.mark();
        assertEquals("D | 1 | return book | 2013/06/15 2350\n", deadline.addToFile());
    }

}
