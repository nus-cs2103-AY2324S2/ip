package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskTest {
    @Test
    public void createTaskTest() {
        try {
            Task event = Task.of("event", false, "event",
                    "2021-01-08 06:30", "2021-01-09 06:30");
            assertEquals("[E][ ] event (from: Jan 08, 2021 06:30 to: Jan 09, "
                    + "2021 06:30)", event.toString());

            event.markDone();
            assertEquals("[E][X] event (from: Jan 08, 2021 06:30 to: Jan 09, "
                    + "2021 06:30)", event.toString());

            event.markUndone();
            assertEquals("[E][ ] event (from: Jan 08, 2021 06:30 to: Jan 09, "
                    + "2021 06:30)", event.toString());

            Task todo = Task.of("todo", false, "todo");
            assertEquals("[T][ ] todo", todo.toString());

            todo.markDone();
            assertEquals("[T][X] todo", todo.toString());

            todo.markUndone();
            assertEquals("[T][ ] todo", todo.toString());

            Task deadline = Task.of("deadline", false, "deadline",
                    "2021-01-01 06:30");
            assertEquals("[D][ ] deadline (by: Jan 01, 2021 06:30)", deadline.toString());

            deadline.markDone();
            assertEquals("[D][X] deadline (by: Jan 01, 2021 06:30)", deadline.toString());

            deadline.markUndone();
            assertEquals("[D][ ] deadline (by: Jan 01, 2021 06:30)", deadline.toString());
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }
}
