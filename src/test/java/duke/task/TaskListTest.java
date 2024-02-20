package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskListTest {
    @SuppressWarnings("checkstyle:EmptyCatchBlock")
    @Test
    public void checkTaskListFunctionTest() {
        try {
            TaskList tasks = new TaskList();
            Task todo = new ToDo("todo");

            LocalDateTime from = LocalDateTime.of(2021, 1, 8, 6, 30);
            LocalDateTime to = LocalDateTime.of(2021, 1, 9, 6, 30);
            Event event = new Event("event", from, to);

            LocalDateTime by = LocalDateTime.of(2021, 1, 8, 6, 30);
            Deadline deadline = new Deadline("deadline", by);

            tasks.add(todo);
            tasks.add(event);
            tasks.add(deadline);

            tasks.markDone("1");
            assertEquals("[T][X] todo", todo.toString());
            tasks.markDone("2");
            assertEquals("[E][X] event (from: Jan 08, 2021 06:30 "
                    + "to: Jan 09, 2021 06:30)", event.toString());
            tasks.markDone("3");
            assertEquals("[D][X] deadline (by: Jan 08, 2021 06:30)", deadline.toString());

            tasks.markUndone("1");
            assertEquals("[T][ ] todo", todo.toString());
            tasks.markUndone("2");
            assertEquals("[E][ ] event (from: Jan 08, 2021 06:30 "
                    + "to: Jan 09, 2021 06:30)", event.toString());
            tasks.markUndone("3");
            assertEquals("[D][ ] deadline (by: Jan 08, 2021 06:30)", deadline.toString());

            assertEquals("Noted. I've removed this task:\n[D][ ] deadline (by: Jan 08, 2021 06:30)\n"
                            + "Now you have 2 tasks in the list.",
                    tasks.deleteTask("3"));
            assertEquals("Noted. I've removed this task:\n"
                    + "[E][ ] event (from: Jan 08, 2021 06:30 to: Jan 09, 2021 06:30)\n"
                    + "Now you have 1 task in the list.", tasks.deleteTask("2"));
            assertEquals("Noted. I've removed this task:\n"
                    + "[T][ ] todo\n"
                    + "Now you have 0 task in the list.", tasks.deleteTask("1"));
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }
}
