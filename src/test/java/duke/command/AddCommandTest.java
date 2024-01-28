package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AddCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui() {
            @Override
            public void showAddedTask(TaskList tasks) {
            }
        };
        storage = new Storage("data/duke.txt") {
            @Override
            public void saveTasks(TaskList tasks) {
            }
        };
    }

    @Test
    void addTodo_emptyDescription_throwsDukeException() {
        AddCommand command = new AddCommand("todo ");
        assertThrows(DukeException.class, () -> command.execute(tasks, ui, storage));
    }

    @Test
    void addDeadline_validDateTime_addsDeadline() throws DukeException, IOException {
        AddCommand command = new AddCommand("deadline return book /by 2022-12-12T12:00");
        command.execute(tasks, ui, storage);
        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.getTask(0));
    }

    @Test
    void addDeadline_invalidDateTime_throwsDukeException() {
        AddCommand command = new AddCommand("deadline return book /by 2022-12-12");
        assertThrows(DukeException.class, () -> command.execute(tasks, ui, storage));
    }
}
