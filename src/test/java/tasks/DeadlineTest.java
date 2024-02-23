package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringify_(){
        Deadline deadline = new Deadline("read book", "2024-02-03");
        assertEquals("[D][ ] read book (by: Feb 3 2024)", deadline.stringify());

        Deadline markedDeadline = new Deadline("play", "2024-02-03");
        markedDeadline.setStatus(true);
        assertEquals("[D][X] play (by: Feb 3 2024)", markedDeadline.stringify());
    }

    @Test
    public void testToString(){
        Deadline deadline = new Deadline("read book", "2024-02-03");
        assertEquals("D | O | read book | null | 2024-02-03", deadline.toString());

        Deadline markedDeadline = new Deadline("play", "2024-02-03");
        markedDeadline.setStatus(true);
        assertEquals("D | X | play | null | 2024-02-03", markedDeadline.toString());
    }
}
