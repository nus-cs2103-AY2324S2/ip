/*
 * AddCommandTest.java
 * This class contains JUnit tests for the AddCommand class in the Duke application.
 */

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
        ui = new Ui();
        storage = new Storage("data/duke.txt") {
            @Override
            public void saveTasks(TaskList tasks) {
            }
        };
    }

    @Test
    void addTodo_emptyDescription_throwsDukeException() {
        AddCommand command = new AddCommand("todo ");
        DukeException exception = assertThrows(DukeException.class, () -> command.execute(tasks, ui, storage));
        String expectedMessage = "The description of a todo cannot be empty."; // Assuming this is the message in DukeException
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void addDeadline_validDateTime_addsDeadline() throws DukeException, IOException {
        AddCommand command = new AddCommand("deadline return book /by 2022-12-12T12:00");
        String result = command.execute(tasks, ui, storage);
        assertTrue(result.contains("Got it. I've added this task"));
        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.getTask(0));
    }

    @Test
    void addDeadline_invalidDateTime_throwsDukeException() {
        AddCommand command = new AddCommand("deadline return book /by 2022-12-12");
        DukeException exception = assertThrows(DukeException.class, () -> command.execute(tasks, ui, storage));
        String expectedMessage = "Invalid date-time format. Please use a valid format such as yyyy-MM-dd HH:mm or dd/MM/yyyy HH:mm.";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
