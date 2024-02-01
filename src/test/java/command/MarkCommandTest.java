package command;

import org.junit.jupiter.api.Test;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MarkCommandTest {

    Storage storage;
    Ui ui;
    TaskList tasks;
    TaskList testList;

    public MarkCommandTest() {
        this.storage = new Storage("");
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.testList = new TaskList();

        Task todo1 = new Todo("test");
        Task deadline = new Deadline("return book",
                LocalDateTime.of(2024, 1, 1, 18, 0));
        Task todo3 = new Todo("task 3");

        tasks.addTask(todo1);
        tasks.addTask(deadline);
        todo3.setDone(true);
        tasks.addTask(todo3);
    }

    @Test
    public void execute_normalInput_success() throws Exception {
        MarkCommand mark = new MarkCommand(CommandType.MARK, 1);
        mark.execute(tasks, ui, storage);
        assertEquals(tasks.getTask(0).getStatusIcon(), " ");
        assertEquals(tasks.getTask(1).getStatusIcon(), "X");
        assertEquals(tasks.getTask(2).getStatusIcon(), "X");

        MarkCommand unmark = new MarkCommand(CommandType.UNMARK, 2);
        unmark.execute(tasks, ui, storage);
        assertEquals(tasks.getTask(0).getStatusIcon(), " ");
        assertEquals(tasks.getTask(1).getStatusIcon(), "X");
        assertEquals(tasks.getTask(2).getStatusIcon(), " ");

    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        try {
            MarkCommand mark = new MarkCommand(CommandType.MARK, 99);
            mark.execute(tasks, ui, storage);
            assertEquals(tasks.getTask(1), "X");
            fail();
        } catch (Exception e) {
            assertEquals("Not a valid task number!", e.getMessage());
        }
    }
}
