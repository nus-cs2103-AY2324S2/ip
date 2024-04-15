package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void markAsDone_undoneTask_marksTaskAsDone() {
        Task task = new ToDo("watch movie");
        task.markAsDone();
        assertEquals(true, task.getDoneStatus());
    }

    @Test
    public void markAsUndone_doneTask_marksTaskAsUndone() {
        Task task = new ToDo("watch movie");
        task.markAsDone();
        task.markAsUndone();
        assertEquals(false, task.getDoneStatus());
    }
}
