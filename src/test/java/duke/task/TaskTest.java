package duke.task;

import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TaskTest {
    @Test
    public void testMarkDone() {
        Task sub3 = new Task("testSubject3");
        sub3.markAsDone();
        assertTrue(sub3.isDone());
    }
}
