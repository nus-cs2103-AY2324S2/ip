package duke.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void markAsDone_taskNotDone_markedAsDone() {
        Todo todo = new Todo("Complete assignment", false);
        todo.markAsDone();

        assertTrue(todo.isComplete);
    }
}
