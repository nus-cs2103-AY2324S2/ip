package yue.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

    @Test
    public void testTag() {
        TodoTask todoTask = new TodoTask("Finish assignment");
        assertEquals("[T]", todoTask.tag());
    }

    @Test
    public void testToString() {
        TodoTask todoTask = new TodoTask("Finish assignment");
        assertEquals("[T][ ] Finish assignment", todoTask.toString());
    }
}

