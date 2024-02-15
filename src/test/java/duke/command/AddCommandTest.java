/*
 * AddCommandTest.java
 * This class contains JUnit tests for the AddCommand class in the Duke application.
 */

package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Storage;
import duke.task.TaskList;

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
        String expectedMessage = "The description cannot be empty.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void addDeadline_validDateTime_addsDeadline() throws DukeException, IOException {
        AddCommand command = new AddCommand("deadline return book /by 2022-12-12T12:00");
        String result = command.execute(tasks, ui, storage);
        Assertions.assertTrue(result.contains("Got it. I've added this task"));
        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.getTask(0));
    }

    @Test
    void addDeadline_invalidDateTime_throwsDukeException() {
        AddCommand command = new AddCommand("deadline return book /by 2022-12-12");
        DukeException exception = assertThrows(DukeException.class, () -> command.execute(tasks, ui, storage));
        String expectedMessage = "Invalid date-time format. "
                + "Please use a valid format such as yyyy-MM-dd HH:mm or dd/MM/yyyy HH:mm.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void addEvent_validEvent_addsEvent() throws DukeException, IOException {
        AddCommand command = new AddCommand("event team meeting /from 2022-12-12 10:00 /to 2022-12-12 12:00");
        String result = command.execute(tasks, ui, storage);
        Assertions.assertTrue(result.contains("Got it. I've added this task"));
        assertInstanceOf(Event.class, tasks.getTask(0));
    }

    @Test
    void addEvent_invalidEventTime_throwsDukeException() {
        AddCommand command = new AddCommand("event team meeting /from 2022-12-12T10:00");
        assertThrows(DukeException.class, () -> command.execute(tasks, ui, storage));
    }
}
