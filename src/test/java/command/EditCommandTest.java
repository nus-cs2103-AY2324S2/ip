package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import exception.BuddyException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;


public class EditCommandTest {
    private Storage storage;
    private TaskList tasks;

    public EditCommandTest() {
        this.storage = new Storage("");
        this.tasks = new TaskList();

        Task task1 = new Todo("test 1");
        Task task2 = new Deadline("return book",
                LocalDateTime.of(2024, 1, 1, 18, 0));
        Task task3 = new Todo("test 2");
        Task task4 = new Event("homecoming",
                LocalDateTime.of(2024, 1, 1, 18, 0),
                LocalDateTime.of(2024, 1, 2, 0, 0));

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        tasks.addTask(task4);
    }

    @Test
    public void execute_noChanges_success() {
        EditCommand e = new EditCommand(3,
                null, null, null, null);
        try {
            e.execute(tasks, storage);
            Event ev = (Event) tasks.getTask(3);
            assertEquals(ev.getDescription(), "homecoming");
            assertEquals(ev.getFrom(), LocalDateTime.of(2024, 1, 1, 18, 0));
            assertEquals(ev.getTo(), LocalDateTime.of(2024, 1, 2, 0, 0));
        } catch (BuddyException be) {
            fail();
        }
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        EditCommand e = new EditCommand(99,
                "new task", null, null, null);
        try {
            e.execute(tasks, storage);
            assertEquals(tasks.getTask(0).getDescription(), "new task");
            fail();
        } catch (BuddyException be) {
            assertEquals(be.getMessage(), "Not a valid task number!");
        }
    }

    @Test
    public void editTask_normalInput_success() {
        EditCommand e = new EditCommand(0,
                "new task", null, null, null);
        try {
            e.execute(tasks, storage);
            assertEquals(tasks.getTask(0).getDescription(), "new task");
        } catch (BuddyException be) {
            fail();
        }
    }

    @Test
    public void editDeadline_normalInput_success() {
        EditCommand e = new EditCommand(1,
                null, LocalDateTime.of(2020, 1, 1, 0, 0), null, null);
        try {
            e.execute(tasks, storage);
            Deadline dl = (Deadline) tasks.getTask(1);
            assertEquals(dl.getBy(), LocalDateTime.of(2020, 1, 1, 0, 0));
        } catch (BuddyException be) {
            fail();
        }
    }

    @Test
    public void editDeadline_incorrectType_exceptionThrown() {
        EditCommand e = new EditCommand(0,
                null, LocalDateTime.of(2020, 1, 1, 0, 0), null, null);
        try {
            e.execute(tasks, storage);
            fail();
        } catch (BuddyException be) {
            assertEquals(be.getMessage(), "Cannot edit by time as task is not a deadline!");
        }
    }

    @Test
    public void editStartTime_normalInput_success() {
        EditCommand e = new EditCommand(3,
                null, null, LocalDateTime.of(2020, 1, 1, 0, 0), null);
        try {
            e.execute(tasks, storage);
            Event ev = (Event) tasks.getTask(3);
            assertEquals(ev.getFrom(), LocalDateTime.of(2020, 1, 1, 0, 0));
            assertEquals(ev.getTo(), LocalDateTime.of(2024, 1, 2, 0, 0));
        } catch (BuddyException be) {
            fail();
        }
    }

    @Test
    public void editEndTime_normalInput_success() {
        EditCommand e = new EditCommand(3,
                null, null, null, LocalDateTime.of(2020, 1, 1, 0, 0));
        try {
            e.execute(tasks, storage);
            Event ev = (Event) tasks.getTask(3);
            assertEquals(ev.getTo(), LocalDateTime.of(2020, 1, 1, 0, 0));
            assertEquals(ev.getFrom(), LocalDateTime.of(2024, 1, 1, 18, 0));
        } catch (BuddyException be) {
            fail();
        }
    }

    @Test
    public void editStartTime_incorrectType_exceptionThrown() {
        EditCommand e = new EditCommand(1,
                null, null, LocalDateTime.of(2020, 1, 1, 0, 0), null);
        try {
            e.execute(tasks, storage);
            fail();
        } catch (BuddyException be) {
            assertEquals(be.getMessage(), "Cannot edit from time as task is not an event!");
        }
    }

    @Test
    public void editEndTime_incorrectType_exceptionThrown() {
        EditCommand e = new EditCommand(1,
                null, null, null, LocalDateTime.of(2020, 1, 1, 0, 0));
        try {
            e.execute(tasks, storage);
            fail();
        } catch (BuddyException be) {
            assertEquals(be.getMessage(), "Cannot edit to time as task is not an event!");
        }
    }
}
