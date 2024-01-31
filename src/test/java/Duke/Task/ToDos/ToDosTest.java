package Duke.Task.ToDos;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void testToString() {
        ToDos toDos = new ToDos("Read book");
        toDos.markAsDone();
        assertEquals("  [T][X] Read book", toDos.toString());
    }

    @Test
    public void testToFile() {
        ToDos toDos = new ToDos("Homework");
        toDos.markAsDone();
        assertEquals("T|1|Homework", toDos.toFile());
    }
}
