package headcube.task;
import headcube.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] hi", new ToDos("hi").toString());
    }

    @Test
    public void testToFileFormat() {
        assertEquals("T | 0 | hi",new ToDos("hi").toFileFormat());
    }

}
