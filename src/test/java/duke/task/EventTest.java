package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void createEventTest() {
        try {
            LocalDateTime from = LocalDateTime.of(2021, 1, 8, 6, 30);
            LocalDateTime to = LocalDateTime.of(2021, 1, 9, 6, 30);
            Event task = new Event("study", from, to);
            assertEquals("[E][ ] study (from: Jan 08, 2021 06:30 to: Jan 09, 2021 06:30)", task.toString());

            task.markDone();
            assertEquals("[E][X] study (from: Jan 08, 2021 06:30 to: Jan 09, 2021 06:30)", task.toString());

            task.markUndone();
            assertEquals("[E][ ] study (from: Jan 08, 2021 06:30 to: Jan 09, 2021 06:30)", task.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void storageEventTest() {
        try {
            LocalDateTime from = LocalDateTime.of(2021, 1, 8, 6, 30);
            LocalDateTime to = LocalDateTime.of(2021, 1, 9, 6, 30);
            Event task = new Event("study", from, to);
            assertEquals("event , 0 , study , 2021-01-08 , 06:30 , 2021-01-09 , 06:30", task.toStorageString());

            task.markDone();
            assertEquals("event , 1 , study , 2021-01-08 , 06:30 , 2021-01-09 , 06:30", task.toStorageString());

            task.markUndone();
            assertEquals("event , 0 , study , 2021-01-08 , 06:30 , 2021-01-09 , 06:30", task.toStorageString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
