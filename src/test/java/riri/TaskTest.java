package riri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markDoneTest() {
        Task task = new Todo("return book");
        task.markDone();
        assertEquals(task.getIsDone(), true);
    }
}
