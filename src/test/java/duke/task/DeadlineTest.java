package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void createDeadlineTest() {
        LocalDateTime by = LocalDateTime.of(2021, 1, 8, 6, 30);
        Deadline task = new Deadline("study", by);
        assertEquals("[D][ ] study (by: Jan 08, 2021 06:30)", task.toString());
    }

    @Test
    public void markDeadlineTest() {
        LocalDateTime by = LocalDateTime.of(2021, 1, 8, 6, 30);
        Deadline task = new Deadline("study", by);

        task.markDone();
        assertEquals("[D][X] study (by: Jan 08, 2021 06:30)", task.toString());
    }

    @Test
    public void unmarkDeadlineTest() {
        LocalDateTime by = LocalDateTime.of(2021, 1, 8, 6, 30);
        Deadline task = new Deadline("study", by);
        task.markDone();
        task.markUndone();
        assertEquals("[D][ ] study (by: Jan 08, 2021 06:30)", task.toString());
    }

    @Test
    public void storageDeadlineTest() {
        LocalDateTime by = LocalDateTime.of(2021, 1, 8, 6, 30);
        Deadline task = new Deadline("study", by);
        assertEquals("deadline , 0 , study , 2021-01-08 , 06:30", task.toStorageString());

        task.markDone();
        assertEquals("deadline , 1 , study , 2021-01-08 , 06:30", task.toStorageString());

        task.markUndone();
        assertEquals("deadline , 0 , study , 2021-01-08 , 06:30", task.toStorageString());
    }
}
