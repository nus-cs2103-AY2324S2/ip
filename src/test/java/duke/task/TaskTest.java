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
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void markTaskTest() {
        try {
            Task event = Task.of("event", false, "event",
                    "2021-01-08 06:30", "2021-01-09 06:30");
            event.markDone();
            assertEquals("[E][X] event (from: Jan 08, 2021 06:30 to: Jan 09, "
                    + "2021 06:30)", event.toString());

            event.markUndone();
            assertEquals("[E][ ] event (from: Jan 08, 2021 06:30 to: Jan 09, "
                    + "2021 06:30)", event.toString());
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void unmarkTaskTest() {
        try {
            Task event = Task.of("event", false, "event",
                    "2021-01-08 06:30", "2021-01-09 06:30");
            event.markDone();
            event.markUndone();
            assertEquals("[E][ ] event (from: Jan 08, 2021 06:30 to: Jan 09, "
                    + "2021 06:30)", event.toString());
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void createTask2Test() {
        try {
            Task todo = Task.of("todo", false, "todo");
            assertEquals("[T][ ] todo", todo.toString());
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void markTask2Test() {
        try {
            Task todo = Task.of("todo", false, "todo");
            todo.markDone();
            assertEquals("[T][X] todo", todo.toString());
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void unmarkTask2Test() {
        try {
            Task todo = Task.of("todo", false, "todo");
            todo.markDone();
            todo.markUndone();
            assertEquals("[T][ ] todo", todo.toString());
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void createTask3Test() {
        try {
            Task deadline = Task.of("deadline", false, "deadline",
                    "2021-01-01 06:30");
            assertEquals("[D][ ] deadline (by: Jan 01, 2021 06:30)", deadline.toString());
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void markTask3Test() {
        try {
            Task deadline = Task.of("deadline", false, "deadline",
                    "2021-01-01 06:30");
            deadline.markDone();
            assertEquals("[D][X] deadline (by: Jan 01, 2021 06:30)", deadline.toString());
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void unmarkTask3Test() {
        try {
            Task deadline = Task.of("deadline", false, "deadline",
                    "2021-01-01 06:30");
            deadline.markDone();
            deadline.markUndone();
            assertEquals("[D][ ] deadline (by: Jan 01, 2021 06:30)", deadline.toString());
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }
}
