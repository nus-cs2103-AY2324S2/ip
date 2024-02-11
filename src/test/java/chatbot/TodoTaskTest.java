package chatbot;

import chatbot.exceptions.AlreadyMarkedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    @Test
    public void toString_newTask_success() {
        assertEquals(new TodoTask("test").toString(), "[T][ ] test");
    }

    @Test
    public void mark_existingTask_success() throws AlreadyMarkedException {
        TodoTask t = new TodoTask("test");
        t.mark();
        assertEquals(t.toString(), "[T][X] test");
    }
}
