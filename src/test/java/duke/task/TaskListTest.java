package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void checkTaskListFunctionTest() {
        TaskList tasks = new TaskList();
        Task todo = new ToDo("todo");

        LocalDateTime from = LocalDateTime.of(2021,1,8, 6, 30);
        LocalDateTime to = LocalDateTime.of(2021,1,9, 6, 30);
        Event event = new Event("event", from, to);

        LocalDateTime by = LocalDateTime.of(2021,1,8, 6, 30);
        Deadline deadline = new Deadline("deadline", by);

        tasks.add(todo);
        tasks.add(event);
        tasks.add(deadline);

        try {
            tasks.markDone("1");
            assertEquals("[T][X] todo", todo.toString());
            tasks.markDone("2");
            assertEquals("[E][X] event (from: Jan 08, 2021 06:30 " +
                    "to: Jan 09, 2021 06:30)", event.toString());
            tasks.markDone("3");
            assertEquals("[D][X] deadline (by: Jan 08, 2021 06:30)", deadline.toString());

            tasks.markUndone("1");
            assertEquals("[T][ ] todo", todo.toString());
            tasks.markUndone("2");
            assertEquals("[E][ ] event (from: Jan 08, 2021 06:30 " +
                    "to: Jan 09, 2021 06:30)", event.toString());
            tasks.markUndone("3");
            assertEquals("[D][ ] deadline (by: Jan 08, 2021 06:30)", deadline.toString());

            assertEquals(2, tasks.deleteTask("1"));
            assertEquals(1, tasks.deleteTask("1"));
            assertEquals(0, tasks.deleteTask("1"));
        } catch (DukeException e) {

        }


    }
}
