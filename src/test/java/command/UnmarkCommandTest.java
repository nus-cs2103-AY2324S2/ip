package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import task.Todo;

public class UnmarkCommandTest {

    private Storage storage;
    private TaskList tasks;
    private TaskList testList;

    public UnmarkCommandTest() {
        this.storage = new Storage("");
        this.tasks = new TaskList();
        this.testList = new TaskList();

        Task task1 = new Todo("test");
        Task task2 = new Deadline("return book",
                LocalDateTime.of(2024, 1, 1, 18, 0));
        Task task3 = new Todo("task 3");

        task1.setDone(true);
        task2.setDone(true);
        task3.setDone(true);

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
    }

    @Test
    public void execute_normalInput_success() throws Exception {
        UnmarkCommand unmark = new UnmarkCommand(2);
        unmark.execute(tasks, storage);
        assertEquals(tasks.getTask(0).getStatusIcon(), "X");
        assertEquals(tasks.getTask(1).getStatusIcon(), "X");
        assertEquals(tasks.getTask(2).getStatusIcon(), " ");
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        try {
            UnmarkCommand unmark = new UnmarkCommand(99);
            unmark.execute(tasks, storage);
            assertEquals(tasks.getTask(1), "X");
            fail();
        } catch (Exception e) {
            assertEquals("Not a valid task number!", e.getMessage());
        }
    }
}
