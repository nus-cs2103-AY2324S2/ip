package ficin.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testMarkDone() {
        Task sub3 = new Task("testSubject3");
        sub3.markAsDone();
        assertTrue(sub3.isDone());
    }
}
