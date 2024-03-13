package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toDbString_expectedTodo_success() {
        assertEquals("T|0|Call Mum", new Todo("Call Mum").toDbString());
    }
}
