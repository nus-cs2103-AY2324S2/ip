package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskListTest {
    private Task todo;
    private Event event;
    private Deadline deadline;

    public void createTaskList(TaskList tasks) {
        try {
            todo = new ToDo("todo");

            LocalDateTime from = LocalDateTime.of(2021, 1, 8, 6, 30);
            LocalDateTime to = LocalDateTime.of(2021, 1, 9, 6, 30);
            event = new Event("event", from, to);

            LocalDateTime by = LocalDateTime.of(2021, 1, 8, 6, 30);
            deadline = new Deadline("deadline", by);

            tasks.add(todo);
            tasks.add(event);
            tasks.add(deadline);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void checkTaskListFunctionTest() {
        TaskList tasks = new TaskList();
        createTaskList(tasks);

        String messages = tasks.getListTasksMessage();
        assertEquals("Hi Hi ! Here are the tasks in your list:\n"
                + "1. [T][ ] todo\n"
                + "2. [E][ ] event (from: Jan 08, 2021 06:30 to: Jan 09, 2021 06:30)\n"
                + "3. [D][ ] deadline (by: Jan 08, 2021 06:30)\n", messages);
    }

    @Test
    public void checkTaskMark1FunctionTest() {
        try {
            TaskList tasks = new TaskList();
            createTaskList(tasks);
            tasks.markDone("1");
            assertEquals("[T][X] todo", todo.toString());
            tasks.markUndone("1");
            assertEquals("[T][ ] todo", todo.toString());
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void checkTaskMark2FunctionTest() {
        try {
            TaskList tasks = new TaskList();
            createTaskList(tasks);

            tasks.markDone("2");
            assertEquals("[E][X] event (from: Jan 08, 2021 06:30 "
                    + "to: Jan 09, 2021 06:30)", event.toString());
            tasks.markUndone("2");
            assertEquals("[E][ ] event (from: Jan 08, 2021 06:30 "
                    + "to: Jan 09, 2021 06:30)", event.toString());
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void checkTaskDelete1FunctionTest() {
        try {
            TaskList tasks = new TaskList();
            createTaskList(tasks);

            assertEquals("Hi Hi! I've removed this task:\n[D][ ] deadline (by: Jan 08, 2021 06:30)\n"
                            + "Now you have 2 tasks in the list.",
                    tasks.deleteTask("3"));
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void checkTaskDelete2FunctionTest() {
        try {
            TaskList tasks = new TaskList();
            createTaskList(tasks);
            assertEquals("Hi Hi! I've removed this task:\n"
                    + "[E][ ] event (from: Jan 08, 2021 06:30 to: Jan 09, 2021 06:30)\n"
                    + "Now you have 2 tasks in the list.", tasks.deleteTask("2"));
        } catch (DukeException e) {
            System.out.println("Found " + e);
        }
    }

    @Test
    public void checkTaskEmptyTest() {
        TaskList tasks = new TaskList();
        assertEquals("Hey, your task list is empty!! Faster create your first task now!!!!",
                tasks.getListTasksMessage());

    }
}
