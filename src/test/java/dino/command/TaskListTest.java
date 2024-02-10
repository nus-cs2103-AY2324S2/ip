package dino.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dino.DinoException;
import dino.TaskList;
import dino.task.Deadline;
import dino.task.Event;
import dino.task.Task;


public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testNoConflictWhenAddingTodo() {
        Task todo = new Task("Finish tutorial");
        assertFalse(taskList.isConflict(todo));
    }

    @Test
    public void testNoConflictWhenAddingDeadline() {
        LocalDateTime deadlineDateTime = LocalDateTime.now().plusDays(1);
        Deadline deadline = new Deadline("Submit lab", deadlineDateTime);
        assertFalse(taskList.isConflict(deadline));
    }

    @Test
    public void testNoConflictWhenAddingEvent() {
        LocalDateTime startDateTime = LocalDateTime.now().plusDays(1);
        LocalDateTime endDateTime = LocalDateTime.now().plusDays(2);
        Event event = new Event("Team Project Meeting", startDateTime, endDateTime);
        assertFalse(taskList.isConflict(event));
    }

    @Test
    public void testConflictWhenAddingOverlappingEvent() {
        LocalDateTime startDateTime1 = LocalDateTime.of(2024, 2, 10, 9, 0);
        LocalDateTime endDateTime1 = LocalDateTime.of(2024, 2, 10, 12, 0);
        LocalDateTime startDateTime2 = LocalDateTime.of(2024, 2, 10, 11, 0);
        LocalDateTime endDateTime2 = LocalDateTime.of(2024, 2, 10, 15, 0);

        Event event1 = new Event("School", startDateTime1, endDateTime1);
        Event event2 = new Event("Appointment", startDateTime2, endDateTime2);

        try {
            taskList.addTask(event1);
        } catch (DinoException e) {
            throw new RuntimeException(e);
        }
        assertTrue(taskList.isConflict(event2));
    }
}
