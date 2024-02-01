package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadline_toString_success() throws Exception {
        assertEquals("[D][ ] return book (by: 6pm)", new Deadline("return book", "6pm").toString());

        assertEquals("[D][ ] 1234 (by: 12am)", new Deadline("1234", "12am").toString());
    }

    @Test
    public void deadline_fileString_success() throws Exception {
        assertEquals("D | 0 | read book | Feb", new Deadline("read book", "Feb").fileString());
    }
}
