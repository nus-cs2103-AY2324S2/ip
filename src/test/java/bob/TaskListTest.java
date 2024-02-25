package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import bob.exception.BobException;
import bob.task.TaskStub;

public class TaskListTest {
    @Test
    public void addEvent_invalidEvent_exceptionThrown() {
        try {
            new TaskList().addEvent("", LocalDateTime.now().plusDays(1), LocalDateTime.now());
            fail();
        } catch (BobException e) {
            assertEquals("wow your event ends before it starts", e.getMessage());
        }
    }

    @Test
    public void mark_nonPositiveTaskIndex_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<>(List.of(new TaskStub("a"))));
        try {
            taskList.mark(-1, true);
            fail();
        } catch (BobException e) {
            assertEquals("bro i want a number between 1 and 1 but you gave me 0", e.getMessage());
        }
    }

    @Test
    public void mark_tooLargeTaskIndex_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<>(List.of(new TaskStub("a"))));
        try {
            taskList.mark(1, true);
            fail();
        } catch (BobException e) {
            assertEquals("bro i want a number between 1 and 1 but you gave me 2", e.getMessage());
        }
    }

    @Test
    public void delete_nonPositiveTaskIndex_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<>(List.of(new TaskStub("a"))));
        try {
            taskList.delete(-1);
            fail();
        } catch (BobException e) {
            assertEquals("bro i want a number between 1 and 1 but you gave me 0", e.getMessage());
        }
    }

    @Test
    public void delete_tooLargeTaskIndex_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<>(List.of(new TaskStub("a"))));
        try {
            taskList.delete(1);
            fail();
        } catch (BobException e) {
            assertEquals("bro i want a number between 1 and 1 but you gave me 2", e.getMessage());
        }
    }
}
