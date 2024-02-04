package arona.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void testToStringConversion() {
        assertEquals("[T][ ] x", new ToDo("x").toString());
    }

    @Test
    public void testToFileFormat() {
        assertEquals("T|0|x y z", new ToDo("x y z").toDataFormat());
    }
}
