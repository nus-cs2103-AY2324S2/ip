package huyang;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class TaskFactoryTest {

    @Test
    public void testCreateTodoTask() {
        try {
            Task task = TaskFactory.createTask("todo Task 1", Parser.CommandType.TODO);
            assertTrue(task instanceof ToDo, "Task should be an instance of Todo");
            assertEquals("Task 1", task.getTaskName(), "Task name should match input");
        } catch (TaskException e) {
            fail("Unexpected TaskException: " + e.getMessage());
        }
    }

    @Test
    public void testCreateDeadlineTask() {
        try {
            LocalDateTime arbitraryDeadlineTime = LocalDateTime.of(2024, 2, 17, 18, 0);
            String deadlineInput = "deadline Task 2 /by "
                    + arbitraryDeadlineTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Task task = TaskFactory.createTask(deadlineInput, Parser.CommandType.DEADLINE);
            assertTrue(task instanceof Deadline, "Task should be an instance of Deadline");
            assertEquals("Task 2", task.getTaskName(), "Task name should match input");
            assertEquals(arbitraryDeadlineTime, ((Deadline) task).getTime(), "Deadline should match input");
        } catch (TaskException e) {
            fail("Unexpected TaskException: " + e.getMessage());
        }
    }

    @Test
    public void testCreateEventTask() {
        try {
            LocalDateTime arbitraryStartTime = LocalDateTime.of(2024, 2, 17, 18, 0);
            LocalDateTime start = arbitraryStartTime;
            LocalDateTime end = arbitraryStartTime.plusHours(2);
            String eventInput = "event Task 3 /from " + start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    + " /to " + end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Task task = TaskFactory.createTask(eventInput, Parser.CommandType.EVENT);
            assertTrue(task instanceof Event, "Task should be an instance of Event");
            assertEquals("Task 3", task.getTaskName(), "Task name should match input");
            assertEquals(start, ((Event) task).getStart(), "Start time should match input");
            assertEquals(end, ((Event) task).getEnd(), "End time should match input");
        } catch (TaskException e) {
            fail("Unexpected TaskException: " + e.getMessage());
        }
    }

    @Test
    public void testEmptyTaskDescription() {
        assertThrows(TaskException.class, () -> TaskFactory.createTask("todo", Parser.CommandType.TODO),
                "Creating task with empty description should throw TaskException");
    }

    @Test
    public void testInvalidDeadlineFormat() {
        assertThrows(TaskException.class, () -> TaskFactory.createTask("deadline Task 5 /by invalidDateTimeFormat",
                        Parser.CommandType.DEADLINE),
                "Creating deadline task with invalid date format should throw TaskException");
    }

    @Test
    public void testInvalidEventFormat() {
        assertThrows(TaskException.class, () -> TaskFactory.createTask("event Task 6 /from "
                        + "invalidDateTimeFormat /to invalidDateTimeFormat", Parser.CommandType.EVENT),
                "Creating event task with invalid date format should throw TaskException");
    }
}
